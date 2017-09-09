package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;

/**
 * Base class for any {@link GameObject} that takes an {@link Input}. This can be used for players,
 * cameras, etc.. Also, this class and the structure of {@link Input} is designed so that it can be
 * used for AI as well. You'll just need to create an AI class that determines it's {@link Input}
 * accordingly and you'll be good to go!
 *
 * @author Andrew Graber
 * @version 9/9/2017
 */
public class ControlledObject extends ModeledObject implements IControlled {
    /**
     * Can be any child of the {@link Input} class. This is what you would use to control an AI if
     * you were so inclined to make one.
     */
    private Input input;

    public ControlledObject(TexturedModel model, Input input, Pos3D pos, World world) {
        super(model, pos, world);
        this.input = input;
    }

    /**
     * {@link Input#updateInput()} is called first, so that the {@link Input} gets properly updated
     * each game cycle. After that, it determines how to use that input to move in {@link #updateMovement()}.
     *
     * @param delta the time (in seconds) between frames.
     */
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
        pos.getLoc().addZ((float)(-amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(-amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }

    public void strafe(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }
}
