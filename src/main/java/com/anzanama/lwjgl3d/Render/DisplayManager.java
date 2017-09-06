package com.anzanama.lwjgl3d.Render;

import com.anzanama.lwjgl3d.Game.Game;
import com.anzanama.lwjgl3d.Util.Config;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static long lastFrameTime;
    private static float delta;

    public static void createDisplay() {
        ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
        try {
            Display.setDisplayMode(new DisplayMode(Config.getInt("screen_width"), Config.getInt("screen_height")));
            Display.create(new PixelFormat(), attribs);
            Display.setTitle("LWJGL3D");
        } catch(LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, Config.getInt("screen_width"), Config.getInt("screen_height"));
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
        Display.sync(Config.getInt("fps_cap"));
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = currentFrameTime - lastFrameTime;
        lastFrameTime = currentFrameTime;
    }

    public static float getFrameTimeSeconds() {
        return delta;
    }

    public static void closeDisplay() {
        Display.destroy();
    }

    private static long getCurrentTime() {
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
