package com.Tankgame.Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class WallManager {

    // generates the walls, 0 means no wall, 1-6 create walls of different colors

    int[][] wallgrid = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    Texture redWall;
    Texture blueWall;
    Texture greenWall;
    Texture cyanWall;
    Texture magentaWall;
    Texture yellowWall;

    Sprite wallSprite;
    Rectangle wallRect;
    public Array<Sprite> wallSprites;
    public Array<Rectangle> wallRects;

    public void init(int[][] tempArray, AssetManager manager) {
        redWall = manager.get("Walls/RedWall1.png", Texture.class);
        blueWall = manager.get("Walls/BlueWall1.png", Texture.class);
        greenWall = manager.get("Walls/GreenWall1.png", Texture.class);
        cyanWall = manager.get("Walls/CyanWall1.png", Texture.class);
        magentaWall = manager.get("Walls/PurpleWall1.png", Texture.class);
        yellowWall = manager.get("Walls/YellowWall1.png", Texture.class);

        wallgrid = tempArray;

        wallSprites = new Array<>();
        wallRects = new Array<>();
    }

    public void create() {
        float tileWidth = 1;
        float tileHeight = 1;
        int xiter;
        int yiter = 0;

        for (int[] row : wallgrid) {
            xiter = 0;
            for (int wall : row) {
                if (wall != 0) {
                    wallSprite = new Sprite();
                    wallRect = new Rectangle();

                    if (wall == 1) {
                        wallSprite.setRegion(redWall);
                    } else if (wall == 2) {
                        wallSprite.setRegion(yellowWall);
                    } else if (wall == 3) {
                        wallSprite.setRegion(greenWall);
                    } else if (wall == 4) {
                        wallSprite.setRegion(cyanWall);
                    } else if (wall == 5) {
                        wallSprite.setRegion(blueWall);
                    } else if (wall == 6) {
                        wallSprite.setRegion(magentaWall);
                    }

                    wallSprite.setSize(tileWidth, tileHeight);
                    wallSprite.setX(xiter);
                    wallSprite.setY(yiter);

                    wallRect.set(xiter, yiter, tileWidth, tileHeight);

                    wallRects.add(wallRect);
                    wallSprites.add(wallSprite);
                }

                xiter += 1;

            }

            yiter += 1;
        }
    }

    public void draw(SpriteBatch batch) {
        for (Sprite sprite : wallSprites) {
            sprite.draw(batch);
        }
    }

}
