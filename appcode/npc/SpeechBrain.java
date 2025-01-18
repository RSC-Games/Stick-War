package appcode.npc;

public class SpeechBrain {
    String npcName;
    String text;

    public SpeechBrain(String name) {
        this.npcName = name;
        this.text = "";
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
