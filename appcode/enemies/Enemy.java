package appcode.enemies;

import appcode.GameManager;
import appcode.Player;
import appcode.Sword;
import velocity.util.Point;
import velocity.util.Vector2;
import velocity.Scene;
import velocity.audio.AudioClip;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.Sprite;
import velocity.sprite.collision.DynamicSprite;

public abstract class Enemy extends DynamicSprite {
    GameManager mgr;
    Player target;
    private int experience;
    private int moveSpeed;
    protected AudioClip deathSound;

    public Enemy(Point pos, String name, String image, int experience, int moveSpeed, String snd) {
        super(pos, 0f, name, image);
        this.experience = experience;
        this.sortOrder = 1;
        this.moveSpeed = moveSpeed;
        this.deathSound = new AudioClip(snd);
    }

    public void init() {
        this.target = Scene.currentScene.getSprite(Player.class);
        this.mgr = Scene.currentScene.getSprite(GameManager.class);
    }

    public void tick() {
        if (!mgr.running) return;
        
        // Chase the player.
        Vector2 distance = new Vector2(this.pos.getPos()).pointDistTo(target.pos.getPos());
        distance = distance.normalize();

        Point mVec = distance.mult(-moveSpeed).approx();
        mVec = clampMove(mVec);
        this.pos.translate(mVec);
    }

    @Override
    public void onTrigger(Sprite other) {
        if (other.name == "Knight Sword") {
            this.deathSound.play();
            Scene.currentScene.removeSprite(this);
            ((Sword)other).killedEnemy(experience);
        }
    }

    @Override
    public void delete() {
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(this.img, d);
    }
}
