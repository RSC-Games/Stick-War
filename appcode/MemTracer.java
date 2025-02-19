package appcode;

import java.awt.Color;
import velocity.util.Point;
import velocity.util.Transform;
import velocity.Rect;
import velocity.sprite.ui.UIText;

public class MemTracer extends UIText {
    long heapAvail;
    long totalHeap;

    public MemTracer(Point pos, String name, Color c) {
        super(new Transform(new Rect(pos, Point.zero), 1), name, "Serif", c);
        this.heapAvail = 0L;
        this.totalHeap = 0L;
    }

    public void tick() {
        Runtime rt = Runtime.getRuntime();

        this.totalHeap = rt.totalMemory();
        this.heapAvail = this.totalHeap - rt.freeMemory();

        this.text = genHeapUsedStr();
    }

    private String genHeapUsedStr() {
        return "Memory (Avail/Total): " + (this.heapAvail / 1048576) + " / " + 
            (this.totalHeap / 1048576) + " MB (" + (this.heapAvail * 100 / this.totalHeap) + "%)";
    }

}
