package appcode.enemies;

import velocity.util.Point;

public class Cactus extends Enemy {
    public Cactus(Point pos) {
        super(pos, "Cactus", "./images/enemies_legacy/cactus.png", 
              100, 3, "./audio/sfx/enemy_killed.wav");
        this.col.setWH(new Point(this.pos.getW() / 2, (int)(this.pos.getH() * 0.95f)));
    }
}
