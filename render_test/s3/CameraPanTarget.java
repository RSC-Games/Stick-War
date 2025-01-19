package render_test.s3;

import render_test.FollowTarget;
import velocity.util.Point;

public class CameraPanTarget extends FollowTarget {
    static final int MULT = 150;
    int frames = 0;

    public CameraPanTarget(Point pos) {
        super(pos, 0, "Target");
    }

    public void tick() {
        this.pos.setPos(parametric(frames / 15f));
        frames++;
    }

    // Domain: [0, 50]
    private Point parametric(float t) {
        return new Point(x(t), y(t));
    }

    // Output x in radians.
    private int x(float t) {
        return (int)(MULT * Math.sin(t/2f));
    }

    // Output y in radians.
    private int y(float t) {
        return (int)(MULT * Math.cos(t/4f));
    }
}
