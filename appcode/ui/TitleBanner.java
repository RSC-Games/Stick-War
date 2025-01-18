package appcode.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.image.BufferedImage;

import velocity.util.Point;
import velocity.sprite.Camera;
import velocity.sprite.ui.UIText;

public class TitleBanner extends UIText {
    BufferedImage img;
    FontMetrics metrics;

    public TitleBanner(Point pos, float rot, String name) {
        super(pos, rot, name, "Serif", Color.white);
        this.text = "The Last Stand";
        this.setSize(50);

        // Get font centering information.
        this.img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        Graphics g = img.getGraphics();
        this.metrics = g.getFontMetrics(this.font);
    }

    public void tick() {
        //LineMetrics metrics = this.font.getLineMetrics(this.text, )
        this.pos.setPos(new Point(Camera.res.x / 2, this.pos.getPos().y));
        this.pos.setWH(metrics.stringWidth(this.text), 50);
    }
    
}
