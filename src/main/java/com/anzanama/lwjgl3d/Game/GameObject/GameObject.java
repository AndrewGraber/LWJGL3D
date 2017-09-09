package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.IPositioned;

public abstract class GameObject implements IPositioned {
    public abstract void update(float delta);
    public abstract void render(float delta);
}
