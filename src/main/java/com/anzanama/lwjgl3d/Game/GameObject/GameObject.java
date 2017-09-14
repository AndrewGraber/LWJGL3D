package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.IPositioned;

/**
 * The base class for all objects inside a world. This includes players, walls, enemies, cameras, etc.
 *
 * Use children of this class for any kind of object you'd like to place into your world.
 */
public abstract class GameObject implements IPositioned {
    /**
     * Updates the object.
     *
     * @param delta the time between frames (in seconds)
     */
    public abstract void update(float delta);

    /**
     * Renders the object.
     *
     * @param delta the time between frames (in seconds)
     */
    public abstract void render(float delta);
}
