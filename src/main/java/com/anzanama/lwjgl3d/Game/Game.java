package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.GameObject.CameraObject;
import com.anzanama.lwjgl3d.GameObject.ControlledObject;
import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Input.ControllerDS4Input;
import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Input.KeyboardInput;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import com.anzanama.lwjgl3d.Render.GameRenderer;
import com.anzanama.lwjgl3d.Render.Light;
import com.anzanama.lwjgl3d.Render.Model.*;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Render.Texture.TerrainTexture;
import com.anzanama.lwjgl3d.Render.Texture.TerrainTexturePack;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.TerrainChunk;
import com.anzanama.lwjgl3d.World.World;
import com.anzanama.lwjgl3d.World.WorldProvider;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.util.*;

public class Game {

    public boolean stopGameFlag;
    private GameRenderer renderer;
    private ModelLoader modelLoader;
    private World world;
    private Input playerInput, cameraInput;
    private Light light;

    private CameraObject camera;
    private TerrainChunk terrainChunk, terrainChunk2, terrainChunk3, terrainChunk4;

    public void initialize() {
        Controller controller = Controllers.getController(0);

        renderer = new GameRenderer();
        world = WorldProvider.createNewWorld("world");
        playerInput = new ControllerDS4Input(controller);
        cameraInput = new KeyboardInput();
        modelLoader = new ModelLoader();
        camera = new CameraObject(new Pos3D(256, 1, 256), cameraInput);
        light = new Light(new Vector3f(256, 200, 256), new Vector3f(1, 1, 1));

        //*********************************** TERRAIN TEXTURE PACK STUFF ****************************************
        TerrainTexture backgroundTexture = new TerrainTexture(modelLoader.loadTexture("grass"));
        TerrainTexture rTexture = new TerrainTexture(modelLoader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(modelLoader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(modelLoader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(modelLoader.loadTexture("blendMap"));
        //*******************************************************************************************************

        RawModel model = OBJFileLoader.loadObjModel("dragon", modelLoader);
        ModelTexture texture = new ModelTexture(modelLoader.loadTexture("solid"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        new ModeledObject(texturedModel, new Pos3D(256, 200, 256, 0, 180, 0), world);
        terrainChunk = new TerrainChunk(new ChunkPos(1, 0, 1), modelLoader, texturePack, blendMap);
        terrainChunk2 = new TerrainChunk(new ChunkPos(2, 0, 1), modelLoader, texturePack, blendMap);
        terrainChunk3 = new TerrainChunk(new ChunkPos(2, 0, 2), modelLoader, texturePack, blendMap);
        terrainChunk4 = new TerrainChunk(new ChunkPos(1, 0, 2), modelLoader, texturePack, blendMap);

        Random random = new Random();

        TexturedModel treeModel = new TexturedModel(OBJFileLoader.loadObjModel("tree", modelLoader), new ModelTexture(modelLoader.loadTexture("tree")));
        for(int i=0; i<200; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0, 4);
            new ModeledObject(treeModel, pos, world);
        }

        TexturedModel fernModel = new TexturedModel(OBJFileLoader.loadObjModel("fern", modelLoader), new ModelTexture(modelLoader.loadTexture("fern"), true));
        for(int i=0; i<200; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0, 0.5f);
            new ModeledObject(fernModel, pos, world);
        }

        TexturedModel grassModel = new TexturedModel(OBJFileLoader.loadObjModel("grassModel", modelLoader), new ModelTexture(modelLoader.loadTexture("grassTexture"), true, true));
        for(int i=0; i<200; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0);
            new ModeledObject(grassModel, pos, world);
        }

        TexturedModel flowerModel = new TexturedModel(OBJFileLoader.loadObjModel("grassModel", modelLoader), new ModelTexture(modelLoader.loadTexture("flower"), true, true));
        for(int i=0; i<200; i++) {
            Pos3D pos = new Pos3D(random.nextFloat()*512, 0, random.nextFloat()*512, 0, random.nextFloat()*360, 0);
            new ModeledObject(flowerModel, pos, world);
        }

        new ControlledObject(texturedModel, playerInput, new Pos3D(256, 0, 256, 0, 0, 0, 0.5f), world);
    }

    public void loop() {
        while(!Display.isCloseRequested() && !stopGameFlag) {
            update(DisplayManager.getFrameTimeSeconds());
            render(DisplayManager.getFrameTimeSeconds());
        }
    }

    public void update(float delta) {
        camera.update(delta);

        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.update(delta);
            }
        }

        world.getChangeScheduler().makeChanges(world);
    }

    public void render(float delta) {

        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.render(delta);
            }
        }
        renderer.processTerrainChunk(terrainChunk);
        renderer.processTerrainChunk(terrainChunk2);
        renderer.processTerrainChunk(terrainChunk3);
        renderer.processTerrainChunk(terrainChunk4);
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
