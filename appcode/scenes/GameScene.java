package appcode.scenes;

import java.awt.Color;

import appcode.*;
import appcode.environment.*;
import appcode.ui.EXPTracker;
import appcode.ui.FullScreenHandler;
import appcode.ui.LevelTracker;
import appcode.ui.MusicPlayer;
import appcode.ui.PauseManager;
import velocity.util.Point;
import velocity.Scene;
import velocity.sprite.collision.LineCollider;
import velocity.sprite.ui.FPSCounter;

public class GameScene extends Scene {
    public GameScene(String name, int uuid) {
        super(name, uuid);
        sprites.clear();

        /* Add sprites here that need to be added in multiple areas.
         * Keep in mind that these sprites will still need to be added to the scene list.
         */
        Player p = new Player(Point.zero, 0, "Player Knight", 
                              "./images/player/player_front_1.png");
        /* End of reusable sprite section. */

        /* Add sprites here (in sort order from 0 - array.length) */
        sprites.add(new GameManager());
        sprites.add(new TiledBackgroundRenderer(Point.zero, "./images/bg/bgtile.png"));
        sprites.add(new Torch(new Point(300, -80)));
        sprites.add(new Torch(new Point(-300, -80)));
        //sprites.add(new AreaLight(0.25f));
        sprites.add(p);
        sprites.add(new PlayerLight(Point.zero, 0f, "Player Light"));
        sprites.add(new ScreenViewLight(Point.zero, 0f, "Screen Lighting"));
        sprites.add(new Sword());

        // Enemy Spawn Locations.
        sprites.add(new EnemySpawnPoint(new Point(1400, -1000)));
        sprites.add(new EnemySpawnPoint(new Point(1400, -500)));
        sprites.add(new EnemySpawnPoint(new Point(1400, 0)));
        sprites.add(new EnemySpawnPoint(new Point(1400, 500)));
        sprites.add(new EnemySpawnPoint(new Point(1400, 1000)));
        sprites.add(new EnemySpawnPoint(new Point(-1400, -1000)));
        sprites.add(new EnemySpawnPoint(new Point(-1400, -500)));
        sprites.add(new EnemySpawnPoint(new Point(-1400, 0)));
        sprites.add(new EnemySpawnPoint(new Point(-1400, 500)));
        sprites.add(new EnemySpawnPoint(new Point(-1400, 1000)));
        sprites.add(new EnemySpawnPoint(new Point(0, -1000)));
        sprites.add(new EnemySpawnPoint(new Point(0, 1000)));
        sprites.add(new EnemySpawnerSystem());
        /* Stop adding sprites here. */

        /* Baked collision data here (written in by hand by developer) */
        sprites.add(new LineCollider(new Point(1540, -1068), uuid, name, new Point(-1540, -1068)));
        sprites.add(new LineCollider(new Point(-1540, -1068), uuid, name, new Point(-1540, 1068)));
        sprites.add(new LineCollider(new Point(-1540, 1068), uuid, name, new Point(1540, 1068)));
        sprites.add(new LineCollider(new Point(1540, -1068), uuid, name, new Point(1540, 1068)));
        /* End baked collision data. */

        /* UI Panel here */
        sprites.add(new MusicPlayer("Game Music", "./audio/music/level1-comedic.wav"));
        sprites.add(new LevelTracker(new Point(3, -5), 0, "Level Tracker", Color.white));
        sprites.add(new EXPTracker(new Point(3, -30), 0, "EXP Tracker", Color.white));
        sprites.add(new FPSCounter(new Point(3, 12), 0, "FPS", Color.green));
        sprites.add(new MemTracer(new Point(3, 26), 0, "MemTracker", Color.orange));
        sprites.add(new PauseManager());
        sprites.add(new FullScreenHandler("FSH"));
        /* End UI Panel */

        // Camera required for rendering. DO NOT FORGET!
        sprites.add(new BoundedCamera(Point.zero, p));
    }
}
