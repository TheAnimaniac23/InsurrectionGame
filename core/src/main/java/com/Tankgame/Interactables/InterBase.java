package com.Tankgame.Interactables;

import com.Tankgame.Entities.EnemyBase;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class InterBase {
    public Texture wall;
    public Texture healthTBack;
    public Texture healthT;
    public Sprite wallSprite;
    public Rectangle wallRect;
    public Sprite healthSpriteBack;
    public Sprite healthSprite;

    public Array<ProjectileBase> hitboxes;

    public AssetManager manager;

    public int health = 10;
    public int healthTotal;
    public boolean alive;

    public Array<ProjectileBase> bullets;
    public Pool<ProjectileBase> bulletsPool;
    public void init(int spawnx, int spawny, int variant, AssetManager manager, Pool<EnemyBase> epool) {}

    public void update(Array<ProjectileBase> hitboxes, Array<Rectangle> walls, Array<EnemyBase> enemies) {}

    public void update2() {}
    public void draw(SpriteBatch batch) {
        wallSprite.draw(batch);
    }
}

