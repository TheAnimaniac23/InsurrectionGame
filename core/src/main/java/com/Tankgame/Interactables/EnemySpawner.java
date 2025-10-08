package com.Tankgame.Interactables;

import com.Tankgame.Entities.EnemyBase;
import com.Tankgame.Entities.GunnerEnemy;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Random;

public class EnemySpawner extends InterBase {

    private Texture wall1;
    private Texture wall2;
    private Texture wall3;
    private Texture wall4;
    private Texture wall5;
    private Texture wall6;
    private Texture wall7;
    private Texture wall8;

    private int spawns = 8;
    private int cooldown;
    private int visCool = 0;

    private boolean visible = false;

    private Random rand;
    private Pool<EnemyBase> enemyPool;

    @Override
    public void init(int x, int y, int var, AssetManager man, Pool<EnemyBase> enemyPool) {
        manager = man;
        this.enemyPool = enemyPool;

        wall = manager.get("Tech/Spawner/EnemySpawner0.png", Texture.class);
        wall1 = manager.get("Tech/Spawner/EnemySpawner1.png", Texture.class);
        wall2 = manager.get("Tech/Spawner/EnemySpawner2.png", Texture.class);
        wall3 = manager.get("Tech/Spawner/EnemySpawner3.png", Texture.class);
        wall4 = manager.get("Tech/Spawner/EnemySpawner4.png", Texture.class);
        wall5 = manager.get("Tech/Spawner/EnemySpawner5.png", Texture.class);
        wall6 = manager.get("Tech/Spawner/EnemySpawner6.png", Texture.class);
        wall7 = manager.get("Tech/Spawner/EnemySpawner7.png", Texture.class);
        wall8 = manager.get("Tech/Spawner/EnemySpawner8.png", Texture.class);
        healthT = manager.get("GUI/HealthBars/EnemyHealthBar0.png", Texture.class);
        healthTBack = manager.get("GUI/HealthBars/EnemyHealthBar1.png", Texture.class);

        wallSprite = new Sprite(wall8);
        wallSprite.setPosition(x, y);
        wallSprite.setSize(1, 1);

        healthSprite = new Sprite(healthT);
        healthSpriteBack = new Sprite(healthTBack);
        healthSprite.setSize(1, 0.25f);
        healthSpriteBack.setSize(1, 0.25f);
        healthSprite.setPosition(x, y+1.0625f);
        healthSpriteBack.setPosition(x, y+1.0625f);

        wallRect = new Rectangle();
        wallRect.set(x, y, 1, 1);

        rand = new Random();

        cooldown = 0;
        health = 20;
        healthTotal = 20;
        alive = true;
    }

    public void spawnEnemy(Array<Rectangle> hitboxes, Array<EnemyBase> enemies) {
        // probably should make it check if the spot is unoccupied. don't know how to do it efficiently.

        int max = 1;
        int min = -1;
        int spawnX = rand.nextInt(max-min+1)+min + (int) wallSprite.getX();
        int spawnY = rand.nextInt(max-min+1)+min + (int) wallSprite.getY();

        GunnerEnemy enemy = new GunnerEnemy();
        enemy.init(spawnX, spawnY, manager);
        enemies.add(enemy);

        spawns -= 1;
        cooldown = 240;
    }

    @Override
    public void update(Array<ProjectileBase> bullets, Array<Rectangle> hitboxes, Array<EnemyBase> enemies) {
        int tempH = health;

        for (ProjectileBase bullet : bullets) {
            if (wallRect.overlaps(bullet.bulletRect)) {
                if (health > 1) {
                    tempH = health - bullet.damage;
                } else {
                    alive = false;
                }
            }
        }

        if (cooldown <= 0 && spawns > 0) {
            spawnEnemy(hitboxes, enemies);
        } else {
            cooldown -= 1;
        }

        if (spawns == 8) {
            wallSprite.setRegion(wall8);
        } else if (spawns == 7) {
            wallSprite.setRegion(wall7);
        } else if (spawns == 6) {
            wallSprite.setRegion(wall6);
        } else if (spawns == 5) {
            wallSprite.setRegion(wall5);
        } else if (spawns == 4) {
            wallSprite.setRegion(wall4);
        } else if (spawns == 3) {
            wallSprite.setRegion(wall3);
        } else if (spawns == 2) {
            wallSprite.setRegion(wall2);
        } else if (spawns == 1) {
            wallSprite.setRegion(wall1);
        } else if (spawns <= 0) {
            wallSprite.setRegion(wall);
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
    }

    @Override
    public void draw(SpriteBatch batch) {
        wallSprite.draw(batch);

        if (visible) {
            healthSpriteBack.draw(batch);
            healthSprite.draw(batch);
        }
    }

}
