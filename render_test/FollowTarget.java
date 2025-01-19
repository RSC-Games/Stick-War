package render_test;

import velocity.util.*;
import velocity.sprite.*;

public class FollowTarget extends Sprite {
    // Define movement for the camera to follow on a (preferably) parametric function.
    public FollowTarget(Point pos, int rot, String name) {
        super(pos, rot, name);
    }
}
