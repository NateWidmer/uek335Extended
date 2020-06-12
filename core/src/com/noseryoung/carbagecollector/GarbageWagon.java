package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class GarbageWagon extends Rectangle {
    private final Texture garbageWagon;
    private TextureRegion garbageWagonRegion;
    private int rotation;
    private ArrayList<PositionChange> positionChanges;

    public GarbageWagon(float x, float y, int rotation) {
        this(x, y, rotation, new ArrayList<PositionChange>());
    }

    public GarbageWagon(float x, float y, int lastWagonRotation, ArrayList<PositionChange> positionChanges) {
        garbageWagon = new Texture("garbageWagon.png");
        width = (int) Math.round(garbageWagon.getWidth() * 3);
        height = (int) Math.round(garbageWagon.getHeight() * 3);
        garbageWagonRegion = new TextureRegion(garbageWagon);
        rotation = lastWagonRotation;
        switch (rotation) {
            case 0:
                this.y = y - width;
                this.x = x;
                break;
            case 90:
                this.y = y;
                this.x = x + width;
                break;
            case 180:
                this.y = y + width;
                this.x = x;
                break;
            case 270:
                this.y = y;
                this.x = x - width;
        }
        this.positionChanges = positionChanges;
    }

    public void addPositionChange(PositionChange change) {
        positionChanges.add(change);
    }

    public void render(SpriteBatch batch) {
        move();
        batch.begin();
        batch.draw(garbageWagonRegion, x, y, width / 2, height / 2, width, height, 1, 1, rotation);
        batch.end();
    }

    private void applyChange(PositionChange change) {
        positionChanges.remove(change);

        float diff;
        switch (rotation) {
            case 0:
                diff = y - change.y;
                break;
            case 90:
                diff = change.x - x;
                break;
            case 180:
                diff = change.y - y;
                break;
            case 270:
                diff = x - change.x;
                break;
            default:
                diff = 0;
        }

        x = change.x;
        y = change.y;

        switch (change.rotation) {
            case 0:
                y = change.y + diff;
                break;
            case 90:
                x = change.x - diff;
                break;
            case 180:
                y = change.y - diff;
                break;
            case 270:
                x = change.x + diff;
                break;
        }

        rotation = change.rotation;
    }

    public ArrayList<PositionChange> getPendingChanges() {
        return (ArrayList) positionChanges.clone();
    }

    public void move() {
        if (positionChanges.size() > 0) {
            PositionChange change = positionChanges.get(0);
            if ((rotation == 0 && change.y < y) ||
                    (rotation == 90 && change.x > x) ||
                    (rotation == 180 && change.y > y) ||
                    (rotation == 270 && change.x < x)) {
                applyChange(change);
            }
        }
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

    public void dispose(){
        garbageWagon.dispose();
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public Texture getGarbageWagon() {
        return garbageWagon;
    }
}
