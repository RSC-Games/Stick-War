package appcode.enemies;

import velocity.util.Point;

public class Executioner extends Enemy {
    public Executioner(Point pos) {
        super(pos, "Executioner", "./images/enemies_legacy/executioner.png", 
              300, 8, "./audio/sfx/enemy_killed.wav");
        this.col.setWH(new Point(this.pos.getW() / 2, (int)(this.pos.getH() * 0.95f)));
    }
}