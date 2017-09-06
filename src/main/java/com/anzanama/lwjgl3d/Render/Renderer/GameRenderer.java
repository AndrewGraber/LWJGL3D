package com.anzanama.lwjgl3d.Render.Renderer;

import com.anzanama.lwjgl3d.Game.GameObject.CameraObject;
import com.anzanama.lwjgl3d.Game.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Render.Lighting.Light;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Render.Shader.StaticShader;
import com.anzanama.lwjgl3d.Render.Shader.TerrainShader;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Util.Math;
import com.anzanama.lwjgl3d.Game.World.Terrain.Terrain;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;

public class GameRenderer {
    private Matrix4f projectionMatrix;
    private StaticShader shader = new StaticShader();
    private ModelRenderer renderer;
    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer;
    private Map<TexturedModel, List<ModeledObject>> objects;
    private List<Terrain> terrains;

    public GameRenderer() {
        enableCulling();
        createProjectionMatrix();
        renderer = new ModelRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
        objects = new HashMap<>();
        terrains = new ArrayList<>();
    }

    public static void enableCulling() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public static void disableCulling() {
        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    public void render(Light sun, CameraObject camera) {
        prepare();
        shader.start();
        shader.loadSkyColor(Config.getFloat("sky_red"), Config.getFloat("sky_green"), Config.getFloat("sky_blue"));
        shader.loadLight(sun);
        shader.loadConfigValues();
        shader.loadViewMatrix(Math.createViewMatrix(camera.getPos()));
        renderer.render(objects);
        shader.stop();

        terrainShader.start();
        terrainShader.loadSkyColor(Config.getFloat("sky_red"), Config.getFloat("sky_green"), Config.getFloat("sky_blue"));
        terrainShader.loadLight(sun);
        terrainShader.loadConfigValues();
        terrainShader.loadViewMatrix(Math.createViewMatrix(camera.getPos()));
        terrainRenderer.render(terrains);
        terrainShader.stop();
        terrains.clear();
        objects.clear();
    }

    public void processTerrainChunk(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processModeledObject(ModeledObject object) {
        TexturedModel objectModel = object.getModel();
        List<ModeledObject> batch = objects.get(objectModel);
        if(batch != null) {
            batch.add(object);
        } else {
            List<ModeledObject> newBatch = new ArrayList<>();
            newBatch.add(object);
            objects.put(objectModel, newBatch);
        }
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(Config.getFloat("sky_red"), Config.getFloat("sky_green"), Config.getFloat("sky_blue"), 1);
    }

    public void cleanUp() {
        shader.cleanUp();
        terrainShader.cleanUp();
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth()/(float)Display.getHeight();
        float y_scale = (float) (1f / java.lang.Math.tan(java.lang.Math.toRadians(Config.getFloat("camera_fov") / 2f))) * aspectRatio;
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Config.getFloat("render_distance") - Config.getFloat("near_clip");

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((Config.getFloat("render_distance") + Config.getFloat("near_clip"))/frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * Config.getFloat("near_clip") * Config.getFloat("render_distance"))/frustum_length);
        projectionMatrix.m33 = 0;
    }
}
