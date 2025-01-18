package appcode.tutorial;

import java.awt.event.MouseEvent;

import appcode.npc.SpeechBrain;
import appcode.ui.TextEngineManager;

import velocity.util.Point;
import velocity.InputSystem;
import velocity.Scene;
import velocity.renderer.DrawInfo;
import velocity.renderer.FrameBuffer;
import velocity.sprite.ImageSprite;

public class TutorialPerson extends ImageSprite {
    SpeechBrain brain;
    String[] text;
    int index;

    public TutorialPerson(Point pos, String image) {
        super(pos, 0f, "StoryTeller", image);
        this.sortOrder = 2;

        // TODO: Keep working on the story.
        String[] storyText = new String[] {
            // The Fang was found almost dead near the town in question for this game. Most of his memories are lost.
            "...",
            "Times are desperate.",
            "Monsters roam this world like never before.",
            "It's all we can do to prevent ourselves from being swept away.",
            "Completely erased from history.",
            "Entire villages are being destroyed daily under this deathwave.",
            "We knew we were running out of time.",
            "With our imminent demise looming, we frantically searched for the source of this endless onslaught of monsters.",
            "We took heavy losses, but we found their home.",
            "The monsters were all coming from a tower.",
            "A huge, abandoned tower.",
            "Spurred by our success, we consulted what little histories we had remaining in our village.",
            "We poured over dusty, fragile books for days.",
            "No one slept. It was too risky.",
            "We were almost out of time.",
            "What we uncovered horrified us.",
            "It was awful. If you are to help us, though, you must know.",
            "This tower, in myths lost to the mist of time, is a festering pit of evil.",
            // Reveal this part of the story through the game? Trim it down and keep the rest for later? Or leave it here?
            "Long ago, the tower belonged to a king. The tower was his keep and a visible reminder of his power.",
            "Like most fools who gain power, he became intoxicated by it.",
            "He began to summon peasants to his tower for minor slights. Once someone entered the evil tower, they never returned.",
            "Ever.",
            "He tortured those peasants.",
            "He brutally assaulted them, then when they didn't scream enough, he skinned them.",
            "Alive!",
            "Ugh. Learning this would have been enough to keep anyone away from the histories. I guess I now know why so few still remain.",
            "The king quickly tired of the torture. It wasn't satisfying enough. He wanted more.",
            // Should I keep the Master of Death or remove it for later?
            "The Master of Death, an infamous necromancer, was passing through the city at the time.",
            "The king summoned him to his royal tower. He realized that to satisfy his bloodlust, he needed to bring the tortured souls back to life.",
            "Once the Master of Death was in the throne room, at the very peak of the tower, the king asked him a fateful question.",
            "\"Will you assist me in bringing these souls back to life?\" the king asked.",
            "Necromancy was forbidden in most cities. The punishment: burning at the stake.",
            "The king's city was one of few that didn't explicitly forbid necromancy. The Master of Death knew this.",
            "And yet, he couldn't practice his art openly without scaring all the villagers away. Or worse, creating a mob.",
            "So the Master of Death remained in the throne room.",
            "\"I will,\" he replied. \"It gives me a chance to practice my art.\"",
            "\"And it gives me a chance to further assert my authority,\" the king said gleefully. \"These serfs don't seem to know who's in charge.\"",
            "\"We have a deal,\" the Master of Death said.",
            "\"We have a deal,\" the king said imperiously.",
            "The peasants were anxious. They didn't know what had transpired in the throne room, but the Master of Death had returned alive.",
            // The Master of Death allied with the king (unnamed) and they brought the peasants back to life. But it created monsters instead.
            "Then, the monsters came. There was a great battle in the lower kingdom around the keep.",
            "Some peasants managed to flee. The rest were killed in the chaos.",
            "...",
            "We need your help. We need you to enter the tower and eliminate whatever is causing this travesty.",
            "Why you? You're famous! The Fox! You're the reason why this world still exists!",
            // We can drop hints of who you are in the story. The Fox is a legendary warrior responsible for pushing back the Master of Death's
            // forces. He has kept the Darkness off the land. You are the Fox. An elite general + warrior. This game doesn't really touch on the
            // elite general side.
            "You don't remember?",
            "... At least you can still fight. Go, and bring light to this forsaken land.",
            "You're our last ray of hope. If you fall...",
            "This world will forever end in darkness..."
        };
        this.text = storyText;
        String[] storyClip = new String[] {
            // Is this necessary? Or should the story be in one long audio clip?
        };
        this.brain = new SpeechBrain("Story Teller");
        this.brain.setText(text[0]);

        // TODO: Make this a story scene and add the tutorial in the next scene while you play.
        String[] tutorialText = new String[] {
            "Huh... (Click to proceed)",
            "DEV NOTE: THIS WILL BECOME A STORY SCENE AND A TUTORIAL WILL BE ADDED IN THE GAME SCENE!",
            "Who are you? A novice?",
            "Definitely a novice. So you don't know how to play. Then I will teach you.",
            "Welcome to the world of The Last Stand.",
            "How to Move: Press the W, A, S, and D keys to move around.",
            "You may move around the map, but protect your crystal at all costs. It is the only path to victory.",
            "To attack, you click the left mouse button. Time your swings wisely for the maximum amount of damage.",
            "It will only hit enemies that are close to you, though. So be careful.",
            "Additionally, to pause the game, press ESCAPE. This is useful for when you have to go do something else really quickly and don't want to restart your run.",
            "These are all of the controls you need to know.",
            "It's up to you to build your skill and survive.",
            "Are you ready? Click to start the game!",
            "Wait! Really quick! I forgot to mention - to skip this tutorial, press the ESCAPE key.",
            "Good luck!"
        };
    }

    public void tick() {
        TextEngineManager mgr = Scene.currentScene.getSprite(TextEngineManager.class);

        if ((mgr.engineReady("MainEngine") 
            && InputSystem.clicked(MouseEvent.BUTTON1)) || index == 0) {

            // Load the game scene if we've hit the end.
            if (index == this.text.length) {
                Scene.scheduleSceneLoad("GameScene");
                return;
            }

            brain.setText(this.text[index++]);
            mgr.submitJob(brain, "MainEngine");
        }
    }
    
    @Override
    public void render(DrawInfo d, FrameBuffer fb) {
        fb.drawShaded(this.img, d);
    }
}
