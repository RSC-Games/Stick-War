package appcode.environment;

import velocity.Scene;
import velocity.sprite.ImageSprite;
import velocity.util.Point;
import velocity.util.Transform;

public class MapBackground extends ImageSprite {
    public MapBackground() {
        super(new Transform(Point.zero), "Map Background", "./assets/sprites/map/city_background.png");
    }

    public void tick() {
        this.transform.setPosition(Scene.currentScene.getCamera().transform.getPosition());
    }
}
