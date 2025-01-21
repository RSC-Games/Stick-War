package render_test.s2;

import render_test.SceneSwitcher;
import velocity.util.Popup;
import velocity.util.Transform;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.UnsupportedFrameBufferOperation;
import velocity.sprite.ImageSprite;

public class BasicTexturePainter extends ImageSprite {
    public BasicTexturePainter(Transform transform, String name, String path) {
        super(transform, name, path);
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        try {
            fb.blit(this.img, d);
        }
        catch (UnsupportedFrameBufferOperation ie) {
            ie.printStackTrace();
            Popup.showWarning("VXRA Renderer Test", "Failed test: Draw textures. " + 
                "Continue to next test?");
            Scene.currentScene.<SceneSwitcher>getSprite(SceneSwitcher.class).loadNextScene();
        }
    }
}
