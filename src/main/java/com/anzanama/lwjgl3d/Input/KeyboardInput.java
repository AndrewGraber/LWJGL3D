package com.anzanama.lwjgl3d.Input;

import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.HashMap;

public class KeyboardInput extends Input {

    @Override
    public void updateInput() {
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
        axes.put("look_x", (float)temp);
        temp = Mouse.getDY();
        axes.put("look_y", (float)temp);
    }

    @Override
    public void updateLook(Pos3D pos, boolean allowPitch) {
        pos.getRot().addYaw(getAxis("look_x") * Config.getFloat("mouse_sensitivity"));
        if(allowPitch) {
            pos.getRot().addPitch(-getAxis("look_y") * Config.getFloat("mouse_sensitivity"));
        }
    }
}
