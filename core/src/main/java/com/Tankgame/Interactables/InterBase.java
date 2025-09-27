package com.Tankgame.Interactables;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InterBase {
    public Texture wall;
    public Sprite wallSprite;
    public Rectangle wallRect;

    public void init(int spawnx, int spawny, int variant, AssetManager manager) {}
    public void draw(SpriteBatch batch) {
        wallSprite.draw(batch);
    }
}

