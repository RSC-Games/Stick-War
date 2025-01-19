package appcode;
import velocity.*;
import velocity.util.*;
import velocity.animation.AnimStateMachine;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.RendererImage;
import velocity.sprite.collision.*;
import velocity.sprite.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LocalPlayer extends DynamicSprite {
    private int maxHP = 0;
    private int hp = 0;

    private BoundedCamera gameCamera;

    private int speed = 3;
    private AnimStateMachine anim;
    private boolean dead = false;

    public LocalPlayer(Point pos, int rot, String name, String image) {
        super(pos, rot, name, image);
        this.sortOrder = 2;

        this.anim = new AnimStateMachine("./assets/anim/player.anim");
        this.anim.setString("action", "idle");

        this.col.setWH((int)(this.pos.getW()), (int)(this.pos.getH()));
    }

    public void init() {
        this.gameCamera = Scene.currentScene.getSprite(BoundedCamera.class);
    }

    public void tick() {
        if (dead) return;

        int vertical = -InputSystem.getAxis(KeyEvent.VK_W, KeyEvent.VK_S);
        int horizontal = InputSystem.getAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        boolean clicked = InputSystem.clicked(MouseEvent.BUTTON1); // For attacking.

        // Move the character.
        Point moveVec = clampMove(new Point(horizontal * speed, vertical * speed));
        pos.translate(moveVec);

        // Animation state.
        //setState(moveVec);
        this.anim.animTick();

        // Update draw rect.
        RendererImage drawFrame = anim.getDrawFrame();
        this.pos.setWH(drawFrame.getWidth(), drawFrame.getHeight());
    }

    @Override
    public void onCollision(Sprite other) {
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(anim.getDrawFrame(), d);
    }

    private void setState(Point mv) {
        if (mv.x == 0 && mv.y == 0)
            anim.setBool("idle", true);
        else if (mv.x > 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "right");
        }
        else if (mv.x < 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "left");
        }
        else if (mv.y > 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "down");
        }
        else if (mv.y < 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "up");
        }
    }
}
