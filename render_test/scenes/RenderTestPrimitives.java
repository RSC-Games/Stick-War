package render_test.scenes;

import render_test.*;
import render_test.s1.*;
import java.util.ArrayList;
import java.awt.Color;

import velocity.util.*;
import velocity.*;
import velocity.sprite.*;
import velocity.sprite.ui.*;

public class RenderTestPrimitives extends Scene {

    public RenderTestPrimitives(String name, int uuid) {
        super(name, uuid);
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new ShapeDrawer(new Point(0, 0)));
        sprites.add(new SceneSwitcher(new Point(0, 0), 10, "RenderTestTextures"));
        /* Stop adding sprites here. */

        /* UI Panel here */
        sprites.add(new FPSCounter(new Transform(new Point(3, 12)), "FPS", Color.white));
        sprites.add(new DrawText(new Transform(new Point(3, 26)), "Text", "TEST: Drawing primtives", Color.green));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new RendererPluginCamera(new Point(0, 0)));

        this.sprites = sprites;
    }
}
