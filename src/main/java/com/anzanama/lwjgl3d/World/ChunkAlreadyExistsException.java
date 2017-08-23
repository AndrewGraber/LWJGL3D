package main.java.com.anzanama.lwjgl3d.World;

import main.java.com.anzanama.lwjgl3d.World.Position.ChunkPos;

public class ChunkAlreadyExistsException extends Exception {
    public ChunkAlreadyExistsException(ChunkPos pos, World world) {
        super("Tried to create a chunk at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ") in World \"" +
            world.getName() + "\" that already existed!");
    }
}
