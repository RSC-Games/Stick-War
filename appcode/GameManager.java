package appcode;

import appcode.ui.DeathEffect;
import appcode.ui.PauseManager;
import velocity.util.Point;
import velocity.util.Timer;
import velocity.Scene;
import velocity.sprite.Sprite;

public class GameManager extends Sprite {
    public Player player;
    public boolean running = true;
    
    private Timer deathTimer = null;
    private PauseManager pauseSystem;

    public GameManager() {
        super(Point.zero, 0f, "Game Manager");
    }

    public void init() {
        this.player = Scene.currentScene.getSprite(Player.class);
        this.pauseSystem = Scene.currentScene.getSprite(PauseManager.class);
    }

    public void tick() {
        if (this.deathTimer != null && this.deathTimer.tick())
            Scene.scheduleSceneLoad("DeathScene");

        if (deathTimer == null)
            running = !pauseSystem.isPaused();
    }

    public void playerDied() {
        this.deathTimer = new Timer(3000, false);
        Scene.currentScene.addSprite(new DeathEffect());
    }
}
