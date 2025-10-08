package com.Tankgame.Projectiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class FireBall extends ProjectileBase {

    private Random rng = new Random();
    private float min = 0.375f;
    private float max = 0.75f;

    int life;
    int immune;
    float rotation;
    float speed = 0.1f;

    @Override
    public void init(float x, float y, AssetManager man, int lifet, float rot) {
        bullet = man.get("Entities/Projectiles/FireBall.png", Texture.class);

        bulletSprite = new Sprite(bullet);
        float size = rng.nextFloat(max-min+1) + min;
        bulletSprite.setSize(size, size);
        bulletSprite.setOrigin(size/2, size/2);
        bulletSprite.setPosition(x, y);
        int tarRot = rng.nextInt(361);
        bulletSprite.setRotation(tarRot);

        bulletRect = new Rectangle();
        bulletRect.set(x, y, size, size);

        life = lifet;
        alive = true;
        damage = 1;
        rotation = rot;
        immune = 2;

        position.set(x-bulletSprite.getWidth()/2, y-bulletSprite.getHeight()/2);
    }

    @Override
    public void update(float delta, Array<Rectangle> walls) {
        life -= 1;
        immune -= 1;

        boolean collision = false;

        for (Rectangle wall : walls) {
            if (bulletRect.overlaps(wall) && immune <= 0) {
                collision = true;
            }
        }

        if (life <= 0 || isOutOfScreen(28, 16) || collision) {
            alive = false;
            reset();
        } else {
            float speedX = speed * (float) Math.toDegrees(Math.cos(Math.toRadians(rotation)));
            float speedY = speed * (float) Math.toDegrees(Math.sin(Math.toRadians(rotation)));
            position.add(delta*speedX, delta*speedY);
            bulletSprite.setPosition(position.x, position.y);
        }

        bulletRect.set(bulletSprite.getX(), bulletSprite.getY(), bulletSprite.getWidth(), bulletSprite.getHeight());
    }
}
