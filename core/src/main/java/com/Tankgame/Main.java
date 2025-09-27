package com.Tankgame;

import com.Tankgame.Menus.InitiLoad;
import com.Tankgame.Menus.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends Game {

    public SpriteBatch spriteBatch;
    public FitViewport viewport;
    public AssetManager manager;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(28, 16);
        manager = new AssetManager();

        this.setScreen(new InitiLoad(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        spriteBatch.dispose();
    }

}
