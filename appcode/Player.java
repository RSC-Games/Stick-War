package appcode;
import velocity.*;
import velocity.util.*;
import velocity.animation.AnimStateMachine;
import velocity.audio.AudioClip;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.RendererImage;
import velocity.sprite.collision.*;
import velocity.sprite.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import appcode.enemies.Enemy;
import appcode.environment.EnemySpawnerSystem;
import appcode.ui.EXPTracker;
import appcode.ui.LevelUpEffect;

public class Player extends DynamicSprite {
    public int level = 1;
    public int experience = 0;
    public int maxExperience = 750;

    private GameManager mgr;
    private EnemySpawnerSystem enemySystem;
    private BoundedCamera gameCamera;
    private Sword sword;
    private EXPTracker uiExpTracker;

    private int speed = 3;
    private AnimStateMachine anim;
    private AudioClip lvlNoise;
    private AudioClip deadNoise;
    private boolean dead = false;

    public Player(Point pos, int rot, String name, String image) {
        super(pos, rot, name, image);
        this.sortOrder = 2;

        this.anim = new AnimStateMachine("./anim/player.anim");
        this.anim.setString("dir", "down");
        this.anim.setBool("idle", false);

        this.lvlNoise = new AudioClip("./audio/sfx/level_up.wav");
        this.deadNoise = new AudioClip("./audio/music/bgm_old/you_died_stinger.wav");

        this.col.setWH((int)(this.pos.getW() * 0.75f), (int)(this.pos.getH() * 0.95f));
    }

    public void init() {
        this.enemySystem = Scene.currentScene.getSprite(EnemySpawnerSystem.class);
        this.sword = Scene.currentScene.getSprite(Sword.class);
        this.mgr = Scene.currentScene.getSprite(GameManager.class);
        this.gameCamera = Scene.currentScene.getSprite(BoundedCamera.class);
        this.uiExpTracker = Scene.currentScene.getSprite(EXPTracker.class);
    }

    public void tick() {
        if (dead || !mgr.running) return;

        int vertical = -InputSystem.getAxis(KeyEvent.VK_W, KeyEvent.VK_S);
        int horizontal = InputSystem.getAxis(KeyEvent.VK_D, KeyEvent.VK_A);
        boolean clicked = InputSystem.clicked(MouseEvent.BUTTON1); // For attacking.

        // Move the character.
        Point moveVec = clampMove(new Point(horizontal * speed, vertical * speed));
        pos.translate(moveVec);

        if (clicked) this.sword.swing();
        this.sword.syncPos(this.pos.getPos());

        // Animation state.
        setState(moveVec);
        this.anim.animTick();

        // Update draw rect.
        RendererImage drawFrame = anim.getDrawFrame();
        this.pos.setWH(drawFrame.getWidth(), drawFrame.getHeight());
    }

    @Override
    public void onCollision(Sprite other) {
        if (other instanceof Enemy && !dead) {
            enemySystem.freezeGame();
            dead = true;
            deadNoise.play();
        }
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(anim.getDrawFrame(), d);
    }

    public void gainEXP(int exp) {
        this.experience += exp;
        gameCamera.startShake();
        uiExpTracker.updateEXP(this.experience);

        if (this.experience >= this.maxExperience) {
            Scene.currentScene.addSprite(new LevelUpEffect());
            uiExpTracker.resetEXP();
            gameCamera.levelUpShake();

            this.level++;
            this.experience = 0;
            this.maxExperience *= 1.6;
            this.lvlNoise.play();
            this.enemySystem.updateTimer(this.level);

            // Increase the player's movement speed (but cap it at 9 pixels/frame)
            this.speed = Math.min(getScaledSpeed(this.level), 9);
        }
    }

    // Models this function: f(x) = 1.33 ^ (x - 3) + 2.453
    private int getScaledSpeed(int x) {
        float f = (float)Math.pow(1.33f, x - 3f) + 2.453f;
        return Math.round(f);
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
