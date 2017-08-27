package com.anzanama.lwjgl3d.World.Position;

/**
 * Loc3D - Holds modeled information about an object's location in three dimensional space, according
 * to floating point x, y, and z coordinates.
 * @author Andrew Graber (AnZaNaMa)
 * @version 8/16/2017
 */
public class Loc3D {
    private float x, y, z;

    public Loc3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Copy Constructor
    public Loc3D(Loc3D loc1) {
        this(loc1.getX(), loc1.getY(), loc1.getZ());
    }

    public Loc3D() {
        this(0.0f, 0.0f, 0.0f);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void addX(float amt) {
        this.x += amt;
    }

    public void addY(float amt) {
        this.y += amt;
    }

    public void addZ(float amt) {
        this.z += amt;
    }
}
