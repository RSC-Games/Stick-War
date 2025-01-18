package appcode.title;

import velocity.util.Point;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ImageSprite;

public class BackgroundTiling extends ImageSprite {
    static final Point TILE_SIZE = new Point(237, 237);

    public BackgroundTiling(Point pos, String image) {
        super(pos, 0f, "Background Tiler", image);
        this.sortOrder = 0;
    }
    
    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        Point startLoc = d.drawRect.getPos();

        for (int x = -4; x <= 4; x++) {
            for (int y = -3; y <= 3; y++) {
                Point drawPoint = new Point(x, y).mult(TILE_SIZE);
                d.drawRect.setPos(drawPoint.add(startLoc));
                fb.drawShaded(this.img, d);
            }
        }
    }

    @Override
    public void DEBUG_render(FrameBuffer fb, DrawInfo info) {
        this.render(info, fb);
    }
}
