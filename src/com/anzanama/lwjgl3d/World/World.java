package com.anzanama.lwjgl3d.World;

import com.anzanama.lwjgl3d.World.Change.CreateChunkWC;
import com.anzanama.lwjgl3d.World.Change.WorldChangeScheduler;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;

import java.util.HashMap;

public class World {
    private String name;
    private HashMap<ChunkPos, Chunk> chunkMap;
    private WorldChangeScheduler changeScheduler;

    public World(String name) {
        this.name = name;
        this.chunkMap = new HashMap<>();
        this.changeScheduler = new WorldChangeScheduler();
    }

    public String getName() {
        return name;
    }

    public HashMap<ChunkPos, Chunk> getChunkMap() {
        return this.chunkMap;
    }

    public Chunk getChunk(ChunkPos pos) {
        if(chunkMap.containsKey(pos)) {
            return chunkMap.get(pos);
        } else {
            WorldProvider.createChunkAtPos(pos, this);
            return chunkMap.get(pos);
        }
    }

    public WorldChangeScheduler getChangeScheduler() {
        return this.changeScheduler;
    }

    public void addChunk(ChunkPos pos, Chunk chunk) {
        try {
            if (!chunkMap.containsKey(pos)) {
                chunkMap.put(pos, chunk);
            } else {
                throw new ChunkAlreadyExistsException(pos, this);
            }
        } catch(ChunkAlreadyExistsException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
