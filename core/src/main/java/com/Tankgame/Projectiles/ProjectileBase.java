package com.Tankgame.Projectiles;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ProjectileBase implements Pool.Poolable {

    public Vector2 position = new Vector2();
    public boolean alive;

    public Texture bullet;
    public Sprite bulletSprite;
    public Rectangle bulletRect;

    @Override
    public void reset() {
        position.set(0, 0);
        alive = false;
    }

    public void update(float delta, Array<Rectangle> walls) {
        position.add(1*delta*60, 1*delta*60);
        if(isOutOfScreen(28, 16)) {alive=false;}
    }

    public boolean isOutOfScreen(float width, float height) {
        if (position.x < 0 || position.x > width || position.y < 0 || position.y > height) {
            return false;
        }
        return true;
    }

    public void init(float x, float y, AssetManager man, int i) {
    }
}
