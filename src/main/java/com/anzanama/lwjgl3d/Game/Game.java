package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.GameObject.CameraObject;
import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Input;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import com.anzanama.lwjgl3d.Render.GameRenderer;
import com.anzanama.lwjgl3d.Render.Light;
import com.anzanama.lwjgl3d.Render.Model.*;
import com.anzanama.lwjgl3d.Render.Shader.StaticShader;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Util.Math;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.TerrainChunk;
import com.anzanama.lwjgl3d.World.World;
import com.anzanama.lwjgl3d.World.WorldProvider;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Game {

    public boolean stopGameFlag;
    private GameRenderer renderer;
    private ModelLoader modelLoader;
    private World world;
    private Input input;
    private Light light;

    private CameraObject camera;
    private ModeledObject object;
    private TerrainChunk terrainChunk, terrainChunk2;
    private ArrayList<ModeledObject> trees = new ArrayList<>();
    private ArrayList<ModeledObject> ferns;
    private ArrayList<ModeledObject> grass;

    public void initialize() {

        renderer = new GameRenderer();
        world = WorldProvider.createNewWorld("world");
        input = new Input();
        modelLoader = new ModelLoader();
        camera = new CameraObject(input);
        light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        RawModel model = OBJLoader.loadObj("dragon", modelLoader);
        ModelTexture texture = new ModelTexture(modelLoader.loadTexture("solid"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        object = new ModeledObject(texturedModel, new Pos3D(0, 0, -50, 0, 180, 0), world);
        terrainChunk = new TerrainChunk(new ChunkPos(0, 0, 0), modelLoader, new ModelTexture(modelLoader.loadTexture("grass")));
        terrainChunk2 = new TerrainChunk(new ChunkPos(1, 0, 0), modelLoader, new ModelTexture(modelLoader.loadTexture("grass")));

        Random random = new Random();

        TexturedModel treeModel = new TexturedModel(OBJLoader.loadObj("tree", modelLoader), new ModelTexture(modelLoader.loadTexture("tree")));
        for(int i=0; i<100; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*256, 0, random.nextFloat()*360, 0, 4);
            trees.add(new ModeledObject(treeModel, pos, world));
        }

        TexturedModel fernModel = new TexturedModel(OBJLoader.loadObj("fern", modelLoader), new ModelTexture(modelLoader.loadTexture("fern"), true));
        for(int i=0; i<100; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*256, 0, random.nextFloat()*360, 0, 0.5f);
            trees.add(new ModeledObject(fernModel, pos, world));
        }

        TexturedModel grassModel = new TexturedModel(OBJLoader.loadObj("grassModel", modelLoader), new ModelTexture(modelLoader.loadTexture("grassTexture"), true, true));
        for(int i=0; i<100; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*256, 0, random.nextFloat()*360, 0);
            trees.add(new ModeledObject(grassModel, pos, world));
        }
    }

    public void loop() {
        while(!Display.isCloseRequested() && !stopGameFlag) {
            update();
            render();
        }
    }

    public void update() {
        camera.update();

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

        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.render();
            }
        }
        renderer.processTerrainChunk(terrainChunk);
        renderer.processTerrainChunk(terrainChunk2);
        renderer.render(light, camera);
        DisplayManager.updateDisplay();
    }

    public void shutdown() {
        modelLoader.cleanUp();
        renderer.cleanUp();
    }

    public GameRenderer getRenderer() {
        return renderer;
    }
}
