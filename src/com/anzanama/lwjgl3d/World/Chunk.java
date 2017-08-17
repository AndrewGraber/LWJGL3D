package com.anzanama.lwjgl3d.World;

import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;

import java.util.ArrayList;

public class Chunk {
    private ChunkPos chunkPos;
    private ArrayList<GameObject> objects;

    public Chunk(ChunkPos chunkPos, ArrayList<GameObject> objects) {
        this.chunkPos = chunkPos;
        this.objects = objects;
    }

    public Chunk(ChunkPos chunkPos) {
        this.chunkPos = chunkPos;
        this.objects = new ArrayList<>();
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ChunkPos getPos() {
        return this.chunkPos;
    }

    public void addGameObject(GameObject obj) {
        this.objects.add(obj);
    }

    public void removeGameObject(GameObject obj) {
        this.objects.remove(obj);
    }
}
