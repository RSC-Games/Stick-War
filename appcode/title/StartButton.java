package appcode.title;

import velocity.util.Point;
import velocity.Scene;
import velocity.animation.AnimStateMachine;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.UIButton;

public class StartButton extends UIButton {
    AnimStateMachine anim;

    public StartButton(Point pos) {
        super(pos, 0f, "StartButton", "./images/ui/start-sword/sword_start_base.png", 
              "./images/ui/start-sword/sword_start_base.png");

        this.anim = new AnimStateMachine("./anim/ui/start_button.anim");
        this.anim.setBool("hovered", false);
    }

    @Override
    public void tick() {
        super.tick();
        anim.animTick();
    }
    
    @Override
    public void clicked() {
        Scene.scheduleSceneLoad("TutorialScene");
    }

    @Override
    public void onHover() {
        anim.setBool("hovered", true);
    }

    @Override
    public void onMouseLeave() {
        anim.setBool("hovered", false);
    }

    @Override
    public void renderUI(DrawInfo d, FrameBuffer fb) {
        fb.blit(anim.getDrawFrame(), d);
    }
}
