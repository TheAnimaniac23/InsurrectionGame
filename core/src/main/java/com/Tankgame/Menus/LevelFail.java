package com.Tankgame.Menus;

import com.Tankgame.Levels.World1;
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

public class LevelFail implements Screen {
    final Main game;
    private int level;

    private Texture backdropT;
    private Texture RestartT;
    private Texture RestartDT;
    private Texture MenuT;
    private Texture MenuDT;
    private Texture LevelT;
    private Texture LevelDT;

    private Sprite RestartButton;
    private Sprite MenuButton;
    private Sprite LevelButton;

    private Rectangle RestartRect;
    private Rectangle MenuRect;
    private Rectangle LevelRect;
    private Rectangle mouseRect;

    public LevelFail(final Main game, int level, int world) {
        this.game = game;
        this.level = level;

        backdropT = game.manager.get("Menus/LevelFailMenu.png", Texture.class);
        RestartT = game.manager.get("Menus/RestartButton.png", Texture.class);
        RestartDT = game.manager.get("Menus/RestartButtonDark.png", Texture.class);
        MenuT = game.manager.get("Menus/MainMenuButton.png", Texture.class);
        MenuDT = game.manager.get("Menus/MainMenuButtonDark.png", Texture.class);
        LevelT = game.manager.get("Menus/LevelMenuButton.png", Texture.class);
        LevelDT = game.manager.get("Menus/LevelMenuButtonDark.png", Texture.class);

        RestartButton = new Sprite(RestartDT);
        RestartButton.setPosition(11.78125f, 8.25f);
        RestartButton.setSize(4.46875f, 1.5f);
        RestartRect = new Rectangle();
        RestartRect.set(RestartButton.getX(), RestartButton.getY(), RestartButton.getWidth(), RestartButton.getHeight());

        MenuButton = new Sprite(MenuDT);
        MenuButton.setPosition(11.6875f, 6.125f);
        MenuButton.setSize(4.65625f, 1.5f);
        MenuRect = new Rectangle();
        MenuRect.set(MenuButton.getX(), MenuButton.getY(), MenuButton.getWidth(), MenuButton.getHeight());

        LevelButton = new Sprite(LevelDT);
        LevelButton.setPosition(11.25f, 4);
        LevelButton.setSize(5.53125f, 1.5f);
        LevelRect = new Rectangle();
        LevelRect.set(LevelButton.getX(), LevelButton.getY(), LevelButton.getWidth(), LevelButton.getHeight());

        mouseRect = new Rectangle();

        Gdx.input.setInputProcessor( new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {

                if (button == Input.Buttons.LEFT) {
                    if (RestartRect.overlaps(mouseRect)) {
                        Restart(level, world);
                    } else if (MenuRect.overlaps(mouseRect)) {
                        game.setScreen( new MainMenu(game));
                    } else if (LevelRect.overlaps(mouseRect)) {
                        game.setScreen( new LevelSelect(game));
                    }
                }

                return true;
            }
        });
    }

    private void Restart(int level, int world) {
        if (world == 1) {
            if (level == 1) {
                game.setScreen(new World1.Level1(game));
            } else if (level == 2) {
                game.setScreen(new World1.Level2(game));
            } else if (level == 3) {
                game.setScreen(new World1.Level3(game));
            } else if (level == 4) {
                game.setScreen(new World1.Level4(game));
            } else if (level == 5) {
                game.setScreen(new World1.Level5(game));
            } else if (level == 6) {
                game.setScreen(new World1.Level6(game));
            } else if (level == 7) {
                game.setScreen(new World1.Level7(game));
            } else if (level == 8) {
                game.setScreen(new World1.Level8(game));
            } else if (level == 9) {
                game.setScreen(new World1.Level9(game));
            } else if (level == 10) {
                game.setScreen(new World1.Level10(game));
            } else if (level == 11) {
                game.setScreen(new World1.Level11(game));
            }
        } else if (world == 2) {
            System.out.println("filler");
        } else if (world == 3) {
            System.out.println("filler");
        } else if (world == 4) {
            System.out.println("filler");
        } else if (world == 5) {
            System.out.println("filler");
        } else if (world == 6) {
            System.out.println("filler");
        }
    }

    @Override
    public void show() {

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
    }

    private void logic() {
        if (RestartRect.overlaps(mouseRect)) {
            RestartButton.setRegion(RestartT);
        } else {
            RestartButton.setRegion(RestartDT);
        }

        if (MenuRect.overlaps(mouseRect)) {
            MenuButton.setRegion(MenuT);
        } else {
            MenuButton.setRegion(MenuDT);
        }

        if (LevelRect.overlaps(mouseRect)) {
            LevelButton.setRegion(LevelT);
        } else {
            LevelButton.setRegion(LevelDT);
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.spriteBatch.begin();

        game.spriteBatch.draw(backdropT, 0 ,0, 28, 16);
        RestartButton.draw(game.spriteBatch);
        MenuButton.draw(game.spriteBatch);
        LevelButton.draw(game.spriteBatch);

        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backdropT.dispose();
        RestartT.dispose();
        RestartDT.dispose();
        MenuT.dispose();
        MenuDT.dispose();
        LevelT.dispose();
        LevelDT.dispose();
    }
}
