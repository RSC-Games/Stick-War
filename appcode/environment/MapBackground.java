package appcode.environment;

import velocity.Scene;
import velocity.sprite.ImageSprite;
import velocity.util.Point;

public class MapBackground extends ImageSprite {
    public MapBackground() {
        super(Point.zero, 0f, "Map Background", "./assets/sprites/map/city_background.png");
    }

    public void tick() {
        this.pos.setPos(Scene.currentScene.getCamera().pos.getPos());
    }
}
