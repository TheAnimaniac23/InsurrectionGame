package com.Tankgame.Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class FloorManager {

    int[][] floorgrid = {
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

    Texture concrete1;
    Texture concrete2;
    Texture steel;
    Texture wood;

    Sprite floorSprite;
    public Array<Sprite> floorSprites;

    public void init(int[][] tempArray, AssetManager manager) {
        concrete1 = manager.get("Floor/ConcreteFloor1.png", Texture.class);
        concrete2 = manager.get("Floor/ConcreteFloor2.png", Texture.class);
        steel = manager.get("Floor/SteelFloor.png", Texture.class);
        wood = manager.get("Floor/WoodFloor.png", Texture.class);

        floorgrid = tempArray;

        floorSprites = new Array<>();
    }

    public void create() {
        // code to initialize and store floor information, such as
        // variants, rotation, and size. Floor size default will be 16 by
        // 16, meaning that there will be 256 floor tiles.

        float floorWidth = 1;
        float floorHeight = 1;
        int xiter;
        int yiter = 0;

        // determining floor type. rotation is randomized each run.
        for (int[] row : floorgrid) {
            xiter = 0;
            for (int item : row) {

                floorSprite = new Sprite();

                if (item == 0) {
                    // concrete floor type. randomized between normal and cracked.
                    int[] concreteRand = {1,0,0,0,0,0,0,0,0,0};
                    int randIndex = MathUtils.random(0, concreteRand.length-1);
                    int floorType = concreteRand[randIndex];

                    if (floorType == 0) {
                        floorSprite.setRegion(concrete2);
                    } else {
                        floorSprite.setRegion(concrete1);
                    }

                } else if (item == 1) {
                    // steel floor type.
                    floorSprite.setRegion(steel);
                } else if (item == 2) {
                    // wood floor type.
                    floorSprite.setRegion(wood);
                }

                float[] rotations = {0, 90, 180, 270};
                int randRot = MathUtils.random(0, rotations.length-1);
                float rotation = rotations[randRot];

                floorSprite.setSize(floorWidth, floorHeight);
                floorSprite.setX(xiter);
                floorSprite.setY(yiter);
                floorSprite.setOriginCenter();
                floorSprite.rotate(rotation);
                floorSprites.add(floorSprite);

                xiter += 1;
            }

            yiter += 1;
        }
    }

    public void draw(SpriteBatch batch) {
        for (Sprite sprite : floorSprites) {
            sprite.draw(batch);
        }
    }

}
