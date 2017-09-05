package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Input;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class CameraObject extends GameObject {
    private Pos3D pos;
    private Input input;
    private float sensitivity;

    public CameraObject(Pos3D pos, Input input, float sensitivity) {
        this.pos = pos;
        this.input = input;
        this.sensitivity = sensitivity;
    }

    public CameraObject(Input input, float sensitivity) {
        this(new Pos3D(), input, sensitivity);
    }

    public CameraObject(Pos3D pos, Input input) {
        this(pos, input, Config.getFloat("mouse_sensitivity"));
    }

    public CameraObject(Input input) {
        this(new Pos3D(), input, Config.getFloat("mouse_sensitivity"));
    }

    @Override
    public void update() {
        input.updateInput();
        updateMovement();
    }

    @Override
    public void render() {}

    public Pos3D getPos() {
        return pos;
    }

    public void setPos(Pos3D pos) {
        this.pos = pos;
    }

    public float getSensitivity() {
        return sensitivity;
    }

    public void updateMovement() {
        if(input.getInput("forward") && !input.getInput("back")) {
            moveForward(Config.getFloat("move_speed"));
        }
        if(input.getInput("back") && !input.getInput("forward")) {
            moveBack(Config.getFloat("move_speed"));
        }
        if(input.getInput("left") && !input.getInput("right")) {
            strafeLeft(Config.getFloat("move_speed"));
        }
        if(input.getInput("right") && !input.getInput("left")) {
            strafeRight(Config.getFloat("move_speed"));
        }
        if(input.getInput("jump") && !input.getInput("sneak")) {
            getPos().getLoc().addY(Config.getFloat("move_speed"));
        }
        if(input.getInput("sneak") && !input.getInput("jump")) {
            getPos().getLoc().addY(-Config.getFloat("move_speed"));
        }
        if(input.getInput("mouse_x")) {
            getPos().getRot().addYaw(((float)input.pullMouseDX())*getSensitivity());
        }
        if(input.getInput("mouse_y")) {
            getPos().getRot().addPitch(-((float)input.pullMouseDY())*getSensitivity());
        }
    }

    public void moveBack(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw() + 90))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw() + 90))));
    }

    public void moveForward(float amt) {
        moveBack(-amt);
    }

    public void strafeRight(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }

    public void strafeLeft(float amt) {
        strafeRight(-amt);
    }
}
