package com.anzanama.lwjgl3d.Game.World.Chunk;

import com.anzanama.lwjgl3d.Game.GameObject.GameObject;
import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.Terrain.Terrain;

import java.util.ArrayList;

public class Chunk {
    private ChunkPos chunkPos;
    private ArrayList<GameObject> objects;
    private ArrayList<Terrain> terrains;

    public Chunk(ChunkPos chunkPos, ArrayList<GameObject> objects) {
        this.chunkPos = chunkPos;
        this.terrains = new ArrayList<>();
        this.objects = objects;
    }

    public Chunk(ChunkPos chunkPos) {
        this(chunkPos, new ArrayList<>());
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }

    public ArrayList<Terrain> getTerrains() {
        return terrains;
    }

    public boolean hasTerrain() {
        return this.terrains.size() > 0;
    }

    public int getTerrainCount() {
        return this.terrains.size();
    }

    public void addTerrain(Terrain terrain) {
        this.terrains.add(terrain);
        terrain.setPos(this.chunkPos);
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
