package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.GameObject.*;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import com.anzanama.lwjgl3d.Render.GameRenderer;
import com.anzanama.lwjgl3d.Render.Light;
import com.anzanama.lwjgl3d.Render.Model.*;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.World;
import com.anzanama.lwjgl3d.World.WorldProvider;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This is the main class of the game. It is where you will base all of the logic and processes of your game off of.
 * To make your game, you should make a class that extends this, then replace the line that instantiates the game
 * object in {@link com.anzanama.lwjgl3d.Main#main(String[])}
 *
 * @author Andrew Graber
 * @version 9/8/2017
 */
public abstract class Game {
    /**
     * This is your way to exit the game when you believe it is complete and ready to close. All you need to do
     * is set this boolean to true, and the next game loop, the process will exit out of the game and run the
     * {@link #shutdown()} method.
     */
    protected boolean stopGameFlag;
    protected GameRenderer renderer;
    protected ModelLoader modelLoader;
    /**
     * Every game needs to have a {@link World}. How would you even make a 3D game that doesn't take place in
     * some sort of world? Beats me.
     */
    protected World world;
    /**
     * Every game needs to have a {@link CameraObject}. Without one, your screen would just be complete blackness
     * and that's no fun at all. Make an interesting game without a camera. I dare you. And no, text-based games
     * don't count, because you wouldn't need to use this library if you were doing so.
     *
     * Don't forget to initalize this in your implementation of the initialize() method.
     */
    protected CameraObject camera;

    /**
     * Even if you've got a {@link CameraObject}, you really won't be having much fun playing in the dark. Here's a
     * {@link Light} to help prevent that. I want to help make sure you're having as much fun as possible.
     */
    protected Light light;

    /**
     * This method is the first part of your game that will be run. It is where all of the objects that you need for
     * your game to boot up and render the first frame without crashing should go. Of course, you can always add
     * more things in later. You're probably going to want to make sure you call super.initialize() in your own game.
     *
     * !#!#!# IMPORTANT NOTE: DON'T FORGET TO INSTANTIATE THE {@link #camera} IN YOUR OWN IMPLEMENTATION OF THIS METHOD #!#!#!
     */
    public void initialize() {
        renderer = new GameRenderer();
        modelLoader = new ModelLoader();
        world = WorldProvider.createNewWorld("world");
        // Initialize the camera! (and the light)
    }

    /**
     * This method is where 99% of your game will be run. There is a default implementation of it in this class,
     * because chances are that you're never going to need to change it. Almost every single game ever has this
     * loop in some form or another.
     *
     * It calls {@link #update(float)}, then {@link #render(float)}. {@link #update(float)} is where you will want to
     * place all your game logic and such (the kind of stuff that the server would calculate if you had multiplayer).
     * The {@link #render(float)} is where all your code for rendering your game will go.
     */
    public void loop() {
        while(!Display.isCloseRequested() && !stopGameFlag) {
            update(DisplayManager.getFrameTimeSeconds());
            render(DisplayManager.getFrameTimeSeconds());
        }
    }

    /**
     * This is the method where all of your code that relates to the objects inside your world should be run. You will
     * use this to receive input, change the position of your player, create new worlds, etc.. Basically, everything
     * that is not specifically related to the rendering of your world should go here. All your data structures,
     * calculations, world generation, etc.
     *
     * The default implementation iterates through all of the {@link GameObject}s in your {@link World}. I highly recommend
     * using this implementation along with any other things you may need to do. Ideally, you won't even need to
     * override this method, because all of your updating will be done in {@link GameObject}s. That's the beauty
     * of the design of this engine. All the {@link GameObject}s that are inside {@link Chunk}s inside your {@link World}
     * will be automatically updated and rendered. There is no need for you to track them yourself.
     *
     * @param delta the time (in seconds) between frames. Use this to ensure that all the time-based components of
     *              your game are properly implemented (i.e.: speed, timers, etc.)
     */
    public void update(float delta) {
        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.update(delta);
            }
        }

        world.getChangeScheduler().makeChanges(world);
    }

    /**
     * This is the method where all of your code that relates to drawing something onto your screen should go. All the
     * changes that were made to the different objects in your world will be made clear here, as this is where the
     * rendering of those changes comes into place. If it's to show up on the screen, it should be done here.
     *
     * The default implementation iterates through all of the {@link GameObject}s in your {@link World}. I highly recommend
     * using this implementation along with any other things you may need to do. Ideally, you won't even need to
     * override this method, because all of your rendering will be done in {@link GameObject}s. That's the beauty
     * of the design of this engine. All the {@link GameObject}s that are inside {@link Chunk}s inside your {@link World}
     * will be automatically updated and rendered. There is no need for you to track them yourself.
     *
     * @param delta the time (in seconds) between frames. Use this to ensure that all the time-based components of
     *              your game are properly implemented.
     */
    public void render(float delta) {
        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.render(delta);
            }
        }

        renderer.render(light, camera);
        DisplayManager.updateDisplay();
    }

    /**
     * This is the cleanup that your game should preform. It will run just before the process stops and the window
     * closes. Therefore, it can be used to do things like save the state of your world to a file.
     */
    public void shutdown() {
        modelLoader.cleanUp();
        renderer.cleanUp();
    }

    /**
     * A simple getter for the {@link #renderer}
     *
     * @return the {@link GameRenderer} object {@link #renderer}
     */
    public GameRenderer getRenderer() {
        return renderer;
    }
}
