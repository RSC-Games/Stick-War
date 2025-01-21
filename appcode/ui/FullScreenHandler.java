package appcode.ui;

import java.awt.event.KeyEvent;

import org.lwjgl.glfw.GLFW;

import velocity.InputSystem;
import velocity.VXRA;
import velocity.config.GlobalAppConfig;
import velocity.renderer.window.Window;
import velocity.sprite.Sprite;
import velocity.util.Point;
import velocity.util.Transform;

/**
 * Allow the user to enter/exit fullscreen if they choose via F11.
 */
public class FullScreenHandler extends Sprite {
    static boolean isFullScreen = GlobalAppConfig.bcfg.WINDOW_FULLSCREEN;

    public FullScreenHandler(String name) {
        super(new Transform(Point.zero), name);
    }

    @Override
    public void tick() {
        if (InputSystem.getKeyDown(KeyEvent.VK_F11) || InputSystem.getKeyDown(GLFW.GLFW_KEY_F11)) {
            // Switch window state.
            Window window = VXRA.rp.getWindow();
            if (isFullScreen)
                window.exitFullScreen();
            else
                window.enterFullScreen();

            isFullScreen = !isFullScreen;
        }
    }
}
