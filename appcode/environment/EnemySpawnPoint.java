package appcode.environment;

import velocity.util.Point;
import velocity.sprite.Sprite;

// Basic entity spawning point. Only a transform.
public class EnemySpawnPoint extends Sprite {
    public EnemySpawnPoint(Point pos) {
        super(pos, 0f, "Spawn Location");
    }
    
}
