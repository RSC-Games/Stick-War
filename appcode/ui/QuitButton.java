package appcode.ui;

import velocity.util.Point;
import velocity.animation.AnimStateMachine;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ui.UIButton;

public class QuitButton extends UIButton {
    AnimStateMachine anim;

    public QuitButton(Point pos) {
        super(pos, 0f, "Quit Button", "./images/ui/quit-sword/sword_quit_base.png", 
              "./images/ui/quit_hover.png");
        this.sortOrder = 1;

        this.anim = new AnimStateMachine("./anim/ui/quit_button.anim");
        this.anim.setBool("hovered", false);
    }
    
    @Override
    public void clicked() {
        System.exit(0);
    }

    @Override
    public void tick() {
        super.tick();
        anim.animTick();
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
