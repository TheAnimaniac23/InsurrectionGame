package com.Tankgame.Entities;

import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class EnemyBase implements Pool.Poolable {

    public Texture chassis;
    public Texture turret;
    public Texture healthT;
    public Texture healthDT;

    public Sprite chassisSprite;
    public Sprite turretSprite;
    public Sprite healthSprite;
    public Sprite healthDSprite;

    public Rectangle chassisRect;

    public Array<ProjectileBase> bullets;

    public AssetManager manager;

    public int health = 20;
    public final int healthTotal = 20;
    public final float speed = 0.05f;

    public float chassisRot;
    public float turretRot;

    public boolean seesPlayer;
    public boolean alive = true;

    public Array<Rectangle> hitboxes;

    public void update(Array<Rectangle> hitboxes, Array<ProjectileBase> bullets, int[][] map, float[] playerPos) {}

    public void draw(SpriteBatch batch) {
        for (ProjectileBase obj : bullets) {
            obj.bulletSprite.draw(batch);
        }

        chassisSprite.draw(batch);
        turretSprite.draw(batch);
    }

    @Override
    public void reset() {
        chassisSprite.setPosition(0, 0);
        alive = false;
    }
}
