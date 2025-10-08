package com.Tankgame.Menus;

import com.Tankgame.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;

public class InitiLoad implements Screen {

    final Main game;

    private Texture loadBarText;
    private Texture loadBarBText;

    private Sprite loadBarSprite;
    private Sprite loadBarBack;
    private Texture logo;

    public InitiLoad(final Main game) {
        this.game = game;

        // the group of assets to be loaded
        game.manager.load("Menus/InsurrectionLogo.png", Texture.class);
        game.manager.load("Menus/LoadBar/LoadBar0.png", Texture.class);
        game.manager.load("Menus/LoadBar/LoadBar1.png", Texture.class);
        game.manager.load("Menus/Levels/Level1.png", Texture.class);
        game.manager.load("Menus/Levels/Level2.png", Texture.class);
        game.manager.load("Menus/Levels/Level3.png", Texture.class);
        game.manager.load("Menus/Levels/Level4.png", Texture.class);
        game.manager.load("Menus/Levels/Level5.png", Texture.class);
        game.manager.load("Menus/Levels/Level6.png", Texture.class);
        game.manager.load("Menus/Levels/Level7.png", Texture.class);
        game.manager.load("Menus/Levels/Level8.png", Texture.class);
        game.manager.load("Menus/Levels/Level9.png", Texture.class);
        game.manager.load("Menus/Levels/Level10.png", Texture.class);
        game.manager.load("Menus/Levels/Level11.png", Texture.class);
        game.manager.load("Menus/Levels/Level1Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level2Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level3Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level4Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level5Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level6Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level7Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level8Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level9Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level10Dark.png", Texture.class);
        game.manager.load("Menus/Levels/Level11Dark.png", Texture.class);
        game.manager.load("Menus/LevelSelect.png", Texture.class);
        game.manager.load("Menus/MainMenu.png", Texture.class);
        game.manager.load("Menus/StartButton.png", Texture.class);
        game.manager.load("Menus/StartButtonDark.png", Texture.class);
        game.manager.load("Menus/LevelFailMenu.png", Texture.class);
        game.manager.load("Menus/LevelMenuButton.png", Texture.class);
        game.manager.load("Menus/LevelMenuButtonDark.png", Texture.class);
        game.manager.load("Menus/MainMenuButton.png", Texture.class);
        game.manager.load("Menus/MainMenuButtonDark.png", Texture.class);
        game.manager.load("Menus/RestartButton.png", Texture.class);
        game.manager.load("Menus/RestartButtonDark.png", Texture.class);
        game.manager.load("Floor/ConcreteFloor1.png", Texture.class);
        game.manager.load("Floor/ConcreteFloor2.png", Texture.class);
        game.manager.load("Floor/SteelFloor.png", Texture.class);
        game.manager.load("Floor/WoodFloor.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner0.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner1.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner2.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner3.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner4.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner5.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner6.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner7.png", Texture.class);
        game.manager.load("Tech/Spawner/EnemySpawner8.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner0.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner1.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner2.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner3.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner4.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner5.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner6.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner7.png", Texture.class);
        game.manager.load("Tech/Spawner/Spawner8.png", Texture.class);
        game.manager.load("Tech/ButtonOff.png", Texture.class);
        game.manager.load("Tech/ButtonOn.png", Texture.class);
        game.manager.load("Tech/EnergyWallCenter.png", Texture.class);
        game.manager.load("Tech/EnergyWallEnd.png", Texture.class);
        game.manager.load("Tech/EnergyWallDownOn.png", Texture.class);
        game.manager.load("Tech/EnergyWallDownOff.png", Texture.class);
        game.manager.load("Tech/EnergyWallUpOn.png", Texture.class);
        game.manager.load("Tech/EnergyWallUpOff.png", Texture.class);
        game.manager.load("Tech/EnergyWallLeftOn.png", Texture.class);
        game.manager.load("Tech/EnergyWallLeftOff.png", Texture.class);
        game.manager.load("Tech/EnergyWallRightOn.png", Texture.class);
        game.manager.load("Tech/EnergyWallRightOff.png", Texture.class);
        game.manager.load("Walls/WoodWall.png", Texture.class);
        game.manager.load("Walls/BlueWall1.png", Texture.class);
        game.manager.load("Walls/BlueWall2.png", Texture.class);
        game.manager.load("Walls/CyanWall1.png", Texture.class);
        game.manager.load("Walls/CyanWall2.png", Texture.class);
        game.manager.load("Walls/GreenWall1.png", Texture.class);
        game.manager.load("Walls/GreenWall2.png", Texture.class);
        game.manager.load("Walls/PurpleWall1.png", Texture.class);
        game.manager.load("Walls/PurpleWall2.png", Texture.class);
        game.manager.load("Walls/RedWall1.png", Texture.class);
        game.manager.load("Walls/RedWall2.png", Texture.class);
        game.manager.load("Walls/YellowWall1.png", Texture.class);
        game.manager.load("Walls/YellowWall2.png", Texture.class);
        game.manager.load("Misc/AshMark.png", Texture.class);
        game.manager.load("Misc/Crate.png", Texture.class);
        game.manager.load("Misc/ExplosiveCrate.png", Texture.class);
        game.manager.load("Entities/Player/PlayerChassisStatic.png", Texture.class);
        game.manager.load("Entities/Player/PlayerTurretMG.png", Texture.class);
        game.manager.load("Entities/Projectiles/Bullet.png", Texture.class);
        game.manager.load("Entities/Projectiles/FireBall.png", Texture.class);
        game.manager.load("Entities/Enemies/ArmoredChassisStaticCS.png", Texture.class);
        game.manager.load("Entities/Enemies/ArmoredChassisStaticFT.png", Texture.class);
        game.manager.load("Entities/Enemies/ArmoredChassisStaticMG.png", Texture.class);
        game.manager.load("Entities/Enemies/ArmoredChassisStaticRG.png", Texture.class);
        game.manager.load("Entities/Enemies/ArmoredChassisStaticSG.png", Texture.class);
        game.manager.load("Entities/Enemies/BossChassis.png", Texture.class);
        game.manager.load("Entities/Enemies/BossTurretMG.png", Texture.class);
        game.manager.load("Entities/Enemies/BossTurretRG.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticCS.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticFT.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticMG.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticML.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticRG.png", Texture.class);
        game.manager.load("Entities/Enemies/ChassisStaticSG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretChassisCS.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretChassisFT.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretChassisMG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretChassisRG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretChassisSG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretCS.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretFT.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretMG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretML.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretRG.png", Texture.class);
        game.manager.load("Entities/Enemies/TurretSG.png", Texture.class);
        game.manager.load("GUI/HealthBars/EnemyHealthBar0.png", Texture.class);
        game.manager.load("GUI/HealthBars/EnemyHealthBar1.png", Texture.class);
        game.manager.load("GUI/HealthBars/PlayerHealthBar0.png", Texture.class);
        game.manager.load("GUI/HealthBars/PlayerHealthBar1.png", Texture.class);
    }

    @Override
    public void render(float delta) {
        if (game.manager.update()) {
            game.setScreen(new MainMenu(game));
        }


        if (game.manager.isLoaded("Menus/InsurrectionLogo.png")) {
            logo = game.manager.get("Menus/InsurrectionLogo.png");
        }

        float progress = game.manager.getProgress();
        logic(progress);
        draw();
    }

    public void logic(float progress) {
        if (game.manager.isLoaded("Menus/LoadBar/LoadBar0.png")) {
            loadBarBText = game.manager.get("Menus/LoadBar/LoadBar0.png");
        }
        if (game.manager.isLoaded("Menus/LoadBar/LoadBar1.png")) {
            loadBarText = game.manager.get("Menus/LoadBar/LoadBar1.png");
        }

        float width = 6.5f*progress;

        // current issue: texture being squashed instead of cropped when rescaling loading bar for the project.

        if (loadBarText != null) {
            loadBarSprite = new Sprite();
            loadBarSprite.setRegion(loadBarText);
            loadBarSprite.setX(10.75f);
            loadBarSprite.setY(6.75f);
            loadBarSprite.setSize(width, 0.5f);
        }

        if (loadBarBText != null) {
            loadBarBack = new Sprite();
            loadBarBack.setRegion(loadBarBText);
            loadBarBack.setX(10.75f);
            loadBarBack.setY(6.75f);
            loadBarBack.setSize(6.5f, 0.5f);
        }
    }

    public void draw() {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.spriteBatch.begin();

        if (game.manager.isLoaded("Menus/InsurrectionLogo.png")) {
            game.spriteBatch.draw(logo, 7.375f, 13.7f, 13.25f, 1.6f);
        }

        if (loadBarBack != null) {
            loadBarBack.draw(game.spriteBatch);
        }
        if (loadBarSprite != null) {
            loadBarSprite.draw(game.spriteBatch);
        }

        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void show() {

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
        logo.dispose();
    }
}
