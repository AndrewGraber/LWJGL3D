package com.anzanama.lwjgl3d.Game.World.Chunk;

import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.World;

public class ChunkAlreadyExistsException extends Exception {
    public ChunkAlreadyExistsException(ChunkPos pos, World world) {
        super("Tried to create a chunk at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ") in World \"" +
            world.getName() + "\" that already existed!");
    }
}
