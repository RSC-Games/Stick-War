package render_test.scenes;

import render_test.*;
import render_test.s2.BasicTexturePainter;
import render_test.s3.*;
import java.util.ArrayList;
import java.awt.Color;

import velocity.util.*;
import velocity.*;
import velocity.sprite.*;
import velocity.sprite.ui.*;

public class RenderTestCameraPan extends Scene {
    public RenderTestCameraPan(String name, int uuid) {
        super(name, uuid);
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new BasicTexturePainter(new Point(0, 0), 0, "Background", "./images/checkerboard.png"));
        sprites.add(new CameraPanTarget(new Point(80, -100)));
        sprites.add(new SceneSwitcher(new Point(0, 0), 10, "RenderTestLighting"));
        /* Stop adding sprites here. */

        /* UI Panel here */
        sprites.add(new FPSCounter(new Point(3, 12), 0, "FPS", Color.white));
        sprites.add(new DrawText(new Point(3, 26), 0, "Text", "TEST: Camera panning", Color.green));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new RendererPluginCamera(new Point(0, 0)));

        this.sprites = sprites;
    }
}
