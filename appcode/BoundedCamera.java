package appcode;

import velocity.sprite.Camera;
import velocity.sprite.Sprite;
import velocity.sprite.camera.Base2DCamera;
import velocity.util.GlobalRandom;
import velocity.util.Point;
import velocity.util.Vector2;

public class BoundedCamera extends Base2DCamera {
    boolean shaking = false;
    int shakeFramesLeft = 0;
    int curShakeDistance;

    final int shakeDistance = 10;
    final int shakeFrames = 10;
    final int levelUpShakeFrames = 45;
    final int levelUpShakeDistance = 20;
    final int levelUpLowerShakeFrames = 75;

    public BoundedCamera(Point pos, Sprite target) {
        super(pos, target);
    }
    
    @Override
    public void tick() {
        super.tick();
        Point middle = Camera.res.div(2);

        // Now we need screen shake when you die.
        Point shakeDelta = simShake();

        // Camera bounds locking (so it can't go off the map).
        Point pos = this.pos.getPos();
        pos.x = shakeDelta.x + clamp(pos.x, -1540 + middle.x, 1540 - middle.x);
        pos.y = shakeDelta.y + clamp(pos.y, -1068 + middle.y, 1068 - middle.y);
        this.pos.setPos(pos);
    }

    public Point simShake() {
        if (!shaking) return Point.zero;

        float theta = (float)Math.toRadians(GlobalRandom.randint(0, 360));
        Vector2 angle = new Vector2(curShakeDistance * (float)Math.cos(theta), curShakeDistance * (float)Math.sin(theta));

        shakeFramesLeft -= 1;
        if (shakeFramesLeft <= 0) {
            if (curShakeDistance == levelUpShakeDistance) {
                shakeFramesLeft = levelUpLowerShakeFrames;
                curShakeDistance = shakeDistance;
            }
            else
                shaking = false;
        }

        return new Point(angle);
    }

    public void startShake() {
        shaking = true;
        shakeFramesLeft = shakeFrames;
        curShakeDistance = shakeDistance;
    }

    public void levelUpShake() {
        shaking = true;
        shakeFramesLeft = levelUpShakeFrames;
        curShakeDistance = levelUpShakeDistance;
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }
}
