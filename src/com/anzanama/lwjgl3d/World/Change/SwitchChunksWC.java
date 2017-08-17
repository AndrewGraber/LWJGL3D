package com.anzanama.lwjgl3d.World.Change;

import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.World;

public class SwitchChunksWC extends WorldChange {
    ChunkPos prevPos, newPos;
    GameObject object;

    public SwitchChunksWC(ChunkPos prevPos, ChunkPos newPos, GameObject object) {
        this.prevPos = prevPos;
        this.newPos = newPos;
        this.object = object;
    }

    @Override
    public void makeChange(World world) {
        world.getChunk(prevPos).removeGameObject(object);
        world.getChunk(newPos).addGameObject(object);
    }
}
