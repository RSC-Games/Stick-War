package appcode.ui;

import velocity.util.Point;
import velocity.Scene;
import velocity.sprite.ui.UIButton;

public class YesButton extends UIButton {
    public YesButton(Point pos) {
        super(pos, 0f, "Yes Button", "./images/ui/yes.png", 
              "./images/ui/yes_hover.png");
        this.sortOrder = 1;
    }
    
    @Override
    public void clicked() {
        Scene.scheduleSceneLoad("GameScene");
    }

    @Override
    public void onHover() {}

    @Override
    public void onMouseLeave() {}
}
