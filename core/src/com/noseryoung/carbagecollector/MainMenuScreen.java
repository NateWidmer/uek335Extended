package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen implements Screen {
    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    TextureAtlas atlas;
    Skin skin;
    Game parent;

    public MainMenuScreen(Game parent) {
        this.parent = parent;

        atlas = new TextureAtlas("skin/vhs-ui.atlas");
        skin = new Skin(Gdx.files.internal("skin/vhs-ui.json"));

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create Labels / Images / Buttons

        Label title = new Label("Garbage Collector", skin);
        Label poweredBy = new Label("Powered By", skin);
        Image logo = new Image(new Texture("tomatoBagLogoWhite.png"));
        logo.setSize(75, 75);
        Image garbageCollectorHome = new Image(new Texture("garbageCollectorSide.png"));
        garbageCollectorHome.setSize(255, 156);
        title.setFontScale(2);

        TextButton playButton = new TextButton("Play", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                parent.setScreen(new GameScreen(parent));
            }
        });
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Create Menu Table

        Table menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.center().padBottom(200);

        menuTable.add(title);
        menuTable.row().padTop(100);
        menuTable.add(garbageCollectorHome).size(garbageCollectorHome.getWidth(), garbageCollectorHome.getHeight());
        menuTable.row().padTop(100);
        menuTable.add(playButton).padBottom(20);
        menuTable.row();
        menuTable.add(exitButton);

        // Create Powered By Table

        Table poweredByTable = new Table();
        poweredByTable.setFillParent(true);
        poweredByTable.right().bottom();
        poweredByTable.padBottom(30).padRight(30);

        poweredByTable.add(poweredBy).padRight(20);
        poweredByTable.add(logo).size(logo.getWidth(), logo.getHeight());

        // Add Tables as Actors on Stage

        stage.addActor(menuTable);
        stage.addActor(poweredByTable);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(31/255f, 0/255f, 41/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
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
        skin.dispose();
        atlas.dispose();
        stage.dispose();
        batch.dispose();
    }
}
