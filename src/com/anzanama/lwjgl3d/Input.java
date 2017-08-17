package com.anzanama.lwjgl3d;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.HashMap;

public class Input {
    private HashMap<String, Boolean> input;

    public Input() {
        input = new HashMap<>();
        input.put("forward", false);
        input.put("back", false);
        input.put("left", false);
        input.put("right", false);
        input.put("jump", false);
        input.put("sneak", false);
        input.put("mouse_x", false);
        input.put("mouse_y", false);
    }

    public void updateInput() {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            input.put("forward", true);
        else input.put("forward", false);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            input.put("back", true);
        else input.put("back", false);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            input.put("left", true);
        else input.put("left", false);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            input.put("right", true);
        else input.put("right", false);
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            input.put("jump", true);
        else input.put("jump", false);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            input.put("sneak", true);
        else input.put("sneak", false);

        if(Mouse.getDX() != 0) {
            input.put("mouse_x", true);
        } else input.put("mouse_x", false);

        if(Mouse.getDY() != 0) {
            input.put("mouse_y", true);
        } else input.put("mouse_y", false);
    }

    public boolean getInput(String key) {
        if(input.containsKey(key)) {
            return input.get(key);
        } else {
            return false;
        }
    }
}
