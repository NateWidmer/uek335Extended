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
        garbage = new Texture(getGarbage());
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

    public void dispose(){
        garbage.dispose();
    }

    public String getGarbage() {
        ArrayList<String> recycleItems = new ArrayList<>();
        recycleItems.add("Sprites/recycle/beerBottle.png");
        recycleItems.add("Sprites/recycle/jar.png");
        recycleItems.add("Sprites/recycle/cup.png");
        recycleItems.add("Sprites/recycle/cartonBox.png");
        recycleItems.add("Sprites/recycle/newspaper.png");
        recycleItems.add("Sprites/recycle/orangeCanister.png");
        recycleItems.add("Sprites/recycle/paperBag.png");
        recycleItems.add("Sprites/recycle/pizzaBox.png");
        recycleItems.add("Sprites/recycle/redCanister.png");
        recycleItems.add("Sprites/recycle/smallTinCan.png");
        recycleItems.add("Sprites/recycle/sodaBottle.png");
        recycleItems.add("Sprites/recycle/sodaCan.png");
        recycleItems.add("Sprites/recycle/sodaCup.png");
        recycleItems.add("Sprites/recycle/sprayBottle.png");
        recycleItems.add("Sprites/recycle/tallTinCan.png");
        recycleItems.add("Sprites/recycle/waterBottle.png");

        Random random = new Random();

        return recycleItems.get(random.nextInt(16));

    }
}
