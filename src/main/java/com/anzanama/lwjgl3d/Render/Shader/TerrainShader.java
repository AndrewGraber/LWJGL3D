package com.anzanama.lwjgl3d.Render.Shader;

import com.anzanama.lwjgl3d.Render.Lighting.Light;
import com.anzanama.lwjgl3d.Util.Config;
import org.lwjgl.util.vector.Matrix4f;

public class TerrainShader extends Shader {
    private static final String VERTEX_FILE = "src/main/resources/shaders/terrainShader.vert";
    private static final String FRAGMENT_FILE = "src/main/resources/shaders/terrainShader.frag";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    private int location_lightPosition;
    private int location_lightColor;
    private int location_shineDamper;
    private int location_reflectivity;
    private int location_ambientLight;
    private int location_fogDensity;
    private int location_fogGradient;
    private int location_backgroundTexture;
    private int location_rTexture;
    private int location_gTexture;
    private int location_bTexture;
    private int location_blendMap;

    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColor = super.getUniformLocation("lightColor");
        location_shineDamper = super.getUniformLocation("shineDamper");
        location_reflectivity = super.getUniformLocation("reflectivity");
        location_ambientLight = super.getUniformLocation("ambientLight");
        location_fogGradient = super.getUniformLocation("fogGradient");
        location_fogDensity = super.getUniformLocation("fogDensity");
        location_backgroundTexture = super.getUniformLocation("backgroundTexture");
        location_rTexture = super.getUniformLocation("rTexture");
        location_gTexture = super.getUniformLocation("gTexture");
        location_bTexture = super.getUniformLocation("bTexture");
        location_blendMap = super.getUniformLocation("blendMap");
    }

    public void connectTextureUnits() {
        super.loadInt(location_backgroundTexture, 0);
        super.loadInt(location_rTexture, 1);
        super.loadInt(location_gTexture, 2);
        super.loadInt(location_bTexture, 3);
        super.loadInt(location_blendMap, 4);
    }

    public void loadShineVariables(float damper, float reflectivity) {
        super.loadFloat(location_shineDamper, damper);
        super.loadFloat(location_reflectivity, reflectivity);
    }

    public void loadSkyColor(float r, float g, float  b) {

    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadLight(Light light) {
        super.loadVector(location_lightPosition, light.getPosition());
        super.loadVector(location_lightColor, light.getColor());
    }

    public void loadConfigValues() {
        super.loadFloat(location_fogDensity, Config.getFloat("fog_density"));
        super.loadFloat(location_fogGradient, Config.getFloat("fog_gradient"));
        super.loadFloat(location_ambientLight, Config.getFloat("ambient_light"));
    }

    public void loadViewMatrix(Matrix4f matrix) {
        super.loadMatrix(location_viewMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }
}
