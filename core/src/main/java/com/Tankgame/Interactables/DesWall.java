package com.Tankgame.Interactables;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class DesWall extends InterBase {
    @Override
    public void init(int spawnx, int spawny, int variant, AssetManager manager) {
        // variants: 1 = Wooden, 2 = Red, 3 = Yellow, 4 = Green, 5 = Cyan, 6 = Blue, 7 = Magenta

        if (variant == 1) {
            wall = manager.get("Walls/WoodWall.png", Texture.class);
        } else if (variant == 2) {
            wall = manager.get("Walls/RedWall2.png", Texture.class);
        } else if (variant == 3) {
            wall = manager.get("Walls/YellowWall2.png", Texture.class);
        } else if (variant == 4) {
            wall = manager.get("Walls/GreenWall2.png", Texture.class);
        } else if (variant == 5) {
            wall = manager.get("Walls/CyanWall2.png", Texture.class);
        } else if (variant == 6) {
            wall = manager.get("Walls/BlueWall2.png", Texture.class);
        } else if (variant == 7) {
            wall = manager.get("Walls/PurpleWall2.png", Texture.class);
        }

        wallSprite = new Sprite(wall);
        wallRect = new Rectangle();

        wallSprite.setSize(1, 1);
        wallSprite.setPosition(spawnx, spawny);
        wallRect.set(spawnx, spawny, 1, 1);
    }
}
