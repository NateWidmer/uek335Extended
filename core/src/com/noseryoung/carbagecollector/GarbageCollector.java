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
        renderGarbageWagons(batch);
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

    public void addGarbageWagon() {
        if (garbageWagons.size() == 0) {
            garbageWagons.add(new GarbageWagon(x, y, rotation, 1));
        } else {
            garbageWagons.add(new GarbageWagon(garbageWagons.get(garbageWagons.size() - 1).x, garbageWagons.get(garbageWagons.size() - 1).y, garbageWagons.get(garbageWagons.size() - 1).getRotation(), garbageWagons.size() + 1));
        }
    }

    public void renderGarbageWagons(SpriteBatch batch) {
        float x =  this.x;
        float y = this.y;
        int rotation = this.rotation;

        for (GarbageWagon wagon : garbageWagons) {
            wagon.updatePosition(rotation, x, y);

            wagon.render(batch);

            x = wagon.getX();
            y = wagon.getY();
            rotation = wagon.getRotation();
        }
    }

    public void rotateWagons(int collectorRotation) {
//        if (garbageWagons != null) {
//            for (GarbageWagon wagon : garbageWagons) {
//                do{
//                    if(garbageWagons.size() == 1){
//                        if(wagon.getX() == x && wagon.getY() == y){
//                            wagon.setRotation(collectorRotation);
//                            break;
//                        }
//                    }else{
//                        if(wagon.getX() == garbageWagons.get(garbageWagons.indexOf(wagon) - 1).getX() && wagon.getY() == garbageWagons.get(garbageWagons.indexOf(wagon) - 1).getY()){
//                            wagon.setRotation(collectorRotation);
//                            break;
//                        }
//                    }
//                } while(true);
//            }
//        }
    }

    public ArrayList<GarbageWagon> getGarbageWagons() {
        return garbageWagons;
    }

    public void setGarbageWagons(ArrayList<GarbageWagon> garbageWagons) {
        this.garbageWagons = garbageWagons;
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
