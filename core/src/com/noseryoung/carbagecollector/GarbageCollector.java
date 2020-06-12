package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class GarbageCollector extends Rectangle {
    private final Texture garbageCollectorImg;
    private TextureRegion garbageRegion;
    private int rotation = 0;
    private ArrayList<GarbageWagon> garbageWagons;

    public GarbageCollector() {
        garbageCollectorImg = new Texture("garbageCollector.png");
        width = (int) Math.round(garbageCollectorImg.getWidth() * 3);
        height = (int) Math.round(garbageCollectorImg.getHeight() * 3);
        this.y = 1200;
        this.x = Gdx.graphics.getWidth() / 2;
        garbageRegion = new TextureRegion(garbageCollectorImg);
        garbageWagons = new ArrayList<>();
    }

    public void render(SpriteBatch batch) {
        move();
        batch.begin();
        batch.draw(garbageRegion, x, y, width / 2, height / 2, width, height, 1, 1, rotation);
        batch.end();
        renderGarbageWagons(batch);
    }

    public void move() {
        float step = 150 * Gdx.graphics.getDeltaTime();
        switch (rotation) {
            case 0:
                y += step;
                break;
            case 90:
                x -= step;
                break;
            case 180:
                y -= step;
                break;
            case 270:
                x += step;
                break;
            default:
                break;
        }
    }

    public void addGarbageWagon() {
        if (garbageWagons.size() == 0) {
            garbageWagons.add(new GarbageWagon(x, y, rotation));
        } else {
            GarbageWagon lastWagon = garbageWagons.get(garbageWagons.size() - 1);
            garbageWagons.add(new GarbageWagon(
                    lastWagon.x,
                    lastWagon.y,
                    lastWagon.getRotation(),
                    lastWagon.getPendingChanges()));
        }
    }

    public void renderGarbageWagons(SpriteBatch batch) {
        for (GarbageWagon wagon : garbageWagons)
            wagon.render(batch);
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;

        PositionChange change = new PositionChange(x, y, rotation);
        for (GarbageWagon wagon : garbageWagons) {
            wagon.addPositionChange(change);
        }
    }


}
