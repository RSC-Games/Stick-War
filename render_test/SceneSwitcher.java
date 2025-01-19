package render_test;

import velocity.util.Timer;
import velocity.Scene;
import velocity.sprite.Sprite;
import velocity.util.Point;

public class SceneSwitcher extends Sprite {
    String scene;
    Timer t;
    public SceneSwitcher(Point pos, int timeout, String scene) {
        super(pos, 0, "Switcher");
        this.scene = scene;
        this.t = new Timer(timeout * 1000, false);
    }

    public void tick() {
        if (t.tick()) loadNextScene();
    }

    public void loadNextScene() {
        Scene.scheduleSceneLoad(scene);
    }
}
