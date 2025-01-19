package render_test.s4;

import render_test.SceneSwitcher;
import velocity.util.Popup;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.renderer.UnsupportedFrameBufferOperation;
import velocity.sprite.ImageSprite;
import velocity.util.Point;

public class LitTexturePainter extends ImageSprite {
    public LitTexturePainter(Point pos, int rot, String name, String path) {
        super(pos, rot, name, path);
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
