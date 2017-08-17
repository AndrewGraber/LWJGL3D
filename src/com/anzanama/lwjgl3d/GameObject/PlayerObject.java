package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Input;
import com.anzanama.lwjgl3d.World.Change.SwitchChunksWC;
import com.anzanama.lwjgl3d.World.Chunk;
import com.anzanama.lwjgl3d.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;
import org.lwjgl.input.Mouse;

public class PlayerObject extends EmptyObject {
    private static final float SPEED = 0.1f;
    private Input input;
    private World world;
    private CameraObject camera;

    public PlayerObject(Pos3D pos, World world, Input input) {
        super(pos, world);
        this.input = input;
        this.world = world;
        camera = new CameraObject(world);
        children.add(camera);
    }

    @Override
    public void update() {
        super.update();
        input.updateInput();
        updateMovement();
        for(GameObject obj : children) {
            obj.setPos(pos);
        }
    }

    public CameraObject getCamera() {
        return camera;
    }

    public void updateMovement() {
        Chunk prevChunk = world.getChunkFromPos3D(pos);

        if(input.getInput("forward") && !input.getInput("back")) {
            moveForward(SPEED);
        }
        if(input.getInput("back") && !input.getInput("forward")) {
            moveBack(SPEED);
        }
        if(input.getInput("left") && !input.getInput("right")) {
            strafeLeft(SPEED);
        }
        if(input.getInput("right") && !input.getInput("left")) {
            strafeRight(SPEED);
        }
        if(input.getInput("jump") && !input.getInput("sneak")) {
            getPos().getLoc().addY(SPEED);
        }
        if(input.getInput("sneak") && !input.getInput("jump")) {
            getPos().getLoc().addY(-SPEED);
        }
        if(input.getInput("mouse_x")) {
            camera.getPos().getRot().addYaw(((float) Mouse.getDX())*camera.getSensitivity());
        }
        if(input.getInput("mouse_y")) {
            camera.getPos().getRot().addPitch(-((float)Mouse.getDY())*camera.getSensitivity());
        }

        if(!world.getChunkFromPos3D(pos).equals(prevChunk)) {
            world.getChangeScheduler().addChange(new SwitchChunksWC(prevChunk.getPos(), ChunkPos.fromPos3D(pos), this));
        }
    }

    public void moveForward(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw() + 90))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw() + 90))));
    }

    public void moveBack(float amt) {
        moveForward(-amt);
    }

    public void strafeLeft(float amt) {
        pos.getLoc().addZ((float)(amt * Math.sin(Math.toRadians(pos.getRot().getYaw()))));
        pos.getLoc().addX((float)(amt * Math.cos(Math.toRadians(pos.getRot().getYaw()))));
    }

    public void strafeRight(float amt) {
        strafeLeft(-amt);
    }
}
