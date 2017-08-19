package com.anzanama.lwjgl3d.World.Change;

import com.anzanama.lwjgl3d.World.Change.WorldChange;
import com.anzanama.lwjgl3d.World.World;
import com.sun.istack.internal.NotNull;

import java.util.Stack;

public class WorldChangeScheduler {
    private Stack<WorldChange> changeStack;

    public WorldChangeScheduler() {
        this.changeStack = new Stack<>();
    }

    @NotNull
    public void addChange(WorldChange change) {
        changeStack.push(change);
    }

    public void makeChanges(World world) {
        while(changeStack.size() != 0) {
            System.out.println("Making World Change");
            WorldChange change = changeStack.pop();
            change.makeChange(world);
        }
    }

}
