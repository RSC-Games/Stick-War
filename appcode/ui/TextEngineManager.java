package appcode.ui;

import java.util.HashMap;

import appcode.npc.SpeechBrain;
import velocity.util.Point;
import velocity.sprite.Sprite;

public class TextEngineManager extends Sprite {
    private HashMap<String, TextEngine> textEngines = new HashMap<String, TextEngine>();

    /**
     * Internal constructor. Create and initialize a text engine.
     */
    public TextEngineManager() {
        super(Point.zero, 0f, "TextEngineManager");
    }

    /**
     * Register a text engine with the engine context manager.
     * 
     * @param id Engine external ID
     * @param engine Actual engine to use.
     */
    public void registerTextEngine(String id, TextEngine engine) {
        textEngines.put(id, engine);
    }

    /** 
     * Identify whether an engine is free or not. This is crucial for avoiding
     * sending another job when the queue is full (size 1)
     * 
     * @param engineID Engine to query.
     * @return Engine state.
     */
    public boolean engineReady(String engineID) {
        return this.textEngines.get(engineID).ready();
    }

    /**
     * Submit a job to a requested text engine.
     * 
     * @param brain The brain with the requested text.
     * @param engineID Engine to send the job to.
     */
    public void submitJob(SpeechBrain brain, String engineID) {
        TextEngine engine = this.textEngines.get(engineID);

        if (engine == null)
            throw new IllegalArgumentException("Cannot get engine of name " + engineID);

        if (!engine.ready())
            throw new EngineInUseException("Cannot push job to engine that's already in use!");

        engine.startJob(brain);
    }
}
