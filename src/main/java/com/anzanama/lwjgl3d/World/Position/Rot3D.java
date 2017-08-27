package com.anzanama.lwjgl3d.World.Position;

/**
 * Rot3D - Holds modeled information about an object's rotation about the x-axis, y-axis, and z-axis
 * @author Andrew Graber (AnZaNaMa)
 * @version 8/16/2017
 */
public class Rot3D {
    private float pitch, yaw, roll;

    public Rot3D(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    //Copy Constructor
    public Rot3D(Rot3D rot1) {
        this(rot1.getPitch(), rot1.getYaw(), rot1.getRoll());
    }

    //Default Constructor
    public Rot3D() {
        this(0.0f, 0.0f, 0.0f);
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }

    public void addPitch(float amt) {
        this.pitch += amt;
    }

    public void addYaw(float amt) {
        this.yaw += amt;
    }

    public void addRoll(float amt) {
        this.roll += amt;
    }
}
