package com.anzanama.lwjgl3d.Input;

import com.anzanama.lwjgl3d.World.Position.Pos3D;

import java.util.HashMap;

public abstract class Input {
    protected HashMap<String, Float> axes;

    public Input() {
        axes = new HashMap<>();
        axes.put("move", 0.0f);
        axes.put("strafe", 0.0f);
        axes.put("jump", 0.0f);
        axes.put("sneak", 0.0f);
        axes.put("look_x", 0.0f);
        axes.put("look_y", 0.0f);
        axes.put("zoom", 0.0f);
        axes.put("button_1", 0.0f);
        axes.put("pitch", 0.0f);
    }

    public abstract void updateInput();

    public float getAxis(String key) {
        if(axes.containsKey(key)) {
            return axes.get(key);
        } else {
            return 0;
        }
    }

    public abstract void updateLook(Pos3D pos, boolean allowPitch);
}
