package appcode;

import velocity.util.Point;
import velocity.util.Timer;
import velocity.InputSystem;
import velocity.Scene;
import velocity.animation.AnimStateMachine;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.RendererImage;
import velocity.sprite.ImageSprite;
import velocity.sprite.collision.DynamicSprite;
import velocity.sprite.collision.Triggerable;

public class Sword extends ImageSprite implements Triggerable {
    private static final float SWORD_DIST = 1.5f;

    private boolean swung;
    private AnimStateMachine anim;
    private Player player;
    private Timer sliceT;
    private Timer waitT;

    private int angle;

    public Sword() {
        super(Point.zero, 0f, "Knight Sword", (RendererImage)null);
        this.swung = false;
        this.anim = new AnimStateMachine("./anim/sword_arc.anim");
        this.anim.setString("dir", "up");
        this.pos.setWH(anim.getDrawFrame().getWidth(), anim.getDrawFrame().getHeight());

        this.sliceT = new Timer(300, false);
        this.waitT = new Timer(350, false);
    }

    public void init() {
        this.player = Scene.currentScene.getSprite(Player.class);
    }

    public void swing() {
        if (!this.waitT.tick()) return;
        this.waitT.reset(); // Prevent infinite sword hitting.

        this.sliceT.reset();
        swung = true;
        updateActiveSprite();
    }

    public void syncPos(Point in) {
        this.pos.setPos(in);
    }

    public void killedEnemy(int exp) {
        this.player.gainEXP(exp);
    }

    public void tick() {
        if (!swung) return;

        // If the timer expired we're done showing the sword.
        if (this.sliceT.tick()) {
            this.waitT.reset();
            this.swung = false;
            return;
        }

        // Calculate offset.
        Point offset = new Point(0, 0);

        switch (angle) {
            case -1: { // Pointing upward. 
                offset = getOffset("up");

                offset.y = offset.x - (int)(offset.y * SWORD_DIST);
                offset.x = 0;
                break;
            }
            case 1: { // Pointing down. 
                offset = getOffset("down");

                offset.y = -offset.x + (int)(offset.y * SWORD_DIST);
                offset.x = 0;
                break;
            }
            case 0: { // Pointing right.
                offset = getOffset("right");

                offset.x = -offset.y + (int)(offset.x * SWORD_DIST);
                offset.y = 0;
                break;
            }
            case -2: // Pointing left
            case 2: { // Also left.
                offset = getOffset("left");
                
                offset.x = offset.y - (int)(offset.x * SWORD_DIST);
                offset.y = 0;
                break;
            }
            default:
                throw new IllegalStateException("Bad state " + angle);
        }

        // And adjust the offset for the sword.
        this.pos.setPos(this.player.pos.getPos().sub(offset));
    }

    private void updateActiveSprite() {
        // Set required texture and track the current sword angle.
        Point dir = Scene.currentScene.screenToWorldPoint(
            InputSystem.getMousePos()).sub(player.pos.getPos());
        this.angle = Math.round((float)(Math.atan2(dir.y, dir.x) / (Math.PI / 2)));
    }

    private Point getOffset(String dir) {
        anim.setString("dir", dir);
        RendererImage img = anim.getDrawFrame();
        Point offset = new Point(img.getWidth(), img.getHeight());
        this.pos.setWH(offset);
        return offset;
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        if (!swung) return;
        fb.drawShaded(this.anim.getDrawFrame(), d);
    }

    /**
     * Allow the collision system to recognize an overlap with this sprite.
     * 
     * @param other The dynamic object to simulate.
     * @return Whether this sprite overlaps it.
     */
    @Override
    public boolean overlaps(DynamicSprite other) {
        // Disable collision if the thing isn't showing.
        if (!swung) return false;
        return other.col.overlaps(this.pos);
    }
}
