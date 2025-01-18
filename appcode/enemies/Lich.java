package appcode.enemies;

import velocity.util.Point;

public class Lich extends Enemy {

    public Lich(Point pos) {
        super(pos, "Lich", "./images/enemies_legacy/lich.png", 
              250, 4, "./audio/sfx/enemy_killed.wav");
    }
    
}
