package appcode;

import velocity.util.Point;
import velocity.util.Transform;
import velocity.lighting.SunLight;
import velocity.sprite.Sprite;

public class AreaLight extends Sprite {
    SunLight light;

    public AreaLight(float intensity) {
        super(new Transform(Point.zero), "AreaLight");
        this.light = new SunLight(intensity);
    }
}
