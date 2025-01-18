package appcode.ui;

import java.awt.Color;

import appcode.Player;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.AnchorPoint;
import velocity.sprite.ui.UIText;
import velocity.util.Point;

public class LevelTracker extends UIText {
    Player player;
    Point offset;
    
    public LevelTracker(Point pos, float rot, String name, Color c) {
        super(pos, rot, name, "SERIF", c);
        this.offset = pos;
        this.setSize(30);
    }

    @Override
    public void init() {
        this.player = Scene.currentScene.getSprite(Player.class);
    }
    
    @Override
    public void tick() {
        this.text = "Level: " + player.level;
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        d.drawRect.setPos(AnchorPoint.getAnchor("bottomleft").add(offset));
        super.renderUI(d, fb);
    }
}
