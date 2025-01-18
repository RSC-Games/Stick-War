package appcode;

import velocity.util.GlobalRandom;
import velocity.util.Point;
import velocity.animation.AnimStateMachine;
import velocity.lighting.PointLight;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ImageSprite;

public class Torch extends ImageSprite {
    AnimStateMachine anim;
    PointLight light;
    float rad = 200;

    public Torch(Point pos) {
        super(pos, 0f, "Torch", "./images/torch/torch_0.png");
        this.sortOrder = 1;

        this.anim = new AnimStateMachine("./anim/torch.anim");
        this.anim.setString("state", "default");
        this.light = new PointLight(this.pos.getPos(), rad, 1.25f);
    }

    @Override
    public void tick() {
        this.anim.animTick();

        Point nPos = pos.getPos().add(new Point(
            GlobalRandom.randint(-5, 5), 
            GlobalRandom.randint(-5, 5)
        ));

        this.light.setPos(nPos);
        this.light.setRadius(GlobalRandom.randint(-5, 5) + rad);
    }

    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(this.anim.getDrawFrame(), d);
    }
    
    public void destroy() {
        this.light.delete();
    }
}
