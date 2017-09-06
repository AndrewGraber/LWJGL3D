package com.anzanama.lwjgl3d.Render.Texture;

public class ModelTexture {

    private int textureID;

    private float shineDamper = 1;
    private float reflectivity = 0;

    private boolean hasTransparency;
    private boolean useFakeLighting;

    public ModelTexture(int id, boolean hasTransparency, boolean useFakeLighting) {
        this.textureID = id;
        this.hasTransparency = hasTransparency;
        this.useFakeLighting = useFakeLighting;
    }

    public ModelTexture(int id, boolean hasTransparency) {
        this(id, hasTransparency, false);
    }

    public ModelTexture(int id) {
        this(id, false, false);
    }

    public int getID() {
        return this.textureID;
    }

    public float getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }

    public float getShineDamper() {
        return shineDamper;
    }

    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    public boolean hasTransparency() {
        return hasTransparency;
    }

    public void setHasTransparency(boolean hasTransparency) {
        this.hasTransparency = hasTransparency;
    }

    public boolean useFakeLighting() {
        return useFakeLighting;
    }

    public void setUseFakeLighting(boolean useFakeLighting) {
        this.useFakeLighting = useFakeLighting;
    }
}
