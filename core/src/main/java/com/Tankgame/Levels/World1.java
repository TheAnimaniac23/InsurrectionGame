package com.Tankgame.Levels;

import com.Tankgame.Entities.Player;
import com.Tankgame.Interactables.InterBase;
import com.Tankgame.Main;
import com.Tankgame.Managers.FloorManager;
import com.Tankgame.Managers.InterManager;
import com.Tankgame.Managers.WallManager;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
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
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        FloorManager flooring;
        WallManager walls;
        InterManager interactibles;

        Player player;

        Array<Rectangle> hitboxes;

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
            interactibles.create();
            hitboxes = new Array<>();

            hitboxes.addAll(walls.wallRects);
            for (InterBase obj : interactibles.inters) {
                hitboxes.add(obj.wallRect);
            }

            player = new Player();
            player.Player(3, 3, game.manager, hitboxes);

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
            player.logic();
        }

        private void draw() {
            ScreenUtils.clear(Color.BLACK);

            game.viewport.apply();
            game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

            game.spriteBatch.begin();

            flooring.draw(game.spriteBatch);
            walls.draw(game.spriteBatch);
            interactibles.draw(game.spriteBatch);
            player.draw(game.spriteBatch);

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
