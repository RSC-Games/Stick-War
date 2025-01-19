package render_test.s4;

import render_test.FollowTarget;

import java.awt.Color;

import velocity.util.Point;
import velocity.lighting.PointLight;
import velocity.sprite.Camera;

public class LightingPanTarget extends FollowTarget {
    PointLight light;
    PointLight mLight;
    static final int MULT = 75;
    int frames = 0;
    Point iniPos;

    public LightingPanTarget(Point pos) {
        super(pos, 0, "Target");
        this.pos.setWH(Camera.res.x, Camera.res.y);
        this.light = new PointLight(this.pos.getPos(), 250, 1.5f, Color.white);
        this.mLight = new PointLight(this.pos.getPos(), 100, 1.2f, Color.white);
        this.iniPos = this.pos.getPos();
    }

    public void tick() {
        this.pos.setPos(parametric(frames / 180f).add(iniPos));
        this.mLight.setPos(this.pos.getPos());
        frames++;
    }

    // Domain: [0, 50]
    private Point parametric(float t) {
        return new Point(x(t), y(t));
    }

    // Output x in radians.
    private int x(float t) {
        return (int)(MULT * Math.pow(t, Math.sqrt(t)));
    }

    // Output y in radians.
    private int y(float t) {
        return 0;
    }
}
