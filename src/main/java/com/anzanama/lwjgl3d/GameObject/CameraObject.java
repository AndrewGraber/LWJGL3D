package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Input.KeyboardInput;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;

public class CameraObject extends GameObject {
    private Pos3D pos;
    private Input input;

    public CameraObject(Pos3D pos, Input input) {
        this.pos = pos;
        this.input = input;
    }

    public CameraObject(Input input) {
        this(new Pos3D(), input);
    }

    @Override
    public void update(float delta) {
        input.updateInput();
        updateMovement();
    }

    @Override
    public void render(float delta) {}

    public Pos3D getPos() {
        return pos;
    }

    public void setPos(Pos3D pos) {
        this.pos = pos;
    }

    public void updateMovement() {
        move(input.getAxis("move") * Config.getFloat("camera_move_speed"));
        strafe(input.getAxis("strafe") * Config.getFloat("camera_move_speed"));
        if(input.getAxis("jump") != 0) {
            getPos().getLoc().addY(input.getAxis("jump") * Config.getFloat("camera_move_speed"));
        }
        if(input.getAxis("sneak") != 0) {
            getPos().getLoc().addY(-input.getAxis("sneak") * Config.getFloat("camera_move_speed"));
        }
        input.updateLook(pos, true);
    }

    public void move(float amt) {
        pos.getLoc().addZ((float)(-amt * Math.sin(Math.toRadians(pos.getRot().getYaw() + 90))));
        pos.getLoc().addX((float)(-amt * Math.cos(Math.toRadians(pos.getRot().getYaw() + 90))));
    }

    public void strafe(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }
}
