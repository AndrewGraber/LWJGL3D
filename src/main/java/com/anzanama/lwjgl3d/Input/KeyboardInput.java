package com.anzanama.lwjgl3d.Input;

import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyboardInput extends Input {

    @Override
    public void updateInput() {
        axes.put("button_1", Mouse.isButtonDown(1) ? 1.0f : 0.0f);
        axes.put("button_0", Mouse.isButtonDown(0) ? 1.0f : 0.0f);

        if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
            axes.put("move", 1.0f);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            axes.put("move", -1.0f);
        } else axes.put("move", 0f);

        if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
            axes.put("strafe", 1.0f);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
            axes.put("strafe", -1.0f);
        } else axes.put("strafe", 0f);

        axes.put("jump", Keyboard.isKeyDown(Keyboard.KEY_SPACE) ? 1.0f : 0.0f);
        axes.put("sneak", Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) ? 1.0f : 0.0f);

        int temp = Mouse.getDX();
        if(getAxis("button_0") > 0) {
            axes.put("rotate", (float)temp);
            axes.put("look_x", 0.0f);
        } else {
            axes.put("look_x", (float)temp);
            axes.put("rotate", 0.0f);
        }
        temp = Mouse.getDY();
        if(getAxis("button_1") > 0) {
            axes.put("pitch", (float)temp);
            axes.put("look_y", 0.0f);
        } else {
            axes.put("look_y", (float) temp);
            axes.put("pitch", 0.0f);
        }

        axes.put("zoom", (float)Mouse.getDWheel());
    }

    @Override
    public void updateLook(Pos3D pos, boolean allowPitch) {
        pos.getRot().addYaw(getAxis("look_x") * Config.getFloat("mouse_sensitivity"));
        if(allowPitch) {
            pos.getRot().addPitch(-getAxis("look_y") * Config.getFloat("mouse_sensitivity"));
        }
    }
}
