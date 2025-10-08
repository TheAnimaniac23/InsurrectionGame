package com.Tankgame.Managers;


import com.Tankgame.Entities.EnemyBase;
import com.Tankgame.Interactables.DesWall;
import com.Tankgame.Interactables.EnemySpawner;
import com.Tankgame.Interactables.Explosive;
import com.Tankgame.Interactables.InterBase;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class InterManager {
    int[][] intergrid = {
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

    private AssetManager manager;

    public Array<InterBase> inters;

    private InterBase temp;
    public void init(int[][] tempArray, AssetManager mang) {
        intergrid = tempArray;

        manager = mang;

        inters = new Array<>();
    }

    public void create(Pool<EnemyBase> pool) {
        int xiter;
        int yiter = 0;

        for (int[] row : intergrid) {
            xiter = 0;
            for (int wall : row) {
                if (wall >= 1 && wall <= 7) {
                    inters.add(new DesWall());
                    temp = inters.get(inters.size-1);
                    temp.init(xiter, yiter, wall, manager, pool);
                } else if (wall == 8) {
                    inters.add(new Explosive());
                    temp = inters.get(inters.size-1);
                    temp.init(xiter, yiter, wall, manager, pool);
                } else if (wall == 9) {
                    inters.add(new EnemySpawner());
                    temp = inters.get(inters.size-1);
                    temp.init(xiter, yiter, wall, manager, pool);
                }
                xiter += 1;
            }
            yiter += 1;
        }
    }

    public void update() {
        for (int i =0; i<inters.size; i++) {
            InterBase obj = inters.get(i);
            if (!obj.alive) {
                inters.removeIndex(i);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (InterBase obj : inters) {
            obj.draw(batch);
        }
    }
}
