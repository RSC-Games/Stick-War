package render_test.scenes;

import render_test.SceneSwitcher;
import render_test.*;
import render_test.s2.*;
import java.util.ArrayList;
import java.awt.Color;

import velocity.util.*;
import velocity.*;
import velocity.sprite.*;
import velocity.sprite.ui.*;

public class RenderTestTextures extends Scene {
    public RenderTestTextures(String name, int uuid) {
        super(name, uuid);
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new BasicTexturePainter(new Transform(new Point(15, 15)), "Tex0", "./images/colored_checkerboard.png"));
        sprites.add(new SceneSwitcher(new Point(0, 0), 2, "RenderTestCameraPan"));
        /* Stop adding sprites here. */

        /* UI Panel here */
        sprites.add(new FPSCounter(new Transform(new Point(3, 12)), "FPS", Color.white));
        sprites.add(new DrawText(new Transform(new Point(3, 26)), "Text", "TEST: Drawing textures", Color.green));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new RendererPluginCamera(new Point(0, 0)));

        this.sprites = sprites;
    }
}
