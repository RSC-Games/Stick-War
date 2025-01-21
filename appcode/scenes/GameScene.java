package appcode.scenes;

import java.awt.Color;

import appcode.*;
import appcode.environment.MapBackground;
import appcode.ui.FullScreenHandler;
import appcode.ui.MusicPlayer;
import velocity.util.Point;
import velocity.util.Transform;
import velocity.Rect;
import velocity.Scene;
import velocity.sprite.ImageSprite;
import velocity.sprite.camera.Base2DCamera;
import velocity.sprite.collision.LineCollider;
import velocity.sprite.collision.RectCollider;
import velocity.sprite.ui.FPSCounter;

public class GameScene extends Scene {
    public GameScene(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here that need to be added in multiple areas.
         * Keep in mind that these sprites will still need to be added to the scene list.
         */
        LocalPlayer localPlayer = new LocalPlayer(Point.zero, "LocalPlayer", 
                              "./assets/sprites/player/idle.png");
        /* End of reusable sprite section. */

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new AreaLight(1));
        sprites.add(new MapBackground());
        sprites.add(new ImageSprite(new Transform(Point.zero), "Map", "./assets/sprites/map/full_map.png"));
        sprites.add(localPlayer);
        /* Stop adding sprites here. */

        /* Baked collision data here (written in by hand by developer) */
        // World borders.
        sprites.add(new LineCollider(new Point(-1576, -518), "BORDER_LEFT", new Point(-1576, 1572)));
        sprites.add(new LineCollider(new Point(-1576, 1572), "BORDER_BOTTOM", new Point(1279, 1572)));
        sprites.add(new LineCollider(new Point(1279, 1616), "BORDER_RIGHT", new Point(1279, -518)));
        sprites.add(new LineCollider(new Point(1279, -518), "BORDER_TOP", new Point(-1576, -518)));

        // Platform geometry
        sprites.add(new RectCollider(new Transform(new Rect(212, 1322, 168, 43)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-508, 1195, 869, 45)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-1295, 1380, 237, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-1415, 879, 319, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-276, 762, 551, 49)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-1493, 473, 162, 45)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-1060, 468, 268, 45)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-508, 1195, 869, 45)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-954, 186, 646, 43)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-1284, -242, 582, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-315, 373, 1307, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(-362, -241, 650, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(22, -155, 44, 43)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(627, -242, 779, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(22, 154, 646, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(394, 489, 191, 43)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(824, 628, 298, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(573, 1045, 329, 42)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(340, 850, 228, 44)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(908, 1331, 338, 43)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(995, 388, 46, 1214)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(717, 1319, 41, 504)), "PLATFORM"));
        sprites.add(new RectCollider(new Transform(new Rect(431, 972, 42, 201)), "PLATFORM"));

        //sprites.add(new LineCollider(new Point(1540, -1068), name, new Point(-1540, -1068)));
        //sprites.add(new LineCollider(new Point(-1540, -1068), name, new Point(-1540, 1068)));
        //sprites.add(new LineCollider(new Point(-1540, 1068), name, new Point(1540, 1068)));
        //sprites.add(new LineCollider(new Point(1540, -1068), name, new Point(1540, 1068)));
        /* End baked collision data. */

        /* UI Panel here */
        sprites.add(new MusicPlayer("Game Music", "./assets/audio/map_theme_1.wav"));
        sprites.add(new FPSCounter(new Transform(new Point(3, 12)), "FPS", Color.green));
        sprites.add(new MemTracer(new Point(3, 26), "MemTracker", Color.orange));
        sprites.add(new FullScreenHandler("FSH"));

        //sprites.add(new MousePointerTracker(new Transform(new Point(3, 40)), "Pointer", Color.white));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new Base2DCamera(Point.zero, localPlayer));
        //sprites.add(new DebugFreeLookCamera(Point.zero));
    }
}
