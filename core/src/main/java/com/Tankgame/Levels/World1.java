package com.Tankgame.Levels;

import com.Tankgame.Entities.EnemyBase;
import com.Tankgame.Entities.GunnerEnemy;
import com.Tankgame.Entities.Player;
import com.Tankgame.Interactables.InterBase;
import com.Tankgame.Main;
import com.Tankgame.Managers.FloorManager;
import com.Tankgame.Managers.InterManager;
import com.Tankgame.Managers.WallManager;
import com.Tankgame.Menus.LevelFail;
import com.Tankgame.PathMaths.Pathfinding;
import com.Tankgame.Projectiles.ProjectileBase;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.ScreenUtils;

public class World1 {

    public static class Level1 implements Screen {
        // level 1 is currently the test world. Once all mechanics are tested, it will be replaced
        // with an actual level.

        // these tile maps are flipped from here versus how they appear in game.
        int[][] floorMap = {
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        int[][] wallMap = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,3,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,3,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,3,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,3,3,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };

        int[][] interMap = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,9,0,0,0,0,8,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        int level = 1;
        int world = 1;

        FloorManager flooring;
        WallManager walls;
        InterManager interactibles;

        Player player;

        Array<Rectangle> hitboxes;
        Array<Rectangle> hitboxes2;
        Array<ProjectileBase> bullets;
        Array<ProjectileBase> pBullets;
        Array<EnemyBase> enemies;
        Pool<EnemyBase> enemyPool = new Pool<EnemyBase>() {
            @Override
            protected EnemyBase newObject() {
                // use RNG to pick a random enemy, but theres only one enemy right now so that code will be implemented later.
                return new GunnerEnemy();
            }
        };

        private Main game;

        public Level1(final Main game) {
            this.game = game;

            flooring = new FloorManager();
            flooring.init(floorMap, game.manager);
            flooring.create();

            walls = new WallManager();
            walls.init(wallMap, game.manager);
            walls.create();

            interactibles = new InterManager();
            interactibles.init(interMap, game.manager);
            interactibles.create(enemyPool);
            hitboxes = new Array<>();
            hitboxes2 = new Array<>();
            bullets = new Array<>();
            pBullets = new Array<>();

            enemies = new Array<>();

            player = new Player();
            player.Player(3, 3, game.manager);

        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {
            player.input(game);
        }

        private void logic() {
            if (player.health <= 0) {
                game.setScreen(new LevelFail(game, level, world));
            }

            hitboxes.clear();
            hitboxes2.clear();
            bullets.clear();
            pBullets.clear();

            interactibles.update();
            hitboxes.addAll(walls.wallRects);
            for (InterBase obj : interactibles.inters) {
                hitboxes.add(obj.wallRect);
            }
            for (EnemyBase enemy : enemies) {
                hitboxes.add(enemy.chassisRect);
            }

            bullets.addAll(player.activeBullets);
            for (InterBase obj : interactibles.inters) {
                try {
                    bullets.addAll(obj.bullets);
                    pBullets.addAll(obj.bullets);
                } catch (NullPointerException e) {
                    continue;
                }
            }

            hitboxes2.addAll(hitboxes);
            hitboxes2.add(player.chassisRect);

            for (InterBase obj : interactibles.inters) {
                obj.update(bullets, hitboxes2, enemies);
            }

            Pathfinding pather = new Pathfinding();
            int[][] map = pather.simplifyMap(wallMap, interMap);

            for (EnemyBase enemy : enemies) {
                enemy.update(hitboxes2, bullets, map, new float[] {player.chassisRect.getX(), player.chassisRect.getY()});
                if (!enemy.alive) {
                    enemies.removeValue(enemy, true);
                    enemyPool.free(enemy);
                }
            }

            player.logic(hitboxes, pBullets);

            for (InterBase obj : interactibles.inters) {
                obj.update2();
            }
        }

        private void draw() {
            ScreenUtils.clear(Color.BLACK);

            game.viewport.apply();
            game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

            game.spriteBatch.begin();

            flooring.draw(game.spriteBatch);
            walls.draw(game.spriteBatch);
            interactibles.draw(game.spriteBatch);

            for (EnemyBase enemy : enemies) {
                enemy.draw(game.spriteBatch);
            }

            player.draw(game.spriteBatch);
            player.draw2(game.spriteBatch);

            game.spriteBatch.end();
        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level2 implements Screen {

        private Main game;

        public Level2(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level3 implements Screen {

        private Main game;

        public Level3(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level4 implements Screen {

        private Main game;

        public Level4(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level5 implements Screen {

        private Main game;

        public Level5(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level6 implements Screen {

        private Main game;

        public Level6(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level7 implements Screen {

        private Main game;

        public Level7(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level8 implements Screen {

        private Main game;

        public Level8(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level9 implements Screen {

        private Main game;

        public Level9(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level10 implements Screen {

        private Main game;

        public Level10(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }

    public static class Level11 implements Screen {

        private Main game;

        public Level11(final Main game) {
            this.game = game;
        }

        @Override
        public void render(float delta) {
            input();
            logic();
            draw();
        }

        private void input() {

        }

        private void logic() {

        }

        private void draw() {

        }

        public void resize(int width, int height) {
            game.viewport.update(width, height, true);
        }

        @Override
        public void show() {}

        @Override
        public void pause() {}

        @Override
        public void resume() {}

        @Override
        public void hide() {}

        @Override
        public void dispose() {}
    }
}
