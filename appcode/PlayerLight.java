package appcode;

import velocity.Scene;
import velocity.lighting.PointLight;
import velocity.sprite.Sprite;
import velocity.util.GlobalRandom;
import velocity.util.Point;

public class PlayerLight extends Sprite {
    Player player;
    PointLight light;
    float radius = 250f;

    public PlayerLight(Point pos, float rot, String name) {
        super(pos, rot, name);
        this.light = new PointLight(pos, radius);
    }

    @Override
    public void init() {
        this.player = Scene.currentScene.getSprite(Player.class);
    }

    @Override
    public void tick() {
        Point nPos = player.pos.getPos().add(new Point(
            GlobalRandom.randint(-5, 5), 
            GlobalRandom.randint(-5, 5)
        ));

        this.pos.setPos(nPos);
        this.light.setPos(nPos);
        this.light.setRadius(GlobalRandom.randint(-5, 5) + radius);
    }

    @Override
    public void delete() {
        this.light.delete();
    }
}
