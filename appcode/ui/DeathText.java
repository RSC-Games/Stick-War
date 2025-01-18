package appcode.ui;

import java.awt.Color;
import java.awt.Font;

import velocity.util.Point;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UITextBox;

public class DeathText extends UITextBox {
    Point offset;

    public DeathText(Point pos) {
        super(pos, Point.zero, "DeathText", Font.SERIF, Color.WHITE);
        this.offset = pos;
        this.text = "You died! Try again?";
        this.setSize(50);
        this.pos.setWH(approxWidth());
    }
    
    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        d.drawRect.setPos(AnchorPoint.getAnchor("top").add(offset));
        super.renderUI(d, fb);
    }
}
