package appcode.ui;

import java.awt.Color;

import appcode.Player;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UIText;
import velocity.util.Point;

public class EXPTracker extends UIText {
    Player player;
    Point offset;
    int lastEXP = 0;
    int curEXP = 0;

    // Flash system (for visual appeal).
    static final int FLASH_PERIOD = 5; // frames
    
    public EXPTracker(Point pos, float rot, String name, Color c) {
        super(pos, rot, name, "SERIF", c);
        this.offset = pos;
        this.setSize(14);
    }

    @Override
    public void init() {
        this.player = Scene.currentScene.getSprite(Player.class);
    }

    /**
     * Called when an enemy is killed and extra experience is gained.
     * TODO: Add flashing (what color?) after death.
     */
    public void updateEXP(int newEXP) {
        //lastEXP = curEXP;
        curEXP = newEXP;
    }

    /**
     * Called after a level up
     */
    public void resetEXP() {
        lastEXP = 0;
        curEXP = 0;
    }
    
    @Override
    public void tick() {
        if (lastEXP < curEXP) lastEXP++;
        this.text = "EXP: " + lastEXP + " / " + player.maxExperience;
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        //this.
        d.drawRect.setPos(AnchorPoint.getAnchor("bottomleft").add(offset));
        super.renderUI(d, fb);
    }
}
