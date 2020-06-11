package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GarbageCollector extends Rectangle {
    private final Texture garbageCollectorImg;
    private TextureRegion garbageRegion;
    private int rotation = 0;

    public GarbageCollector() {
        garbageCollectorImg = new Texture("garbageCollector.png");
        width = (int) Math.round(garbageCollectorImg.getWidth() * 3);
        height = (int) Math.round(garbageCollectorImg.getHeight() * 3);
        this.y = 1200;
        this.x = Gdx.graphics.getWidth() / 2;
        garbageRegion = new TextureRegion(garbageCollectorImg);
    }

    public void render(SpriteBatch batch) {
        move();
        batch.begin();
        batch.draw(garbageRegion, x, y, width / 2, height / 2, width, height, 1, 1, rotation);
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


    public Texture getGarbageCollectorImg() {
        return garbageCollectorImg;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

}
