package appcode.environment;

import java.util.ArrayList;

import appcode.GameManager;
import appcode.enemies.Cactus;
import appcode.enemies.Enemy;
import appcode.enemies.Executioner;
import appcode.enemies.Lich;
import appcode.enemies.Wolf;
import appcode.enemies.Zombie;
import velocity.util.GlobalRandom;
import velocity.util.Point;
import velocity.util.Timer;
import velocity.Scene;
import velocity.sprite.Sprite;

public class EnemySpawnerSystem extends Sprite {
    ArrayList<Point> spawnLocs = new ArrayList<Point>();
    GameManager mgr;
    Timer spawnTimer;

    public EnemySpawnerSystem() {
        super(Point.zero, 0f, "Spawn System");
    }
    
    public void init() {
        // Collect all of the spawn points and delete them from the scene.
        ArrayList<EnemySpawnPoint> points = Scene.currentScene.getSprites(EnemySpawnPoint.class);

        for (EnemySpawnPoint point : points) {
            this.spawnLocs.add(point.pos.getPos());
            Scene.currentScene.removeSprite(point);
        }

        this.spawnTimer = new Timer(6000, true);
        this.mgr = Scene.currentScene.getSprite(GameManager.class);
    }

    public void tick() {
        if (mgr.running && this.spawnTimer.tick()) {
            Point spawnPoint = spawnLocs.get(GlobalRandom.randint(0, spawnLocs.size()));

            Enemy enemy = makeNewEnemy(spawnPoint);
            Scene.currentScene.addSprite(enemy);
        }
    }

    public void freezeGame() {
        this.mgr.running = false;
        this.mgr.playerDied();
    }

    public void updateTimer(int level) {
        this.spawnTimer = new Timer((int)(6000 * (1 - (0.1f * level))), true);
    }

    private Enemy makeNewEnemy(Point p) {
        // Current crash patch disabled for full-range testing.
        int enemyType = Math.min(GlobalRandom.randint(0, mgr.player.level), 9);

        // TODO: Enemies left to add:
        // Clown
        // Skull knight
        // Shinobi
        // Evil child
        // Anubis
        switch (enemyType) {
            case 0:
                return new Cactus(p);
            case 1:
                return new Lich(p);
            case 2:
                return new Wolf(p);
            case 3:
                return new Zombie(p);
            case 4:
                return new Executioner(p); // :D
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
        }

        return null;
    }
}
