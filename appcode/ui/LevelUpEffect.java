package appcode.ui;

import java.awt.Color;

import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UIText;
import velocity.util.Point;
import velocity.util.Timer;

public class LevelUpEffect extends UIText {
    Point offset = new Point(-30, -100);

    Timer delete = new Timer(1500, false);
    Timer c = new Timer(33, true);
    boolean show = true;

    public LevelUpEffect() {
        super(Point.zero, 0f, "LevelUpEffect", "SERIF", Color.GREEN);
        this.text = "Level Up";
        this.setSize(26);
    }

    @Override
    public void tick() {
        if (delete.tick())
            Scene.currentScene.removeSprite(this);

        if (c.tick())
            show = !show;
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        if (!show) return;
        d.drawRect.setPos(AnchorPoint.getAnchor("center").add(offset));
        super.renderUI(d, fb);
    }
    
}
