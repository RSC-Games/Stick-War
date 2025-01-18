package appcode.enemies;

import velocity.util.Point;

public class Wolf extends Enemy {
    public Wolf(Point pos) {
        super(pos, "Wolf", "./images/enemies_legacy/wolf.png", 
              250, 7, "./audio/sfx/enemy_killed.wav");
    }
    
}
