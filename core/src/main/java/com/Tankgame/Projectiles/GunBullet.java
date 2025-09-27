package com.Tankgame.Projectiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GunBullet extends ProjectileBase {

    int life;
    float speed = 0.1f;

    @Override
    public void init(float x, float y, AssetManager manager, int lifeT) {
        bullet = manager.get("Entities/Projectiles/Bullet.png", Texture.class);
        bulletSprite = new Sprite(bullet);
        life = lifeT;
        alive = true;
        position.set(x, y);
    }

    @Override
    public void update(float delta, Array<Rectangle> walls) {
        life -= 1;
        if (life <= 0 || isOutOfScreen(28, 16)) {
            alive = false;
            reset();
        } else {
            position.add(1*delta*speed, 1*delta*speed);
            bulletSprite.setPosition(position.x, position.y);
        }
    }
}
