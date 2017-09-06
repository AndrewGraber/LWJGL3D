package com.anzanama.lwjgl3d.Input;

import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import org.lwjgl.input.Controller;

import java.util.HashMap;

public class ControllerDS4Input extends Input {
    Controller controller;

    public ControllerDS4Input(Controller controller) {
        this.controller = controller;
        controller.setDeadZone(0, 0.1f);
        controller.setDeadZone(1, 0.1f);
        controller.setDeadZone(2, 0.1f);
        controller.setDeadZone(3, 0.1f);
    }

    @Override
    public void updateInput() {
        controller.poll();

        axes.put("move", -controller.getAxisValue(2));
        axes.put("strafe", controller.getAxisValue(3));
        axes.put("jump", controller.getAxisValue(5));
        axes.put("sneak", controller.getAxisValue(4));
        axes.put("look_x", controller.getAxisValue(1));
        axes.put("look_y", -controller.getAxisValue(0));
    }

    @Override
    public void updateLook(Pos3D pos, boolean allowPitch) {
        pos.getRot().addYaw(getAxis("look_x") * Config.getFloat("controller_sensitivity"));
        if(allowPitch) {
            pos.getRot().addPitch(-getAxis("look_y") * Config.getFloat("controller_sensitivity"));
        }
    }
}
