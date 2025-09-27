package com.Tankgame.Entities;

import com.Tankgame.Main;
import com.Tankgame.Menus.LevelSelect;
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

    private Sprite chassisSprite;
    private Sprite turretSprite;
    private Rectangle chassisRect;

    private boolean keysW;
    private boolean keysA;
    private boolean keysS;
    private boolean keysD;
    private float mouseX;
    private float mouseY;

    private float targetX;
    private float targetXMove;
    private float targetY;
    private float targetYMove;
    private float rotSpeed = 360;
    private float moveSpeed = 0.05f;
    private float chassisRot = 0;
    private float turretRot = 0;
    private int health = 20;
    private int cooldown = 0;
    private boolean buttonGun;
    private Array<Rectangle> wallSprites;
    private AssetManager man;

    public Array<ProjectileBase> activeBullets = new Array<>();
    public final Pool<ProjectileBase> bulletPool = new Pool<ProjectileBase>() {
        @Override
        protected ProjectileBase newObject() {
            return new GunBullet();
        }
    };

    public void Player(int spawnx, int spawny, AssetManager manager, Array<Rectangle> walls) {

        chassis = manager.get("Entities/Player/PlayerChassisStatic.png", Texture.class);
        turret = manager.get("Entities/Player/PlayerTurretMG.png", Texture.class);

        chassisSprite = new Sprite(chassis);
        turretSprite = new Sprite(turret);
        chassisRect = new Rectangle();

        man = manager;

        chassisRect.set(spawnx, spawny, 1, 1);

        chassisSprite.setSize(1, 1);
        chassisSprite.setPosition(spawnx, spawny);
        chassisSprite.setOrigin(0.5f, 0.5f);
        turretSprite.setSize(0.75f, 2.5f);
        turretSprite.setCenter(chassisSprite.getX() + 0.5f, chassisSprite.getY() + 0.5f);
        turretSprite.setOrigin(turretSprite.getWidth()/2, turretSprite.getHeight()/2);

        wallSprites = walls;

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
            bullet.init(turretSprite.getX()+turretSprite.getWidth()/2, turretSprite.getY()+turretSprite.getHeight()/2, man, 360);
            activeBullets.add(bullet);
            // sets so new bullet spawns every 60 ticks or ~1 bullet a second.
            cooldown = 60;
        }
    }

    private void WeaponUpdate() {
        cooldown -= 1;
        int len = activeBullets.size;
        for (int i = len; --i >= 0;) {
            ProjectileBase obj = activeBullets.get(i);
            obj.update(1, wallSprites);
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

        keysW = Gdx.input.isKeyPressed(Input.Keys.W);
        keysA = Gdx.input.isKeyPressed(Input.Keys.A);
        keysS = Gdx.input.isKeyPressed(Input.Keys.S);
        keysD = Gdx.input.isKeyPressed(Input.Keys.D);

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

    public void logic() {
        // Turret Rotation Logic, makes much sense to me.
        turretRot = (float) Math.toDegrees(Math.atan2(mouseY-(chassisSprite.getY()+0.5f), mouseX-(chassisSprite.getX()+0.5f)));
        turretSprite.setRotation(turretRot-90);

        // Chassis Rotation Logic... very confusing, I only 75% understand it.
        float targetRot = MathUtils.atan2(targetX, targetY) * MathUtils.radiansToDegrees;

        if (chassisRot < 0 && targetRot >= 180) {
            chassisRot += 360;
        }
        if (chassisRot > 0 && targetRot < 0) {
            chassisRot -= 360;
        }

        float delta = Gdx.graphics.getDeltaTime();
        float rotAmount = rotSpeed*delta;

        float angDif = targetRot - chassisRot;
        if (Math.abs(angDif) < rotAmount) {
            rotAmount = Math.abs(angDif);
        }
        rotAmount = Math.copySign(rotAmount, angDif);

        chassisRot = chassisRot + rotAmount;
        chassisSprite.setRotation(chassisRot+rotAmount);

        // Movement of the chassis
        chassisSprite.setPosition(chassisSprite.getX()+targetXMove, chassisSprite.getY()+targetYMove);

        // collision control
        for (int i = 0; i < wallSprites.size; i++) {
            Rectangle temp = wallSprites.get(i);

            int side = 4; // 0=top, 1=left, 2=bottom, 3=right

            if (temp.overlaps(chassisRect)) {
                float centerXP = chassisRect.x + chassisRect.width / 2;
                float centerYP = chassisRect.y + chassisRect.height / 2;
                float centerXW = temp.x + temp.width / 2;
                float centerYW = temp.y + temp.height / 2;

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

            if (side == 0) {
                chassisSprite.setPosition(chassisSprite.getX(), chassisSprite.getY()-moveSpeed);
            } else if (side == 1) {
                chassisSprite.setPosition(chassisSprite.getX()+moveSpeed, chassisSprite.getY());
            } else if (side == 2) {
                chassisSprite.setPosition(chassisSprite.getX(), chassisSprite.getY()+moveSpeed);
            } else if (side == 3) {
                chassisSprite.setPosition(chassisSprite.getX()-moveSpeed, chassisSprite.getY());
            }
        }
        chassisRect.setPosition(chassisSprite.getX(), chassisSprite.getY());
        turretSprite.setCenter(chassisSprite.getX()+0.5f, chassisSprite.getY()+0.5f);

        WeaponUpdate();
    }

    public void draw(SpriteBatch batch) {
        chassisSprite.draw(batch);
        turretSprite.draw(batch);

        for (ProjectileBase obj : activeBullets) {
            obj.bulletSprite.draw(batch);
        }
    }

}
