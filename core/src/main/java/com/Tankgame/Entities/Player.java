package com.Tankgame.Entities;

import com.Tankgame.Main;
import com.Tankgame.Projectiles.GunBullet;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Player {

    private Texture chassis;
    private Texture turret;
    private Texture healthT;
    private Texture healthTBack;

    private Sprite chassisSprite;
    private Sprite turretSprite;
    public Rectangle chassisRect;

    private Sprite healthSpriteBack;
    private Sprite healthSprite;

    private float mouseX;
    private float mouseY;

    private float targetX;
    private float targetXMove;
    private float targetY;
    private float targetYMove;
    private final float moveSpeed = 0.05f;
    private float chassisRot = 0;
    private float turretRot = 0;
    public int health = 20;
    private int cooldown = 0;
    private boolean buttonGun;
    private Array<Rectangle> hitboxes;
    private AssetManager man;

    public Array<ProjectileBase> activeBullets = new Array<>();
    public final Pool<ProjectileBase> bulletPool = new Pool<ProjectileBase>() {
        @Override
        protected ProjectileBase newObject() {
            return new GunBullet();
        }
    };

    public void Player(int spawnx, int spawny, AssetManager manager) {

        chassis = manager.get("Entities/Player/PlayerChassisStatic.png", Texture.class);
        turret = manager.get("Entities/Player/PlayerTurretMG.png", Texture.class);
        healthT = manager.get("GUI/HealthBars/PlayerHealthBar1.png", Texture.class);
        healthTBack = manager.get("GUI/HealthBars/PlayerHealthBar0.png", Texture.class);

        chassisSprite = new Sprite(chassis);
        turretSprite = new Sprite(turret);
        healthSprite = new Sprite(healthT);
        healthSpriteBack = new Sprite(healthTBack);

        chassisRect = new Rectangle();

        man = manager;

        chassisRect.set(spawnx, spawny, 1, 1);

        chassisSprite.setSize(1, 1);
        chassisSprite.setPosition(spawnx, spawny);
        chassisSprite.setOrigin(0.5f, 0.5f);
        turretSprite.setSize(0.75f, 2.5f);
        turretSprite.setCenter(chassisSprite.getX() + 0.5f, chassisSprite.getY() + 0.5f);
        turretSprite.setOrigin(turretSprite.getWidth()/2, turretSprite.getHeight()/2);

        healthSpriteBack.setSize(6, 1);
        healthSpriteBack.setPosition(0, 15);
        healthSprite.setSize(4.25f, 0.375f);
        healthSprite.setPosition(0.25f, 15.3125f);

        Gdx.input.setInputProcessor( new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    buttonGun = true;
                }
                return true;
            }

            @Override
            public boolean touchUp(int x, int y, int pointer, int button) {
                if (button == Input.Buttons.LEFT) {
                    buttonGun = false;
                }
                return true;
            }
        });
    }

    private void WeaponFire() {
        if (cooldown <= 0 && activeBullets.size < 10) {
            ProjectileBase bullet = bulletPool.obtain();
            float bulletX = chassisSprite.getX()+(chassisSprite.getWidth()/2);
            float bulletY = chassisSprite.getY()+(chassisSprite.getHeight()/2);
            bullet.init(bulletX, bulletY, man, 150, turretRot);
            activeBullets.add(bullet);
            // sets so new bullet spawns every 60 ticks or ~1 bullet a second.
            cooldown = 10;
        }
    }

    private void WeaponUpdate(float delta) {
        cooldown -= 1;
        int len = activeBullets.size;
        for (int i = len; --i >= 0;) {
            ProjectileBase obj = activeBullets.get(i);
            obj.update(delta, hitboxes);
            if (!obj.alive) {
                activeBullets.removeIndex(i);
                bulletPool.free(obj);
            }
        }
    }

    public void input(final Main game) {
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 mouseGamePos = game.viewport.unproject(mousePos);
        mouseX = mouseGamePos.x;
        mouseY = mouseGamePos.y;

        boolean keysW = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean keysA = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean keysS = Gdx.input.isKeyPressed(Input.Keys.S);
        boolean keysD = Gdx.input.isKeyPressed(Input.Keys.D);

        chassisRot = chassisSprite.getRotation();

        if (keysW || keysA || keysS || keysD) {
            if (keysW && !keysS) {
                targetY = moveSpeed;
            } else if (keysS && !keysW) {
                targetY = -moveSpeed;
            } else {
                targetY = 0;
            }
            if (keysD && !keysA) {
                targetX = -moveSpeed;
            } else if (keysA && !keysD) {
                targetX = moveSpeed;
            } else {
                targetX = 0;
            }
            targetXMove = -targetX;
            targetYMove = targetY;
        } else {
            targetXMove = 0;
            targetYMove = 0;
        }

        if (buttonGun) {
            WeaponFire();
        }
    }

    public void logic(Array<Rectangle> hitboxes, Array<ProjectileBase> bullets) {
        this.hitboxes = hitboxes;

        // Turret Rotation Logic, makes much sense to me.
        turretRot = (float) Math.toDegrees(Math.atan2(mouseY-(chassisSprite.getY()+0.5f), mouseX-(chassisSprite.getX()+0.5f)))-90;
        turretSprite.setRotation(turretRot);

        // Chassis Rotation Logic... very confusing, I only 75% understand it.
        float targetRot = MathUtils.atan2(targetX, targetY) * MathUtils.radiansToDegrees;

        if (chassisRot < 0 && targetRot >= 180) {
            chassisRot += 360;
        }
        if (chassisRot > 0 && targetRot < 0) {
            chassisRot -= 360;
        }

        float delta = Gdx.graphics.getDeltaTime();
        float rotSpeed = 360;
        float rotAmount = rotSpeed *delta;

        float angDif = targetRot - chassisRot;
        if (Math.abs(angDif) < rotAmount) {
            rotAmount = Math.abs(angDif);
        }
        rotAmount = Math.copySign(rotAmount, angDif);

        chassisRot = chassisRot + rotAmount;
        chassisSprite.setRotation(chassisRot);

        // Movement of the chassis
        chassisSprite.setPosition(chassisSprite.getX()+targetXMove, chassisSprite.getY()+targetYMove);
        chassisRect.setPosition(chassisSprite.getX(), chassisSprite.getY());

        // collision control

        for (int i = 0; i < this.hitboxes.size; i++) {
            Rectangle temp = this.hitboxes.get(i);

            int side = 4; // 0=top, 1=left, 2=bottom, 3=right

            if (temp.overlaps(chassisRect)) {
                float centerXP = chassisRect.x + (chassisRect.width / 2);
                float centerYP = chassisRect.y + (chassisRect.height / 2);
                float centerXW = temp.x + (temp.width / 2);
                float centerYW = temp.y + (temp.height / 2);

                float overX;
                float overY;

                if (centerXP < centerXW) {
                    overX = (chassisRect.x + chassisRect.width) - temp.x;
                } else {
                    overX = (temp.x + temp.width) - chassisRect.x;
                }

                if (centerYP < centerYW) {
                    overY = (chassisRect.y + chassisRect.height) - temp.y;
                } else {
                    overY = (temp.y + temp.height) - chassisRect.y;
                }

                if (Math.abs(overX) < Math.abs(overY)) {
                    if (centerXP < centerXW) {
                        side = 3;
                    } else {
                        side = 1;
                    }
                } else {
                    if (centerYP < centerYW) {
                        side = 0;
                    } else {
                        side = 2;
                    }
                }
            }

            float ancX = chassisSprite.getX();
            float ancY = chassisSprite.getY();

            if (side == 0) {
                ancY = temp.getY() - chassisSprite.getHeight();
            } else if (side == 1) {
                ancX = temp.getX() + temp.getWidth();
            } else if (side == 2) {
                ancY = temp.getY() + temp.getHeight();
            } else if (side == 3) {
                ancX = temp.getX() - chassisSprite.getWidth();
            }
            chassisSprite.setPosition(ancX, ancY);
        }

        // bullet damage control
        for (ProjectileBase obj : bullets) {
            if (chassisRect.overlaps(obj.bulletRect)) {
                health -= obj.damage;
            }
        }

        // health bar logic
        float temp = 4.25f * health/20;
        healthSprite.setSize(temp, 0.375f);

        chassisRect.setPosition(chassisSprite.getX(), chassisSprite.getY());
        turretSprite.setCenter(chassisSprite.getX()+0.5f, chassisSprite.getY()+0.5f);

        WeaponUpdate(delta);
    }

    public void draw(SpriteBatch batch) {
        for (ProjectileBase obj : activeBullets) {
            obj.bulletSprite.draw(batch);
        }

        chassisSprite.draw(batch);
        turretSprite.draw(batch);
    }

    //draws the player health bar. Always draws last on screen, so its on top.
    public void draw2(SpriteBatch batch) {
        healthSpriteBack.draw(batch);
        healthSprite.draw(batch);
    }

}
