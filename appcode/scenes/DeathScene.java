package appcode.scenes;

import appcode.ui.DeathText;
import appcode.ui.FullScreenHandler;
import appcode.ui.MusicPlayer;
import appcode.ui.QuitButton;
import appcode.ui.Tombstone;
import appcode.ui.YesButton;
import velocity.util.Point;
import velocity.Scene;
import velocity.sprite.Camera;

public class DeathScene extends Scene {
    public DeathScene(String name, int uuid) {
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
        sprites.add(new MusicPlayer("Game Music", "./audio/music/death_theme.wav"));
        sprites.add(new Tombstone(Point.zero));
        sprites.add(new DeathText(new Point(0, 85)));
        sprites.add(new YesButton(new Point(0, 20)));
        sprites.add(new QuitButton(new Point(0, 85)));
        sprites.add(new FullScreenHandler("FSH"));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new Camera(Point.zero));
    }
}

