package render_test.s4;

import render_test.FollowTarget;

import java.awt.Color;

import velocity.util.Point;
import velocity.util.Transform;
import velocity.lighting.PointLight;
import velocity.sprite.Camera;

public class LightingPanTarget extends FollowTarget {
    PointLight light;
    PointLight mLight;
    static final int MULT = 75;
    int frames = 0;
    Point iniPos;

    public LightingPanTarget(Point pos) {
        super(new Transform(pos), "Target");
        this.transform.updateRect(new Point(Camera.res.x, Camera.res.y));
        this.light = new PointLight(this.transform.getPosition(), 250, 1.5f, Color.white);
        this.mLight = new PointLight(this.transform.getPosition(), 100, 1.2f, Color.white);
        this.iniPos = this.transform.getPosition();
    }

    public void tick() {
        this.transform.setPosition(parametric(frames / 180f).add(iniPos));
        this.mLight.setPos(this.transform.getPosition());
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
