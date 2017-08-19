package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.GameObject.TestCubeObject;
import com.anzanama.lwjgl3d.Input;
import com.anzanama.lwjgl3d.GameObject.PlayerObject;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;
import com.anzanama.lwjgl3d.World.WorldProvider;
import org.lwjgl.opengl.Display;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Game {
    public boolean stopGameFlag;
    private PlayerObject player;
    private World world;
    private Input input;
    private TestCubeObject cube1, cube2, cube3;

    public void initialize() {
        world = WorldProvider.createNewWorld("world");
        input = new Input();
        player = new PlayerObject(new Pos3D(), world, input);
        cube1 = new TestCubeObject(new Pos3D(0, 0, -10), world);
        cube2 = new TestCubeObject(new Pos3D(-2, 0, -8), world);
        cube3 = new TestCubeObject(new Pos3D(3, 0, -4), world);
    }

    public void loop() {
        while(!Display.isCloseRequested() && !stopGameFlag) {
            update();
            render();
        }
    }

    public void update() {
        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.update();
            }
        }

        world.getChangeScheduler().makeChanges(world);
    }

    public void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();

        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.render();
            }
        }
    }

    public void shutdown() {

    }
}
