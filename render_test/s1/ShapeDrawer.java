package render_test.s1;

import render_test.SceneSwitcher;

import java.awt.Color;
import velocity.util.Popup;
import velocity.util.Transform;
import velocity.Rect;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.UnsupportedFrameBufferOperation;
import velocity.sprite.Renderable;
import velocity.util.Point;

public class ShapeDrawer extends Renderable {
    static final int MULT = 150;

    int frames = 0;
    public ShapeDrawer(Point pos) {
        super(new Transform(pos), "Draw Shapes");
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        Point p = d.drawRect.getDrawLoc();

        try {
            fb.drawLine(p, p.add(new Point(150, 80)), 3, Color.blue);
            fb.drawLines(new Point[] {p, new Point(-100, 200).add(p), new Point(94, -39).add(p)},
                        2, Color.yellow, true);

            fb.drawRect(new Rect(p, -302, 184), 3, Color.cyan, true);
            fb.drawRect(new Rect(p.add(new Point(-5, 5)), 10, 10), 3, Color.orange, false);

            fb.drawCircle(p, 50, 2, Color.green, true);
            fb.drawCircle(p.sub(new Point(300, -200)), 25, 5, Color.red, false);
        }
        catch (UnsupportedFrameBufferOperation ie) {
            ie.printStackTrace();
            Popup.showWarning("VXRA Renderer Test", "Failed test: Draw primtives. " + 
                "Continue to next test?");
            Scene.currentScene.<SceneSwitcher>getSprite(SceneSwitcher.class).loadNextScene();
        }
    }
}
