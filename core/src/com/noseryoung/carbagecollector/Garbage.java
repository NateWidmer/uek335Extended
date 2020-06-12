package com.noseryoung.carbagecollector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Garbage extends Rectangle {
    private Texture garbage;
    int xBorderMax;
    int xBorderMin;
    int yBorderMax;
    int yBorderMin;

    public Garbage(int xBorderMax, int xBorderMin, int yBorderMax, int yBorderMin) {
        this.xBorderMax = xBorderMax;
        this.xBorderMin = xBorderMin;
        this.yBorderMax = yBorderMax;
        this.yBorderMin = yBorderMin;
        spawnGarbage(1200, Gdx.graphics.getWidth() / 2);
        width = (int) Math.round(garbage.getWidth() * 1.5);
        height = (int) Math.round(garbage.getHeight() * 1.5);
    }

    public void spawnGarbage(float xGarbageCollector, float yGarbageCollector) {
        garbage = getGarbage();
        float xCoordinate;
        float yCoordinate;
        do{
            xCoordinate = (float) Math.random() * ((xBorderMax - xBorderMin) + 1) + xBorderMin;
            yCoordinate = (float) Math.random() * ((yBorderMax - yBorderMin) + 1) + yBorderMin;
        }while(xCoordinate == xGarbageCollector || yCoordinate == yGarbageCollector);

        x = xCoordinate;
        y = yCoordinate;
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(garbage, x, y, width, height);
        batch.end();
    }

    public Texture getGarbage() {
        ArrayList<Texture> recycleItems = new ArrayList<>();
        recycleItems.add(new Texture("Sprites/recycle/beerBottle.png"));
        recycleItems.add(new Texture("Sprites/recycle/jar.png"));
        recycleItems.add(new Texture("Sprites/recycle/cup.png"));
        recycleItems.add(new Texture("Sprites/recycle/cartonBox.png"));
        recycleItems.add(new Texture("Sprites/recycle/newspaper.png"));
        recycleItems.add(new Texture("Sprites/recycle/orangeCanister.png"));
        recycleItems.add(new Texture("Sprites/recycle/paperBag.png"));
        recycleItems.add(new Texture("Sprites/recycle/pizzaBox.png"));
        recycleItems.add(new Texture("Sprites/recycle/redCanister.png"));
        recycleItems.add(new Texture("Sprites/recycle/smallTinCan.png"));
        recycleItems.add(new Texture("Sprites/recycle/sodaBottle.png"));
        recycleItems.add(new Texture("Sprites/recycle/sodaCan.png"));
        recycleItems.add(new Texture("Sprites/recycle/sodaCup.png"));
        recycleItems.add(new Texture("Sprites/recycle/sprayBottle.png"));
        recycleItems.add(new Texture("Sprites/recycle/tallTinCan.png"));
        recycleItems.add(new Texture("Sprites/recycle/waterBottle.png"));

        Random random = new Random();
        Texture item = recycleItems.get(random.nextInt(16));

        return item;

    }
}
