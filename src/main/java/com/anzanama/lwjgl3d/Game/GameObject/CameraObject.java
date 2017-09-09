package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

/**
 * This class is very bare-bones, so it may seem unnecessary (and maybe it is), but it's needed for
 * the sole purpose of polymorphism. I can see how an interface may seem like a better alternative,
 * but I feel this is more appropriate for the situation. If I can find a better way to implement
 * this, I will. Who knows? Maybe I'll need to put some actual methods in here in the future.
 *
 * @author Andrew Graber
 * @version 9/9/2017
 */
public class CameraObject extends EmptyObject {
    public CameraObject(Pos3D pos, World world) {
        super(pos, world);
    }
}
