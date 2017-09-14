package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Input.Input;

public interface IControlled {
    void updateMovement();
    void move(float amount);
    void strafe(float amount);
}
