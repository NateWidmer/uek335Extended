package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GarbageCollector extends Actor {
    private Sprite garbageCollectorSprite;
    private final Texture garbageCollectorImg;

    public GarbageCollector() {
        garbageCollectorImg = new Texture("garbageCollector.png");
        garbageCollectorSprite = new Sprite(garbageCollectorImg);
        garbageCollectorSprite.scale(1.5f);
        garbageCollectorSprite.rotate(270);
        garbageCollectorSprite.setX(Gdx.graphics.getWidth() / 2);
        garbageCollectorSprite.setY(1200);
    }

    public void draw(Batch batch) {
        garbageCollectorSprite.draw(batch);
    }

    public void move() {
        switch ((int) garbageCollectorSprite.getRotation()) {
            case 0:
                garbageCollectorSprite.setY(garbageCollectorSprite.getY() + 2);
                break;
            case 90:
                garbageCollectorSprite.setX(garbageCollectorSprite.getX() - 2);
                break;
            case 180:
                garbageCollectorSprite.setY(garbageCollectorSprite.getY() - 2);
                break;
            case 270:
                garbageCollectorSprite.setX(garbageCollectorSprite.getX() + 2);
                break;
            default:
                break;
        }
    }

    public Sprite getGarbageCollectorSprite() {
        return garbageCollectorSprite;
    }

    public void setGarbageCollectorSprite(Sprite garbageCollectorSprite) {
        this.garbageCollectorSprite = garbageCollectorSprite;
    }

    public Texture getGarbageCollectorImg() {
        return garbageCollectorImg;
    }
}
