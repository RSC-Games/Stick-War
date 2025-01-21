package render_test.s4;

import render_test.SceneSwitcher;
import velocity.util.Popup;
import velocity.util.Transform;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.UnsupportedFrameBufferOperation;
import velocity.sprite.ImageSprite;

public class LitTexturePainter extends ImageSprite {
    public LitTexturePainter(Transform transform, String name, String path) {
        super(transform, name, path);
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        try {
            fb.drawShaded(this.img, d);
        }
        catch (UnsupportedFrameBufferOperation ie) {
            ie.printStackTrace();
            Popup.showWarning("VXRA Renderer Test", "Failed test: Illumination. " + 
                "Continue to next test?");
            Scene.currentScene.<SceneSwitcher>getSprite(SceneSwitcher.class).loadNextScene();
        }
    }
}
