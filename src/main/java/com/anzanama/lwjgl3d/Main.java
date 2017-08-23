package main.java.com.anzanama.lwjgl3d;

import main.java.com.anzanama.lwjgl3d.Game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
    public static void main(String[] args) {
        initDisplay();
        Game game = new Game();
        game.initialize();
        game.loop();
        game.shutdown();
        cleanUp();
    }

    public static void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
            Keyboard.create();
            Mouse.create();
            Mouse.setGrabbed(true);
        } catch(LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanUp() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
}
