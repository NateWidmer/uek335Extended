package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    SpriteBatch batch;
    Stage stage;
    Viewport viewport;
    OrthographicCamera camera;
    TextureAtlas atlas;
    Skin skin;
    Game parent;
    ShapeRenderer shapeRenderer;
    private int score;
    private Label scoreCountLabel;
    private Sprite background;
    private final Texture backgroundImg;
    private GarbageCollector garbageCollector;
    private Garbage garbage;

    public GameScreen(Game parent) {
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

        shapeRenderer = new ShapeRenderer();

        backgroundImg = new Texture("background.png");
        background = new Sprite(backgroundImg);
        background.scale(2.2f);
        background.setX(Gdx.graphics.getWidth() / 2 - background.getWidth() / 2);
        background.setY(1100);

        garbageCollector = new GarbageCollector();
        garbage = new Garbage(890, 100, 1590, 800);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Label score = new Label("Score: ", skin);
        scoreCountLabel = new Label("0", skin);

        Drawable drawableUp = new TextureRegionDrawable(new TextureRegion(new Texture("Sprites/buttons/shadedLight26.png")));
        drawableUp.setMinHeight(150);
        drawableUp.setMinWidth(150);
        ImageButton upControl = new ImageButton(drawableUp);

        Drawable drawableDown = new TextureRegionDrawable(new TextureRegion(new Texture("Sprites/buttons/shadedLight27.png")));
        drawableDown.setMinHeight(150);
        drawableDown.setMinWidth(150);
        ImageButton downControl = new ImageButton(drawableDown);

        Drawable drawableLeft = new TextureRegionDrawable(new TextureRegion(new Texture("Sprites/buttons/shadedLight24.png")));
        drawableLeft.setMinHeight(150);
        drawableLeft.setMinWidth(150);
        ImageButton leftControl = new ImageButton(drawableLeft);

        Drawable drawableRight = new TextureRegionDrawable(new TextureRegion(new Texture("Sprites/buttons/shadedLight25.png")));
        drawableRight.setMinHeight(150);
        drawableRight.setMinWidth(150);
        ImageButton rightControl = new ImageButton(drawableRight);

        upControl.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                garbageCollector.setRotation(0);
                garbageCollector.rotateWagons(0);
            }
        });

        downControl.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                garbageCollector.setRotation(180);
                garbageCollector.rotateWagons(180);

            }
        });

        leftControl.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                garbageCollector.setRotation(90);
                garbageCollector.rotateWagons(90);

            }
        });

        rightControl.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                garbageCollector.setRotation(270);
                garbageCollector.rotateWagons(270);

            }
        });

        Table scoreTable = new Table();
        scoreTable.setFillParent(true);
        scoreTable.padTop(300);

        scoreTable.add(score);
        scoreTable.add(scoreCountLabel);

        Table controlTableUpDown = new Table();
        controlTableUpDown.setFillParent(true);
        controlTableUpDown.right().padRight(150);
        controlTableUpDown.bottom().padBottom(225);
        controlTableUpDown.add(upControl);
        controlTableUpDown.row().pad(40, 0, 40, 0);
        controlTableUpDown.add(downControl);

        Table controlTableLeftRight = new Table();
        controlTableLeftRight.left().padLeft(150);
        controlTableLeftRight.bottom().padBottom(355);
        controlTableLeftRight.add(leftControl).padRight(40);
        controlTableLeftRight.add(rightControl);

        stage.addActor(scoreTable);
        stage.addActor(controlTableUpDown);
        stage.addActor(controlTableLeftRight);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(31 / 255f, 0 / 255f, 41 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        batch.begin();
        background.draw(batch);
        batch.end();

        checkGarbageCollision();
        checkWagonCollision();
        garbageCollector.render(batch);
        garbage.render(batch);


        if (garbageCollector.getX() <= 90 || garbageCollector.getX() >= 900 || garbageCollector.getY() <= 790 || garbageCollector.getY() >= 1600) {
            parent.setScreen(new FailScreen(parent, score));
        }
    }

    private void checkGarbageCollision() {
        if (garbageCollector.overlaps(garbage)) {
            garbage.spawnGarbage(garbageCollector.getX(), garbageCollector.getY());
            scoreCountLabel.setText(score += 10);
            // if (score % 3 == 0) {
                garbageCollector.addGarbageWagon();
            // }
        }
    }

    private void checkWagonCollision() {
        for (GarbageWagon wagon: garbageCollector.getGarbageWagons()) {
            if (garbageCollector.getGarbageWagons().indexOf(wagon) != 0 && garbageCollector.overlaps(wagon)) {
                parent.setScreen(new FailScreen(parent, score));
            }
        }
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
