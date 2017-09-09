package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Input.Input;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;
import org.lwjgl.input.Mouse;

public class FollowCameraObject extends CameraObject {
    private GameObject target;
    private Input input;
    private float distance;
    private float angleAroundPlayer;

    public FollowCameraObject(Pos3D pos, World world, Input input, GameObject target, float distance, float angleAroundPlayer) {
        super(pos, world);
        this.input = input;
        this.target = target;
        this.distance = distance;
        this.angleAroundPlayer = angleAroundPlayer;
    }

    @Override
    public void update(float delta) {
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
    }

    private void calculateZoom() {
        float zoomLevel = input.getAxis("zoom") * Config.getFloat("zoom_distance");
        distance -= zoomLevel;
    }

    private void calculatePitch() {
        float pitchChange = input.getAxis("pitch") * Config.getFloat("pitch_change");
        pos.getRot().addPitch(-pitchChange);
    }

    private void calculateAngleAroundPlayer() {
        float angleChange = input.getAxis("rotate") * Config.getFloat("rotate_change");
        angleAroundPlayer -= angleChange;
    }

    private float calculateHorizontalDistance() {
        return (float) (distance * Math.cos(Math.toRadians(pos.getRot().getPitch())));
    }

    private float calculateVerticalDistance() {
        return (float) (distance * Math.sin(Math.toRadians(pos.getRot().getPitch())));
    }

    private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
        float theta = target.getPos().getRot().getYaw() + angleAroundPlayer;
        float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));

        pos.getRot().setYaw(180f-theta);

        pos.setY(target.getPos().getY() + verticalDistance);
        pos.setX(target.getPos().getX() - offsetX);
        pos.setZ(target.getPos().getZ() - offsetZ);
    }
}
