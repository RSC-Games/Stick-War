package appcode.title;

import velocity.util.Point;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ImageSprite;

public class BGWallTile extends ImageSprite {
    static final Point TILE_SIZE = new Point(237, 0);

    public BGWallTile(Point pos, String image) {
        super(pos, 0f, "Background Wall Tiler", image);
        this.sortOrder = 1;
    }
    
    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        //Point p = d.drawRect.getDrawLoc();
        Point p = d.drawRect.getPos();
        p.y = d.drawRect.getH()/2;

        for (int x = -4; x <= 4; x++) {
            Point drawPoint = new Point(x, 0).mult(TILE_SIZE);
            d.drawRect.setPos(drawPoint.add(p));
            fb.drawShaded(this.img, d);
        }
    }

    @Override
    public void DEBUG_render(FrameBuffer fb, DrawInfo info) {
        this.render(info, fb);
    }
}
