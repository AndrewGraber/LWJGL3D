package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.World.Position.IPositioned;

public abstract class GameObject implements IPositioned {
    public abstract void update(float delta);
    public abstract void render(float delta);
}
