package appcode.tutorial;

import java.awt.event.KeyEvent;

import velocity.InputSystem;
import velocity.Scene;
import velocity.sprite.Sprite;
import velocity.util.Point;

public class SkipKeyListener extends Sprite {
    // AWT Escape key code may be wrong on certain systems. Got value 256.
    private static final int K_ESCAPE = 256;

    public SkipKeyListener() {
        super(Point.zero, 0f, "Skip Tutorial System.");
    }
    
    public void tick() {
        if (InputSystem.getKey(KeyEvent.VK_ESCAPE) || InputSystem.getKey(K_ESCAPE))  // Val 27
            Scene.scheduleSceneLoad("GameScene");
    }
}
