package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.GameObject.CameraObject;
import com.anzanama.lwjgl3d.GameObject.GameObject;
import com.anzanama.lwjgl3d.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Input;
import com.anzanama.lwjgl3d.Render.DisplayManager;
import com.anzanama.lwjgl3d.Render.Light;
import com.anzanama.lwjgl3d.Render.Model.*;
import com.anzanama.lwjgl3d.Render.Shader.StaticShader;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Util.Math;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;
import com.anzanama.lwjgl3d.World.WorldProvider;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import java.util.*;

import static org.lwjgl.opengl.GL11.*;

public class Game {
    public boolean stopGameFlag;
    private ModelLoader modelLoader;
    private ModelRenderer modelRenderer;
    private StaticShader shader;
    private World world;
    private Input input;
    private Light light;

    private CameraObject camera;
    private ModeledObject object;

    public void initialize() {
        world = WorldProvider.createNewWorld("world");
        input = new Input();
        modelLoader = new ModelLoader();
        shader = new StaticShader();
        modelRenderer = new ModelRenderer(shader);
        camera = new CameraObject(input);
        light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        RawModel model = OBJLoader.loadObj("dragon", modelLoader);
        ModelTexture texture = new ModelTexture(modelLoader.loadTexture("solid"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        object = new ModeledObject(texturedModel, new Pos3D(0, 0, -50, 0, 180, 0), world, shader);
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
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.3f, 0.3f, 0.3f, 1);

        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(Math.createViewMatrix(camera.getPos()));

        HashMap<ChunkPos, Chunk> chunkMap = world.getChunkMap();
        Iterator<Map.Entry<ChunkPos, Chunk>> it = chunkMap.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<ChunkPos, Chunk> item = it.next();
            ArrayList<GameObject> chunkObjects = item.getValue().getObjects();
            for(GameObject obj : chunkObjects) {
                obj.render();
            }
        }

        DisplayManager.updateDisplay();
    }

    public void shutdown() {
        shader.cleanUp();
        modelLoader.cleanUp();
    }

    public ModelRenderer getModelRenderer() {
        return modelRenderer;
    }
}
