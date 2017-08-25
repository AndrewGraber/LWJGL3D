package com.anzanama.lwjgl3d.World.Change;

import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.World;

public class CreateChunkWC extends WorldChange {
    private ChunkPos pos;

    public CreateChunkWC(ChunkPos pos){
        this.pos = pos;
    }

    @Override
    public void makeChange(World world) {

    }
}
