package appcode.scenes;

import java.awt.Color;

import appcode.*;
import appcode.ui.FullScreenHandler;
import appcode.ui.MusicPlayer;
import appcode.ui.PauseManager;
import velocity.util.Point;
import velocity.Scene;
import velocity.sprite.Camera;
import velocity.sprite.ImageSprite;
import velocity.sprite.camera.Base2DCamera;
import velocity.sprite.collision.LineCollider;
import velocity.sprite.ui.FPSCounter;

public class GameScene extends Scene {
    public GameScene(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here that need to be added in multiple areas.
         * Keep in mind that these sprites will still need to be added to the scene list.
         */
        LocalPlayer localPlayer = new LocalPlayer(Point.zero, 50, "LocalPlayer", 
                              "./assets/sprites/player/idle.png");
        /* End of reusable sprite section. */

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new AreaLight(1));
        sprites.add(new ImageSprite(Point.zero, 0, "Background", "./assets/sprites/map/city_background.png"));
        sprites.add(new ImageSprite(Point.zero, 0, "Map", "./assets/sprites/map/full_map.png"));
        sprites.add(localPlayer);
        /* Stop adding sprites here. */

        /* Baked collision data here (written in by hand by developer) */
        sprites.add(new LineCollider(new Point(1540, -1068), uuid, name, new Point(-1540, -1068)));
        sprites.add(new LineCollider(new Point(-1540, -1068), uuid, name, new Point(-1540, 1068)));
        sprites.add(new LineCollider(new Point(-1540, 1068), uuid, name, new Point(1540, 1068)));
        sprites.add(new LineCollider(new Point(1540, -1068), uuid, name, new Point(1540, 1068)));
        /* End baked collision data. */

        /* UI Panel here */
        sprites.add(new MusicPlayer("Game Music", "./assets/audio/map_theme_1.wav"));
        sprites.add(new FPSCounter(new Point(3, 12), 0, "FPS", Color.green));
        sprites.add(new MemTracer(new Point(3, 26), 0, "MemTracker", Color.orange));
        sprites.add(new PauseManager());
        sprites.add(new FullScreenHandler("FSH"));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new Base2DCamera(Point.zero, localPlayer));
    }
}
