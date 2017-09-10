package com.anzanama.lwjgl3d.Game;

import com.anzanama.lwjgl3d.Game.GameObject.ControlledObject;
import com.anzanama.lwjgl3d.Game.World.Terrain.Terrain;
import com.anzanama.lwjgl3d.GameObject.FollowCameraObject;
import com.anzanama.lwjgl3d.Game.GameObject.GameObject;
import com.anzanama.lwjgl3d.Game.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.GameObject.FreeCameraObject;
import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Input.KeyboardInput;
import com.anzanama.lwjgl3d.Render.Lighting.Light;
import com.anzanama.lwjgl3d.Render.Model.OBJFileLoader;
import com.anzanama.lwjgl3d.Render.Model.RawModel;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Render.Texture.TerrainTexture;
import com.anzanama.lwjgl3d.Render.Texture.TerrainTexturePack;
import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import org.lwjgl.util.vector.Vector3f;

import java.util.*;

/**
 * Class Description Here
 *
 * @author Andrew Graber
 * @version 9/8/2017
 */
public class ExampleGame extends Game {

    @Override
    public void initialize() {
        super.initialize();

        Input input = new KeyboardInput();
        light = new Light(new Vector3f(256, 200, 256), new Vector3f(1, 1, 1));

        RawModel model = OBJFileLoader.loadObjModel("dragon", modelLoader);
        ModelTexture texture = new ModelTexture(modelLoader.loadTexture("solid"));
        texture.setShineDamper(10);
        texture.setReflectivity(1);
        TexturedModel texturedModel = new TexturedModel(model, texture);
        new ModeledObject(texturedModel, new Pos3D(256, 200, 256, 0, 180, 0), world);

        GameObject player = new ModeledObject(texturedModel, new Pos3D(256, 0, 256, 0, 0, 0, 0.5f), world);
        camera = new FreeCameraObject(new Pos3D(256, 1, 256), world, input);

        //*********************************** TERRAIN TEXTURE PACK STUFF ****************************************
        TerrainTexture backgroundTexture = new TerrainTexture(modelLoader.loadTexture("grass"));
        TerrainTexture rTexture = new TerrainTexture(modelLoader.loadTexture("mud"));
        TerrainTexture gTexture = new TerrainTexture(modelLoader.loadTexture("grassFlowers"));
        TerrainTexture bTexture = new TerrainTexture(modelLoader.loadTexture("path"));

        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(modelLoader.loadTexture("blendMap"));
        //*******************************************************************************************************

        Terrain terrain = new Terrain(modelLoader, texturePack, blendMap);
        world.getChunk(new ChunkPos(0, 0, 0)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap);
        world.getChunk(new ChunkPos(1, 0, 0)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap);
        world.getChunk(new ChunkPos(1, 0, 1)).addTerrain(terrain);
        terrain = new Terrain(modelLoader, texturePack, blendMap);
        world.getChunk(new ChunkPos(0, 0, 1)).addTerrain(terrain);

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
    }
}
