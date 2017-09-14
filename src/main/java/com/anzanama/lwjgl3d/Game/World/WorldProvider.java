package com.anzanama.lwjgl3d.Game.World;

import com.anzanama.lwjgl3d.Game.World.Chunk.Chunk;
import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;

/**
 * Use in tandem with a {@link World}. This will generate chunks for the world and provide them to it.
 * Theoretically, you only need one of these for all the worlds you have.
 */
public class WorldProvider {

    /**
     * Temporary - creates a world of 16 chunks. //TODO center around the origin
     *
     * @param name the name of the world to create
     * @return the World object.
     */
    public static World createNewWorld(String name) {
        World world = new World(name);

        //TEMPORARY
        //Generates a section of 16 Chunks and places them in the world
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                for(int k=0; k<4; k++) {
                    ChunkPos pos = new ChunkPos(i, j, k);
                    Chunk chunk = new Chunk(pos);
                    world.addChunk(pos, chunk);
                }
            }
        }
        return world;
    }

    /**
     * Creates a chunk at a certain position and adds it to the world.
     *
     * @param pos the {@link ChunkPos} to place the chunk at.
     * @param world the world to place the chunks in.
     * @return the {@link Chunk} that was just created.
     */
    public static Chunk createChunkAtPos(ChunkPos pos, World world) {
        Chunk chunk = new Chunk(pos);
        world.addChunk(pos, chunk);
        return chunk;
    }
}
