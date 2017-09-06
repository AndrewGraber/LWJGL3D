package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

public class ControlledObject extends ModeledObject {
    private Input input;

    public ControlledObject(TexturedModel model, Input input, Pos3D pos, World world) {
        super(model, pos, world);
        this.input = input;
    }

    @Override
    public void update(float delta) {
        input.updateInput();
        updateMovement();
    }

    public void updateMovement() {
        move(input.getAxis("move") * Config.getFloat("player_move_speed"));
        strafe(input.getAxis("strafe") * Config.getFloat("player_move_speed"));
        if(input.getAxis("jump") != 0) {
            getPos().getLoc().addY(input.getAxis("jump") * Config.getFloat("player_move_speed"));
        }
        if(input.getAxis("sneak") != 0) {
            getPos().getLoc().addY(-input.getAxis("sneak") * Config.getFloat("player_move_speed"));
        }
        input.updateLook(pos, false);
    }

    public void move(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }

    public void strafe(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }
}
