package com.anzanama.lwjgl3d.Game.World.Change;

import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.World;

public class CreateChunkWC extends WorldChange {
    private ChunkPos pos;

    public CreateChunkWC(ChunkPos pos){
        this.pos = pos;
    }

    @Override
    public void makeChange(World world) {

    }
}
