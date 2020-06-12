package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GarbageWagon extends Rectangle {
    private final Texture garbageWagon;
    private TextureRegion garbageWagonRegion;
    private int rotation = 0;
    private float timer;
    private static final float DEFAULT_TIMER = 0.75f;
    private int wagonPosition;

    public GarbageWagon(float lastWagonX, float lastWagonY, int lastWagonRotation, int wagonPosition) {
        garbageWagon = new Texture("garbageWagon.png");
        width = (int) Math.round(garbageWagon.getWidth() * 3);
        height = (int) Math.round(garbageWagon.getHeight() * 3);
        garbageWagonRegion = new TextureRegion(garbageWagon);
        rotation = lastWagonRotation;
        updatePosition(rotation, lastWagonX, lastWagonY);
        timer = 0;
        this.wagonPosition = wagonPosition;
    }

    public void updatePosition(int rotation, float x, float y) {
        timer += Gdx.graphics.getDeltaTime();
        if (timer >= DEFAULT_TIMER * wagonPosition) {
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
            this.rotation = rotation;
            timer = 0;
        }

    }

    public void render(SpriteBatch batch) {
        move();
        batch.begin();
        batch.draw(garbageWagonRegion, x, y, width / 2, height / 2, width, height, 1, 1, rotation);
        batch.end();
    }

    public void move() {
        switch (rotation) {
            case 0:
                y += 2;
                break;
            case 90:
                x -= 2;
                break;
            case 180:
                y -= 2;
                break;
            case 270:
                x += 2;
                break;
            default:
                break;
        }
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
