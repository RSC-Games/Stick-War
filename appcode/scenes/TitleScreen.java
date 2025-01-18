package appcode.scenes;

import appcode.ui.*;

import java.awt.Color;

import appcode.*;

import velocity.util.*;
import velocity.*;
import velocity.sprite.*;
import velocity.sprite.ui.FPSCounter;

public class TitleScreen extends Scene {
    public TitleScreen(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here that need to be added in multiple areas.
         * Keep in mind that these sprites will still need to be added to the scene list.
         */
        /* End of reusable sprite section. */

        /* Add sprites here (in sort order from 0 - array.length) */
        /* Stop adding sprites here. */

        /* Baked collision data here (written in by hand by developer) */
        /* End baked collision data. */

        /* UI Panel here */
        sprites.add(new TitleBanner(new Point(0, 90), 0f, "Title"));
        sprites.add(new FPSCounter(new Point(3, 12), 0, "FPS", Color.green));
        sprites.add(new MemTracer(new Point(3, 26), 0, "MemTracker", Color.green));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new Camera(Point.zero));
    }
}
