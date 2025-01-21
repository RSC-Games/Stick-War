package appcode.ui;

import velocity.util.Point;
import velocity.util.Transform;
import velocity.sprite.Sprite;
import velocity.audio.MusicClip;

public class MusicPlayer extends Sprite {
    MusicClip clip;

    public MusicPlayer(String name, String songpath) {
        super(new Transform(Point.zero), "MusicPlayer");
        this.clip = new MusicClip(songpath, true);
    }

    public void init() {
        this.clip.play();
    }

    @Override
    public void delete() {
        this.clip.remove();
    }
}
