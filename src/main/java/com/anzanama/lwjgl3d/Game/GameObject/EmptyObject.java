package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

import java.util.ArrayList;

public class EmptyObject extends GameObject {
    protected Pos3D pos;
    protected ArrayList<GameObject> children;

    public EmptyObject(Pos3D pos, World world, ArrayList<GameObject> children) {
        this.pos = pos;
        this.children = children;
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }

    public EmptyObject(Pos3D pos, World world, GameObject... children) {
        this.pos = pos;
        this.children = new ArrayList<>();
        for(GameObject obj : children) {
            this.children.add(obj);
        }
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }

    public EmptyObject(World world, GameObject... children) {
        this.pos = new Pos3D();
        this.children = new ArrayList<>();
        for(GameObject obj : children) {
            this.children.add(obj);
        }
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }

    public EmptyObject(Pos3D pos, World world) {
        this(pos, world, new ArrayList<GameObject>());
    }

    public EmptyObject(World world) {
        this(new Pos3D(), world, new ArrayList<GameObject>());
    }

    @Override
    public Pos3D getPos() {
        return pos;
    }

    @Override
    public void setPos(Pos3D pos) {
        this.pos = pos;
    }

    @Override
    public void update(float delta) {
        for(GameObject obj : children) {
            obj.update(delta);
        }
    }

    @Override
    public void render(float delta) {
        for(GameObject obj : children) {
            obj.render(delta);
        }
    }
}
