package appcode.environment;

import velocity.util.Point;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ImageSprite;

// Possible LVCPU Enhancement. Take input tiles and draw the entire scene with them.
public class TiledBackgroundRenderer extends ImageSprite {
    Point tileSz;

    public TiledBackgroundRenderer(Point pos, String image) {
        super(pos, 0f, "TiledBGRenderer", image);
        this.tileSz = this.pos.getWH();
        this.sortOrder = -1;
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        Point orig = d.drawRect.getPos();
        
        for (int x = -6; x <= 6; x++) {
        for (int y = -4; y <= 4; y++) {
            d.drawRect.setPos(orig.add(new Point(x, y).mult(tileSz)));
            fb.drawShaded(this.img, d);
        }
        }
    }

    @Override
    public void DEBUG_render(FrameBuffer fb, DrawInfo d) {
        this.render(d, fb);
    }
}
