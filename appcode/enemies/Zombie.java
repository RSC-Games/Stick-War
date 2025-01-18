package appcode.enemies;

import velocity.audio.AudioClip;
import java.util.ArrayList;
import java.util.Arrays;

import velocity.util.GlobalRandom;
import velocity.util.Point;
import velocity.util.Timer;

public class Zombie extends Enemy {
    ArrayList<AudioClip> noises;
    Timer noiseTimer;
    
    public Zombie(Point pos) {
        super(pos, "Zombie", "./images/enemies_legacy/zombie.png", 
              500, 4, "./audio/sfx/enemy_killed.wav");
        this.col.setWH((int)(this.col.getW() * 0.8), this.col.getH() - 15);

        noises = new ArrayList<AudioClip>(Arrays.asList(new AudioClip[] {
            new AudioClip("./audio/sfx/zombie_brains_1.wav"),
            new AudioClip("./audio/sfx/zombie_brains_2.wav"),
            new AudioClip("./audio/sfx/zombie_brains_3.wav"),
            new AudioClip("./audio/sfx/zombie_food_brains_2.wav"),
            new AudioClip("./audio/sfx/zombie_food_brains.wav"),
            new AudioClip("./audio/sfx/zombie_food_i.wav"),
            new AudioClip("./audio/sfx/zombie_food_immediately.wav"),
            new AudioClip("./audio/sfx/zombie_food_want.wav"),
            new AudioClip("./audio/sfx/zombie_give_me_brains.wav"),
            new AudioClip("./audio/sfx/zombie_groan.wav")
        }));

        this.noiseTimer = new Timer(4000, false);
    }

    public void tick() {
        super.tick();

        if (noiseTimer.tick()) {
            int clip = GlobalRandom.randint(0, noises.size());
            noises.get(clip).play();
            noiseTimer.setDuration(GlobalRandom.randint(2000, 6000));
            noiseTimer.reset();
        }
    }
}
