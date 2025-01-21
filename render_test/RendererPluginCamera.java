package render_test;

import velocity.util.*;
import velocity.Scene;
import velocity.sprite.*;

public class RendererPluginCamera extends Camera {
    FollowTarget t;

    public RendererPluginCamera(Point pos) {
        super(pos);
    }

    public void init() {
        this.t = Scene.currentScene.<FollowTarget>getSprite(FollowTarget.class);
    }

    public void tick() {
        if (t == null) return;
        this.transform.setPosition(this.t.transform.getPosition());
    }
}
