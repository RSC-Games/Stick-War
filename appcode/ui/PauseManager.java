package appcode.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;

import velocity.InputSystem;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UITextBox;
import velocity.util.Point;

public class PauseManager extends UITextBox {
    private static final int K_ESCAPE = 256;
    
    boolean render = false;

    public PauseManager() {
        super(Point.zero, Point.zero, "Pause Manager", "SERIF", Color.white);
        this.text = "GAME PAUSED";
        this.setSize(30);
        this.pos.setWH(approxWidth());
    }
    
    @Override
    public void tick() {
        if (InputSystem.getKeyDown(KeyEvent.VK_ESCAPE) || InputSystem.getKeyDown(K_ESCAPE))
            render = !render;
            
        pos.setPos(AnchorPoint.getAnchor("center"));
    }

    public boolean isPaused() { return render; }

    @Override
    public void renderUI(DrawInfo info, FrameBuffer fb) {
        if (render)
            super.renderUI(info, fb);
    }
}
