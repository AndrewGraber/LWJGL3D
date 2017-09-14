package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.GameObject.CameraObject;
import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

public class FreeCameraObject extends CameraObject implements IControlled {
    protected Input input;

    public FreeCameraObject(Pos3D pos, World world, Input input) {
        super(pos, world);
        this.input = input;
    }

    @Override
    public void update(float delta) {
        input.updateInput();
        updateMovement();
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
