package appcode.ui;

import java.awt.Color;

import velocity.util.Point;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.Camera;
import velocity.sprite.ui.UIRenderable;

public class DeathEffect extends UIRenderable {
    int radius;
    int thickness = 1;

    public DeathEffect() {
        super(new Point(0, 0), 0f, "Death Vignette");
        this.radius = (int)Math.sqrt(Math.pow(Camera.res.x, 2) + Math.pow(Camera.res.y, 2));
        this.sortOrder = -5;
    }

    public void tick() {
        this.thickness += ((radius - 150) / 120);
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        Point outP = new Point(-(this.radius - Camera.res.x) / 2, -(this.radius - Camera.res.y) / 2);
        fb.drawCircle(outP, radius, thickness, Color.BLACK, false, sortOrder);
    }
    
}
