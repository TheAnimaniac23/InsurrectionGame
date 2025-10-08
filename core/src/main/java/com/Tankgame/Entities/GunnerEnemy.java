package com.Tankgame.Entities;

import com.Tankgame.PathMaths.Pathfinding;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// issue: enemy will send a copy careening into oblivion when another enemy dies, seemingly random, dont know the fix yet.

public class GunnerEnemy extends EnemyBase {

    private List<int[]> path;
    private float tX;
    private float tY;

    private int visCool = 0;

    private boolean visible = false;

    private Random rand;

    public void init(int x, int y, AssetManager manager) {
        this.manager = manager;

        chassis = manager.get("Entities/Enemies/ChassisStaticMG.png", Texture.class);
        turret = manager.get("Entities/Enemies/TurretMG.png", Texture.class);
        healthT = manager.get("GUI/HealthBars/EnemyHealthBar0.png", Texture.class);
        healthDT = manager.get("GUI/HealthBars/EnemyHealthBar1.png", Texture.class);

        chassisSprite = new Sprite(chassis);
        turretSprite = new Sprite(turret);
        healthSprite = new Sprite(healthT);
        healthDSprite = new Sprite(healthDT);

        chassisSprite.setPosition(x, y);
        chassisSprite.setSize(1, 1);
        chassisSprite.setOrigin(0.5f, 0.5f);
        turretSprite.setCenter(chassisSprite.getX()+0.5f, chassisSprite.getY()+0.5f);
        turretSprite.setSize(0.75f, 2.5f);
        turretSprite.setOrigin(0.375f, 1.25f);

        healthSprite.setSize(1, 0.25f);
        healthDSprite.setSize(1, 0.25f);

        chassisRect = new Rectangle();
        chassisRect.set(x, y, 1, 1);

        bullets = new Array<>();
        hitboxes = new Array<>();
        path = new ArrayList<>();

        rand = new Random();
        seesPlayer = false;
    }

    private int[] bounder(int tarX, int tarY) {

        if (tarY >= 16) {
            tarY = 15;
        } else if (tarY < 0) {
            tarY = 0;
        }

        if (tarX >= 28) {
            tarX = 27;
        } else if (tarX < 0) {
            tarX = 0;
        }

        return new int[] {tarX, tarY};
    }

    @Override
    public void update(Array<Rectangle> hitboxes, Array<ProjectileBase> eBullets, int[][] map, float[] playerPos) {
        Pathfinding pathGen = new Pathfinding();
        this.hitboxes = hitboxes;
        hitboxes.removeValue(this.chassisRect, true);

        int targetX = 0;
        int targetY = 0;
        int max = 5;
        int min = -5;

        if (seesPlayer) {
            targetX = (int) Math.round(chassisSprite.getX()-0.5f);
            targetY = (int) Math.round(chassisSprite.getY()-0.5f);
        } else {
            targetX = rand.nextInt(max-min+1)+min + (int) Math.round(chassisSprite.getX()-0.5f);
            targetY = rand.nextInt(max-min+1)+min + (int) Math.round(chassisSprite.getY()-0.5f);
        }

        int[] target = bounder(targetX, targetY);
        targetX = target[0];
        targetY = target[1];

        while (map[targetY][targetX] == 1) {
            targetX = rand.nextInt(max-min+1)+min + (int) Math.round(chassisSprite.getX()-0.5f);
            targetY = rand.nextInt(max-min+1)+min + (int) Math.round(chassisSprite.getY()-0.5f);
            int[] temp = bounder(targetX, targetY);
            targetX = temp[0];
            targetY = temp[1];
        }

        int startY = (int) Math.round(chassisSprite.getY());
        int startX = (int) Math.round(chassisSprite.getX());

        if (path.isEmpty() && !seesPlayer) {
            path = pathGen.findPath(map, new int[] {startX, startY}, new int[] {targetX, targetY});
        }
        // figure out how to move via generated path, also to generate a new path when at the target location OR when the player is seen.
        int[] position = {(int) Math.round(chassisSprite.getX()-0.5f), (int) Math.round(chassisSprite.getY()-0.5f)};

        if (!path.isEmpty()) {
            int[] targetPos;
            if (path.size() > 1) {
                targetPos = new int[] {path.get(1)[0], path.get(1)[1]};
            } else {
                targetPos = new int[] {path.getFirst()[0], path.getFirst()[1]};
            }

            if (Arrays.equals(position, targetPos)) { // the issue was that *to me* this was comparing equal values, but their hashcodes were different due to how i got the values. fixed now.
                path.removeFirst();
            } else {
                // determine direction of travel on X and Y axis, then move the chassis towards it. Fixed it, issue was that i forgot to change which variable was being checked, its fixed now.
                if (targetPos[0] < position[0]) {
                    tX = -speed;
                } else if (targetPos[0] > position[0]) {
                    tX = speed;
                } else {
                    tX = 0;
                }

                if (targetPos[1] < position[1]) {
                    tY = -speed;
                } else if (targetPos[1] > position[1]) {
                    tY = speed;
                } else {
                    tY = 0;
                }
            }
        }

        // chassis rotation logic.
        float targetRot = MathUtils.atan2(tX, tY) * MathUtils.radiansToDegrees;

        if (chassisRot < 0 && targetRot >= 180) {
            chassisRot += 360;
        }
        if (chassisRot > 0 && targetRot < 0) {
            chassisRot -= 360;
        }

        float delta = Gdx.graphics.getDeltaTime();
        float rotSpeed = 360;
        float rotAmount = rotSpeed * delta;

        float angDif = targetRot - chassisRot;
        if (Math.abs(angDif) < rotAmount) {
            rotAmount = Math.abs(angDif);
        }
        rotAmount = Math.copySign(rotAmount, angDif);

        chassisRot = chassisRot + rotAmount;
        chassisSprite.setRotation(chassisRot);

        // turret movement
        if (seesPlayer) {
            turretRot = (float) Math.toDegrees(Math.atan2(playerPos[1]-(chassisSprite.getY()+0.5f), playerPos[0]-(chassisSprite.getX()+0.5f)))-90;
        } else {
            turretRot = chassisRot;
        }
        turretSprite.setRotation(turretRot);

        // chassis movement
        chassisSprite.setPosition(chassisSprite.getX() + tX, chassisSprite.getY() + tY);
        chassisRect.setPosition(chassisSprite.getX(), chassisSprite.getY());

        // collision, copied 1:1 from Player.java.
        for (int i = 0; i < this.hitboxes.size; i++) {
            Rectangle temp = this.hitboxes.get(i);

            int side = 4;

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

        // damage control.
        int tempH = health;

        for (ProjectileBase obj : eBullets) {
            if (chassisRect.overlaps(obj.bulletRect)) {
                tempH = health - obj.damage;
                if (tempH < 1) {
                    alive = false;
                }
            }
        }

        visCool -= 1;

        if (tempH != health) {
            health = tempH;
            float width = (float) health/healthTotal;
            healthSprite.setSize(width, 0.25f);
            visCool = 40;
            visible = true;
        }

        if (visCool <= 0) {
            visible = false;
        }

        if (visCool < 11 && visCool > -1) {
            float opac = (float) visCool/10;
            healthSprite.setAlpha(opac);
            healthDSprite.setAlpha(opac);
        } else {
            healthDSprite.setAlpha(1);
            healthSprite.setAlpha(1);
        }

        healthSprite.setPosition(chassisSprite.getX(), chassisSprite.getY()+1.0625f);
        healthDSprite.setPosition(chassisSprite.getX(), chassisSprite.getY()+1.0625f);

        turretSprite.setCenter(chassisSprite.getX()+0.5f, chassisSprite.getY()+0.5f);
        chassisRect.setPosition(chassisSprite.getX(), chassisSprite.getY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (ProjectileBase obj : bullets) {
            obj.bulletSprite.draw(batch);
        }

        chassisSprite.draw(batch);
        turretSprite.draw(batch);

        if (visible) {
            healthDSprite.draw(batch);
            healthSprite.draw(batch);
        }
    }
}
