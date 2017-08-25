package com.anzanama.lwjgl3d;

import com.anzanama.lwjgl3d.Game.Game;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {
    private static Game game;

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        try {
            Keyboard.create();
            Mouse.create();
            Mouse.setGrabbed(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        game = new Game();
        game.initialize();
        game.loop();
        game.shutdown();
        cleanUp();
    }

    public static void cleanUp() {
        DisplayManager.closeDisplay();
        Keyboard.destroy();
        Mouse.destroy();
    }

    public static Game getGame() {
        return game;
    }
}
