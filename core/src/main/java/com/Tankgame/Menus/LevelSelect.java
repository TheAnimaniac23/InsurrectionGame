package com.Tankgame.Menus;

import com.Tankgame.Main;
import com.Tankgame.Levels.World1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class LevelSelect implements Screen {

    final Main game;

    private Texture levelBack;

    private Texture level1_1;
    private Texture level1_2;
    private Texture level1_3;
    private Texture level1_4;
    private Texture level1_5;
    private Texture level1_6;
    private Texture level1_7;
    private Texture level1_8;
    private Texture level1_9;
    private Texture level1_10;
    private Texture level1_11;
    private Texture level1_1On;
    private Texture level1_2On;
    private Texture level1_3On;
    private Texture level1_4On;
    private Texture level1_5On;
    private Texture level1_6On;
    private Texture level1_7On;
    private Texture level1_8On;
    private Texture level1_9On;
    private Texture level1_10On;
    private Texture level1_11On;

    private Sprite level1_1Sprite;
    private Sprite level1_2Sprite;
    private Sprite level1_3Sprite;
    private Sprite level1_4Sprite;
    private Sprite level1_5Sprite;
    private Sprite level1_6Sprite;
    private Sprite level1_7Sprite;
    private Sprite level1_8Sprite;
    private Sprite level1_9Sprite;
    private Sprite level1_10Sprite;
    private Sprite level1_11Sprite;

    private Rectangle level1_1Rect;
    private Rectangle level1_2Rect;
    private Rectangle level1_3Rect;
    private Rectangle level1_4Rect;
    private Rectangle level1_5Rect;
    private Rectangle level1_6Rect;
    private Rectangle level1_7Rect;
    private Rectangle level1_8Rect;
    private Rectangle level1_9Rect;
    private Rectangle level1_10Rect;
    private Rectangle level1_11Rect;
    private Rectangle mouseRect;

    public LevelSelect(final Main game) {
        this.game = game;

        levelBack = game.manager.get("Menus/LevelSelect.png", Texture.class);
        level1_1 = game.manager.get("Menus/Levels/Level1Dark.png", Texture.class);
        level1_2 = game.manager.get("Menus/Levels/Level2Dark.png", Texture.class);
        level1_3 = game.manager.get("Menus/Levels/Level3Dark.png", Texture.class);
        level1_4 = game.manager.get("Menus/Levels/Level4Dark.png", Texture.class);
        level1_5 = game.manager.get("Menus/Levels/Level5Dark.png", Texture.class);
        level1_6 = game.manager.get("Menus/Levels/Level6Dark.png", Texture.class);
        level1_7 = game.manager.get("Menus/Levels/Level7Dark.png", Texture.class);
        level1_8 = game.manager.get("Menus/Levels/Level8Dark.png", Texture.class);
        level1_9 = game.manager.get("Menus/Levels/Level9Dark.png", Texture.class);
        level1_10 = game.manager.get("Menus/Levels/Level10Dark.png", Texture.class);
        level1_11 = game.manager.get("Menus/Levels/Level11Dark.png", Texture.class);
        level1_1On = game.manager.get("Menus/Levels/Level1.png", Texture.class);
        level1_2On = game.manager.get("Menus/Levels/Level2.png", Texture.class);
        level1_3On = game.manager.get("Menus/Levels/Level3.png", Texture.class);
        level1_4On = game.manager.get("Menus/Levels/Level4.png", Texture.class);
        level1_5On = game.manager.get("Menus/Levels/Level5.png", Texture.class);
        level1_6On = game.manager.get("Menus/Levels/Level6.png", Texture.class);
        level1_7On = game.manager.get("Menus/Levels/Level7.png", Texture.class);
        level1_8On = game.manager.get("Menus/Levels/Level8.png", Texture.class);
        level1_9On = game.manager.get("Menus/Levels/Level9.png", Texture.class);
        level1_10On = game.manager.get("Menus/Levels/Level10.png", Texture.class);
        level1_11On = game.manager.get("Menus/Levels/Level11.png", Texture.class);
        levelBack = game.manager.get("Menus/LevelSelect.png", Texture.class);

        level1_1Sprite = new Sprite(level1_1);
        level1_2Sprite = new Sprite(level1_2);
        level1_3Sprite = new Sprite(level1_3);
        level1_4Sprite = new Sprite(level1_4);
        level1_5Sprite = new Sprite(level1_5);
        level1_6Sprite = new Sprite(level1_6);
        level1_7Sprite = new Sprite(level1_7);
        level1_8Sprite = new Sprite(level1_8);
        level1_9Sprite = new Sprite(level1_9);
        level1_10Sprite = new Sprite(level1_10);
        level1_11Sprite = new Sprite(level1_11);

        level1_1Sprite.setSize(1, 1);
        level1_2Sprite.setSize(1, 1);
        level1_3Sprite.setSize(1, 1);
        level1_4Sprite.setSize(1, 1);
        level1_5Sprite.setSize(1, 1);
        level1_6Sprite.setSize(1, 1);
        level1_7Sprite.setSize(1, 1);
        level1_8Sprite.setSize(1, 1);
        level1_9Sprite.setSize(1, 1);
        level1_10Sprite.setSize(1, 1);
        level1_11Sprite.setSize(1, 1);

        level1_1Sprite.setPosition(3.5f, 11.656f);
        level1_2Sprite.setPosition(5.5f, 11.656f);
        level1_3Sprite.setPosition(7.5f, 11.656f);
        level1_4Sprite.setPosition(9.5f, 11.656f);
        level1_5Sprite.setPosition(11.5f, 11.656f);
        level1_6Sprite.setPosition(13.5f, 11.656f);
        level1_7Sprite.setPosition(15.5f, 11.656f);
        level1_8Sprite.setPosition(17.5f, 11.656f);
        level1_9Sprite.setPosition(19.5f, 11.656f);
        level1_10Sprite.setPosition(21.5f, 11.656f);
        level1_11Sprite.setPosition(23.5f, 11.656f);

        mouseRect = new Rectangle();
        mouseRect.set(0, 0, 0.1f, 0.1f);

        level1_1Rect = new Rectangle();
        level1_2Rect = new Rectangle();
        level1_3Rect = new Rectangle();
        level1_4Rect = new Rectangle();
        level1_5Rect = new Rectangle();
        level1_6Rect = new Rectangle();
        level1_7Rect = new Rectangle();
        level1_8Rect = new Rectangle();
        level1_9Rect = new Rectangle();
        level1_10Rect = new Rectangle();
        level1_11Rect = new Rectangle();

        level1_1Rect.set(level1_1Sprite.getX(), level1_1Sprite.getY(), 1, 1);
        level1_2Rect.set(level1_2Sprite.getX(), level1_2Sprite.getY(), 1, 1);
        level1_3Rect.set(level1_3Sprite.getX(), level1_3Sprite.getY(), 1, 1);
        level1_4Rect.set(level1_4Sprite.getX(), level1_4Sprite.getY(), 1, 1);
        level1_5Rect.set(level1_5Sprite.getX(), level1_5Sprite.getY(), 1, 1);
        level1_6Rect.set(level1_6Sprite.getX(), level1_6Sprite.getY(), 1, 1);
        level1_7Rect.set(level1_7Sprite.getX(), level1_7Sprite.getY(), 1, 1);
        level1_8Rect.set(level1_8Sprite.getX(), level1_8Sprite.getY(), 1, 1);
        level1_9Rect.set(level1_9Sprite.getX(), level1_9Sprite.getY(), 1, 1);
        level1_10Rect.set(level1_10Sprite.getX(), level1_10Sprite.getY(), 1, 1);
        level1_11Rect.set(level1_11Sprite.getX(), level1_11Sprite.getY(), 1, 1);

        Gdx.input.setInputProcessor( new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                if (button == Input.Buttons.LEFT) {
                    if (level1_1Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level1(game));
                    } else if (level1_2Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level2(game));
                    } else if (level1_3Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level3(game));
                    } else if (level1_4Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level4(game));
                    } else if (level1_5Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level5(game));
                    } else if (level1_6Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level6(game));
                    } else if (level1_7Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level7(game));
                    } else if (level1_8Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level8(game));
                    } else if (level1_9Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level9(game));
                    } else if (level1_10Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level10(game));
                    } else if (level1_11Rect.overlaps(mouseRect)) {
                        game.setScreen(new World1.Level11(game));
                    }
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 mouseGamePos = game.viewport.unproject(mousePos);
        float mouseX = mouseGamePos.x;
        float mouseY = mouseGamePos.y;

        mouseRect.set(mouseX, mouseY, 0.1f, 0.1f);

        if (level1_1Rect.overlaps(mouseRect)) {
            level1_1Sprite.setRegion(level1_1On);
        } else {
            level1_1Sprite.setRegion(level1_1);
        }

        if (level1_2Rect.overlaps(mouseRect)) {
            level1_2Sprite.setRegion(level1_2On);
        } else {
            level1_2Sprite.setRegion(level1_2);
        }

        if (level1_3Rect.overlaps(mouseRect)) {
            level1_3Sprite.setRegion(level1_3On);
        } else {
            level1_3Sprite.setRegion(level1_3);
        }

        if (level1_4Rect.overlaps(mouseRect)) {
            level1_4Sprite.setRegion(level1_4On);
        } else {
            level1_4Sprite.setRegion(level1_4);
        }

        if (level1_5Rect.overlaps(mouseRect)) {
            level1_5Sprite.setRegion(level1_5On);
        } else {
            level1_5Sprite.setRegion(level1_5);
        }

        if (level1_6Rect.overlaps(mouseRect)) {
            level1_6Sprite.setRegion(level1_6On);
        } else {
            level1_6Sprite.setRegion(level1_6);
        }

        if (level1_7Rect.overlaps(mouseRect)) {
            level1_7Sprite.setRegion(level1_7On);
        } else {
            level1_7Sprite.setRegion(level1_7);
        }

        if (level1_8Rect.overlaps(mouseRect)) {
            level1_8Sprite.setRegion(level1_8On);
        } else {
            level1_8Sprite.setRegion(level1_8);
        }

        if (level1_9Rect.overlaps(mouseRect)) {
            level1_9Sprite.setRegion(level1_9On);
        } else {
            level1_9Sprite.setRegion(level1_9);
        }

        if (level1_10Rect.overlaps(mouseRect)) {
            level1_10Sprite.setRegion(level1_10On);
        } else {
            level1_10Sprite.setRegion(level1_10);
        }

        if (level1_11Rect.overlaps(mouseRect)) {
            level1_11Sprite.setRegion(level1_11On);
        } else {
            level1_11Sprite.setRegion(level1_11);
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.spriteBatch.begin();

        game.spriteBatch.draw(levelBack, 0, 0, 28, 16);
        level1_1Sprite.draw(game.spriteBatch);
        level1_2Sprite.draw(game.spriteBatch);
        level1_3Sprite.draw(game.spriteBatch);
        level1_4Sprite.draw(game.spriteBatch);
        level1_5Sprite.draw(game.spriteBatch);
        level1_6Sprite.draw(game.spriteBatch);
        level1_7Sprite.draw(game.spriteBatch);
        level1_8Sprite.draw(game.spriteBatch);
        level1_9Sprite.draw(game.spriteBatch);
        level1_10Sprite.draw(game.spriteBatch);
        level1_11Sprite.draw(game.spriteBatch);

        game.spriteBatch.end();
    }

    @Override
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
    public void dispose() {
        levelBack.dispose();
        level1_1.dispose();
        level1_2.dispose();
        level1_3.dispose();
        level1_4.dispose();
        level1_5.dispose();
        level1_6.dispose();
        level1_7.dispose();
        level1_8.dispose();
        level1_9.dispose();
        level1_10.dispose();
        level1_11.dispose();
        level1_1On.dispose();
        level1_2On.dispose();
        level1_3On.dispose();
        level1_4On.dispose();
        level1_5On.dispose();
        level1_6On.dispose();
        level1_7On.dispose();
        level1_8On.dispose();
        level1_9On.dispose();
        level1_10On.dispose();
        level1_11On.dispose();
    }
}
