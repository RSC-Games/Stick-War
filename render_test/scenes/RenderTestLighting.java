package render_test.scenes;

import render_test.*;
import render_test.s4.*;
import java.awt.Color;

import velocity.util.*;
import velocity.*;
import velocity.sprite.ui.*;

public class RenderTestLighting extends Scene {
    public RenderTestLighting(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new LitTexturePainter(new Transform(new Point(0, 0)), "Tex0", "./images/checkerboard.png"));
        sprites.add(new LitTexturePainter(new Transform(new Point(512, 0)), "Tex1", "./images/colored_checkerboard.png"));
        sprites.add(new LightingPanTarget(new Point(0, 0)));
        sprites.add(new SceneSwitcher(new Point(0, 0), 10, "GameScene"));
        /* Stop adding sprites here. */

        /* UI Panel here */
        sprites.add(new FPSCounter(new Transform(new Point(3, 12)), "FPS", Color.white));
        sprites.add(new DrawText(new Transform(new Point(3, 26)), "Text", "TEST: Illumination", Color.green));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new RendererPluginCamera(new Point(-1024, 0)));
    }
}
