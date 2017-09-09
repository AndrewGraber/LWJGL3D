package com.anzanama.lwjgl3d.Game.World;

import com.anzanama.lwjgl3d.Game.World.Chunk.Chunk;
import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;

public class WorldProvider {
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

    public static Chunk createChunkAtPos(ChunkPos pos, World world) {
        Chunk chunk = new Chunk(pos);
        world.addChunk(pos, chunk);
        return chunk;
    }
}
