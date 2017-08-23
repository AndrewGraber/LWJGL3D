package main.java.com.anzanama.lwjgl3d.GameObject;

import main.java.com.anzanama.lwjgl3d.World.Position.IPositioned;

public abstract class GameObject implements IPositioned {
    public abstract void update();
    public abstract void render();
}
