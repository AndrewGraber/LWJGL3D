package com.anzanama.lwjgl3d;

import com.anzanama.lwjgl3d.Game.Game;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import com.anzanama.lwjgl3d.Util.Config;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {
    private static Game game;
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static void main(String[] args) {
        try {
            if (OS.contains("windows")) {
                addLibraryPath("./libs/lwjgl/native/windows/");
            } else if (OS.contains("linux")) {
                addLibraryPath("./libs/lwjgl/native/linux/");
            } else if (OS.contains("mac os")) {
                addLibraryPath("./libs/lwjgl/native/macosx/");
            } else if (OS.contains("freebsd")) {
                addLibraryPath("./libs/lwjgl/native/freebsd/");
            } else if (OS.contains("sun os") || OS.contains("sunos") || OS.contains("solaris")) {
                addLibraryPath("./libs/lwjgl/native/solaris/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Config.initialize("config.txt");

        DisplayManager.createDisplay();

        try {
            Keyboard.create();
            Controllers.create();
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
        Controllers.destroy();
    }

    public static Game getGame() {
        return game;
    }

    public static void addLibraryPath(String pathToAdd) throws Exception{
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);

        //get array of paths
        final String[] paths = (String[])usrPathsField.get(null);

        //check if the path to add is already present
        for(String path : paths) {
            if(path.equals(pathToAdd)) {
                return;
            }
        }

        //add the new path
        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length-1] = pathToAdd;
        usrPathsField.set(null, newPaths);
    }
}
