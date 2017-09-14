package com.anzanama.lwjgl3d.Game.World;

import com.anzanama.lwjgl3d.Game.World.Change.WorldChangeScheduler;
import com.anzanama.lwjgl3d.Game.World.Chunk.Chunk;
import com.anzanama.lwjgl3d.Game.World.Chunk.ChunkAlreadyExistsException;
import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;

import java.util.HashMap;

/**
 * A representation of the world of your game. This is kind of self-explanatory. It would not be impossible to have
 * multiple of these running at the same time. Maybe that'd work well multi-threaded //TODO maybe
 */
public class World {
    /**
     * Name of the world - largely unused so far, but it seems like a nice thing to have.
     */
    private String name;
    /**
     * A map of all the {@link Chunk}s in the world. Different {@link Chunk}s comprise different sections of the world.
     * @see Chunk
     */
    private HashMap<ChunkPos, Chunk> chunkMap;

    /**
     * Used to schedule {@link com.anzanama.lwjgl3d.Game.World.Change.WorldChange}s. These should be used to make
     * changes to the world that will alter the {@link #chunkMap}, as it cannot be changed willy nilly during the
     * regular update cycle. Changes will be applied at the end of the current update cycle.
     */
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

    /**
     * Returns a {@link Chunk} at the given {@link ChunkPos}. If the chunk does not already exist, it will create
     * a new one.
     *
     * @param pos the {@link ChunkPos} of the chunk to get.
     * @return the {@link Chunk} at the given position.
     */
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

    /**
     * Adds a given chunk into the world at the location given. It's recommended to use the {@link WorldProvider} to
     * generate Chunks with {@link WorldProvider#createChunkAtPos(ChunkPos, World)}. Will throw a
     * {@link ChunkAlreadyExistsException} if the {@link Chunk} already exists. I'm considering just removing this.
     *
     * @param pos the {@link ChunkPos} to place the chunk at. //TODO remove this and just use {@link Chunk#getPos()}
     * @param chunk the {@link Chunk} object to add to the {@link World}.
     */
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
