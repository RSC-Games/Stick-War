package render_test;

import java.awt.Color;
import java.awt.Font;
import velocity.util.*;
import velocity.sprite.ui.UIText;

public class DrawText extends UIText {
    public DrawText(Point pos, int rot, String name, String text, Color c) {
        super(pos, rot, name, Font.SERIF, c);
        this.text = text;
    }
}
