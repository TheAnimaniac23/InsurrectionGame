package com.Tankgame.Interactables;

import com.Tankgame.Entities.EnemyBase;
import com.Tankgame.Projectiles.FireBall;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

import java.util.Random;

public class Explosive extends InterBase {

    private boolean dett = false;
    private boolean adett = true;
    private int cooldown = 0;

    public Texture ash;

    private Random rng = new Random();

    @Override
    public void init(int spawnx, int spawny, int variant, AssetManager man, Pool<EnemyBase> epool) {
        wall = man.get("Misc/ExplosiveCrate.png", Texture.class);
        ash = man.get("Misc/AshMark.png", Texture.class);

        wallSprite = new Sprite(wall);
        wallRect = new Rectangle();

        wallSprite.setPosition(spawnx, spawny);
        wallSprite.setSize(1, 1);

        bullets = new Array<>();
        bulletsPool = new Pool<ProjectileBase>() {
            @Override
            protected ProjectileBase newObject() {
                return new FireBall();
            }
        };

        wallRect.set(spawnx+0.0625f, spawny+0.0625f, 0.875f, 0.875f);

        alive = true;

        manager = man;
    }

    private void ExUpdate(float delta, Array<Rectangle> walls) {
        walls.removeValue(wallRect, true);

        int len = bullets.size;
        for (int i = len; --i >= 0;) {
            ProjectileBase obj = bullets.get(i);
            obj.update(delta, walls);
            if (!obj.alive) {
                bullets.removeIndex(i);
                bulletsPool.free(obj);
            }
        }
    }

    @Override
    public void update(Array<ProjectileBase> hitboxes, Array<Rectangle> walls, Array<EnemyBase> enemies) {
        for (ProjectileBase obj : hitboxes) {
            if (wallRect.overlaps(obj.bulletRect)) {
                dett = true;
            }
        }

        if (dett && adett) {
            // creates a big explosion, aka spawn a bunch of "Fire Ball" projectiles in random directions. realistically should create 5-15 fireballs of random sizes.
            wallSprite.setRegion(ash);

            cooldown = 100;
            adett = false;

            for (int i = 0; i < 15; i++) {
                // for whatever reason it's not generating all the FireBall objects.
                ProjectileBase bullet = bulletsPool.obtain();
                float bX = wallSprite.getX() + wallSprite.getWidth()/2;
                float bY = wallSprite.getY() + wallSprite.getHeight()/2;
                int rot = rng.nextInt(361);
                bullet.init(bX, bY, manager, 17, rot);
                bullets.add(bullet);
            }
        }

        ExUpdate(Gdx.graphics.getDeltaTime(), walls);

        if (cooldown > 0 && dett) {
            cooldown -= 1;
            float opa = (float) cooldown/100;
            wallSprite.setAlpha(opa);
        } else if (cooldown <= 0 && dett) {
            alive = false;
        }
    }

    @Override
    public void update2() {
        if (dett) {
            wallRect.setPosition(-5, -5);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        wallSprite.draw(batch);

        for (ProjectileBase obj : bullets) {
            obj.bulletSprite.draw(batch);
        }
    }
}
