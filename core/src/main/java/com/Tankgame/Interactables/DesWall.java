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

public class DesWall extends InterBase {

    public int visCool;
    public boolean visible;

    @Override
    public void init(int spawnx, int spawny, int variant, AssetManager man, Pool<EnemyBase> epool) {
        // variants: 1 = Wooden, 2 = Red, 3 = Yellow, 4 = Green, 5 = Cyan, 6 = Blue, 7 = Magenta

        healthT = man.get("GUI/HealthBars/EnemyHealthBar0.png", Texture.class);
        healthTBack = man.get("GUI/HealthBars/EnemyHealthBar1.png", Texture.class);

        if (variant == 1) {
            wall = man.get("Walls/WoodWall.png", Texture.class);
        } else if (variant == 2) {
            wall = man.get("Walls/RedWall2.png", Texture.class);
        } else if (variant == 3) {
            wall = man.get("Walls/YellowWall2.png", Texture.class);
        } else if (variant == 4) {
            wall = man.get("Walls/GreenWall2.png", Texture.class);
        } else if (variant == 5) {
            wall = man.get("Walls/CyanWall2.png", Texture.class);
        } else if (variant == 6) {
            wall = man.get("Walls/BlueWall2.png", Texture.class);
        } else if (variant == 7) {
            wall = man.get("Walls/PurpleWall2.png", Texture.class);
        }

        manager = man;

        wallSprite = new Sprite(wall);
        wallRect = new Rectangle();
        healthSprite = new Sprite(healthT);
        healthSpriteBack = new Sprite(healthTBack);

        wallSprite.setSize(1, 1);
        wallSprite.setPosition(spawnx, spawny);
        wallRect.set(spawnx, spawny, 1, 1);
        healthSprite.setSize(1, 0.25f);
        healthSpriteBack.setSize(1, 0.25f);
        healthSprite.setPosition(spawnx, spawny+1.0625f);
        healthSpriteBack.setPosition(spawnx, spawny+1.0625f);

        health = 10;
        healthTotal = 10;
        alive = true;
    }

    @Override
    public void update(Array<ProjectileBase> hitboxes, Array<Rectangle> walls, Array<EnemyBase>  enemies) {
        int tempH = health;

        for (ProjectileBase obj : hitboxes) {
            if (wallRect.overlaps(obj.bulletRect)) {
                if (health > 1) {
                    tempH = health - obj.damage;
                } else {
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
            healthSpriteBack.setAlpha(opac);
        } else {
            healthSpriteBack.setAlpha(1);
            healthSprite.setAlpha(1);
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
