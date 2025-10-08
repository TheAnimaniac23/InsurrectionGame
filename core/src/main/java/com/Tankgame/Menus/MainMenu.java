package com.Tankgame.Menus;

import com.Tankgame.Main;
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
import com.badlogic.gdx.Input.Keys;

public class MainMenu implements Screen {
    final Main game;

    private Texture mainMenuBack;

    private Texture startButton;
    private Texture startButtonPress;
    private Sprite startSprite;
    private Rectangle startRect;
    private Rectangle mouseRect;

    public MainMenu(final Main game) {
        this.game = game;

        mainMenuBack = game.manager.get("Menus/MainMenu.png", Texture.class);
        startButton = game.manager.get("Menus/StartButtonDark.png", Texture.class);
        startButtonPress = game.manager.get("Menus/StartButton.png", Texture.class);

        startSprite = new Sprite(startButton);
        startSprite.setSize(3.5f, 1.5f);
        startSprite.setX(12.25f);
        startSprite.setY(7.25f);

        startRect = new Rectangle();
        startRect.set(startSprite.getX(), startSprite.getY(), 3.5f, 1.5f);

        mouseRect = new Rectangle();
        mouseRect.set(0, 0, 0.1f, 0.1f);

        Gdx.input.setInputProcessor( new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                if (button == Input.Buttons.LEFT && startRect.overlaps(mouseRect)) {
                    game.setScreen(new LevelSelect(game));
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
        // used to get the mouse position, and edit button sprites to match, as well as detect the
        // depressing of buttons.

        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 mouseGamePos = game.viewport.unproject(mousePos);
        float mouseX = mouseGamePos.x;
        float mouseY = mouseGamePos.y;

        mouseRect.set(mouseX, mouseY, 0.1f, 0.1f);
    }

    private void logic() {
        if (startRect.overlaps(mouseRect)) {
            startSprite.setRegion(startButtonPress);
        } else {
            startSprite.setRegion(startButton);
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.spriteBatch.begin();

        game.spriteBatch.draw(mainMenuBack, 0, 0, 28, 16);
        startSprite.draw(game.spriteBatch);

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
        mainMenuBack.dispose();
        startButton.dispose();
        startButtonPress.dispose();
    }

}
