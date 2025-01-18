package appcode.scenes;

import appcode.ui.*;

import java.awt.Color;

import appcode.*;
import appcode.title.*;
import appcode.tutorial.*;

import velocity.util.*;
import velocity.*;
import velocity.sprite.*;
import velocity.sprite.ui.FPSCounter;

public class StoryScene extends Scene {
    public StoryScene(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here that need to be added in multiple areas.
         * Keep in mind that these sprites will still need to be added to the scene list.
         */
        /* End of reusable sprite section. */

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new BackgroundTiling(Point.zero, "./images/bg/bgtile.png"));
        sprites.add(new BGWallTile(Point.zero, "./images/bg/bgwalltile.png"));
        sprites.add(new Torch(new Point(300, -80)));
        sprites.add(new Torch(new Point(-300, -80)));
        sprites.add(new AreaLight(0.1f));
        sprites.add(new TutorialPerson(new Point(0, -150), "./images/tutorial_knight.png"));
        /* Stop adding sprites here. */

        /* Baked collision data here (written in by hand by developer) */
        /* End baked collision data. */

        /* UI Panel here */
        sprites.add(new TextEngineManager());
        sprites.add(new SkipKeyListener());
        sprites.add(new MusicPlayer("Title Music", "./audio/music/tutorial-eerie.wav"));
        sprites.add(new TextEngine("bottom", new Point(550, 100), "MainEngine", 
                    "Serif", Color.white));
        sprites.add(new MemTracer(new Point(3, 26), 0, "MemTracker", Color.green));
        sprites.add(new FPSCounter(new Point(3, 12), 0, "FPS", Color.green));
        sprites.add(new FullScreenHandler("FSH"));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new Camera(Point.zero));
    }
}
