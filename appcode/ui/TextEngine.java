package appcode.ui;

import java.awt.Color;

import appcode.npc.SpeechBrain;
import velocity.util.Point;
import velocity.util.Timer;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UITextBox;

public class TextEngine extends UITextBox {
    Timer timer;
    String anchorString;
    SpeechBrain currentBrain = null;
    int cntr = 0;

    public TextEngine(String anchor, Point wh, String name, String fontPath, Color c) {
        super(Point.zero, wh, name, fontPath, c);
        this.anchorString = anchor;

        this.setSize(25);
        this.text = "";
    }

    public void init() {
        TextEngineManager textMgr = Scene.currentScene.getSprite(TextEngineManager.class);
        textMgr.registerTextEngine("MainEngine", this);
        this.timer = new Timer(50, true);
    }

    public void tick() {
        if (this.currentBrain == null) return;

        if (this.timer.tick()) {
            String text = this.currentBrain.getText();

            if (cntr <= text.length())
                this.text = text.substring(0, cntr++);
            else {
                this.currentBrain = null;
                cntr = 0;
            }
        }
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        Point offset = d.drawRect.getWH().div(2);
        offset.x = 0;
        
        //AnchorPoint.getAnchor(anchorString).sub(offset).add(p)
        Point newPos = AnchorPoint.getAnchor(anchorString).sub(offset);
        d.drawRect.setPos(newPos);
        super.renderUI(d, fb);
    }

    public boolean ready() {
        return this.currentBrain == null;
    }

    public void startJob(SpeechBrain brain) {
        timer.reset();
        this.currentBrain = brain;
    }
}
