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

public class LocalPlayer extends PhysicsSprite {
    private int maxHP = 0;
    private int hp = 0;

    private int speed = 6;
    private int jumpSpeed = 20;
    private AnimStateMachine anim;
    private boolean dead = false;

    private boolean jumped = false;

    public LocalPlayer(Point pos, String name, String image) {
        super(new Transform(pos), name, image, 2f);
        this.transform.sortOrder = 2;

        this.anim = new AnimStateMachine("./assets/anim/player.anim");
        this.anim.setString("action", "idle");

        this.col.setWH(this.transform.location.getW(), this.transform.location.getH());
    }

    public void init() {
        //this.gameCamera = Scene.currentScene.getSprite(BoundedCamera.class);
    }

    public void tick() {
        if (dead) return;

        // Jumping
        if (InputSystem.getKey(KeyEvent.VK_SPACE) && touchingGround()) {
            this.jumped = true;
            velocity.y = -jumpSpeed;
        }

        else if (touchingGround())
            this.jumped = false;

        //int vertical = -InputSystem.getAxis(KeyEvent.VK_W, KeyEvent.VK_S);
        int horizontal = InputSystem.getAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        boolean clicked = InputSystem.clicked(MouseEvent.BUTTON1); // For attacking.

        // Move the character.
        Point moveVec = clampMove(new Point(horizontal * speed, 0 * speed));
        transform.translate(moveVec);

        if (moveVec.x != 0)
            transform.setScale(new Point(moveVec.x > 0 ? 1 : -1, 1));

        // Animation state.
        setState(moveVec);
        this.anim.animTick();

        // Update draw rect.
        RendererImage drawFrame = anim.getDrawFrame();
        this.transform.updateRect(new Point(drawFrame.getWidth(), drawFrame.getHeight()));
    }

    @Override
    public void simPhysics() {
        super.simPhysics();
    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof RectCollider) {
            //System.out.println("Hit a rectcollider " + other.transform.location);
        }
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(anim.getDrawFrame(), d);
    }

    private void setState(Point mv) {
        if (jumped)
            anim.setString("action", "jump");
        else if (mv.x == 0 && mv.y == 0)
            anim.setString("action", "idle");
        else if (mv.x > 0)
            anim.setString("action", "right");
        else if (mv.x < 0)
            anim.setString("action", "right");
        /*
        else if (mv.y > 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "down");
        }
        else if (mv.y < 0) {
            anim.setBool("idle", false);
            anim.setString("dir", "up");
        }*/
    }
}
