package appcode.ui;

import java.awt.Color;

import velocity.util.Point;
import velocity.Rect;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.Camera;
import velocity.sprite.ui.UIImage;

public class Tombstone extends UIImage {
    public Tombstone(Point pos) {
        super(pos, 0f, "Tombstone", "./images/ui/tombstone.png");
    }
    
    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        d.drawRect.setPos(Camera.res.div(2));
        fb.drawRect(new Rect(Camera.res.div(2), Camera.res), 1, Color.CYAN, true, -1);
        fb.blit(this.img, d);
    }
}
