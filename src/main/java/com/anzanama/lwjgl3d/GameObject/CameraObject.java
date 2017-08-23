package main.java.com.anzanama.lwjgl3d.GameObject;

import main.java.com.anzanama.lwjgl3d.World.Position.Pos3D;
import main.java.com.anzanama.lwjgl3d.World.World;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class CameraObject extends EmptyObject {
    public static final float DEFAULT_FOV = 70;
    public static final float DEFAULT_ASPECT = 800.0f/600.0f;
    public static final float DEFAULT_NEAR_CLIP = 0.3f;
    public static final float DEFAULT_RENDER_DISTANCE = 1000;
    public static final float DEFAULT_SENSITIVITY = 0.5f;

    private float fov; //Field of View angle (degrees)
    private float aspectRatio; //The camera's aspect ratio (width to height)
    private float nearClip; //The distance from the camera's location to clip
    private float renderDistance; //The furthest point something will be rendered
    private float sensitivity;

    public CameraObject(Pos3D pos, World world, float fov, float aspectRatio, float nearClip, float renderDistance, float sensitivity) {
        super(pos, world);
        this.pos = pos;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        this.nearClip = nearClip;
        this.renderDistance = renderDistance;
        this.sensitivity = sensitivity;
        initProjection();
    }

    public CameraObject(World world, float fov, float aspectRatio, float nearClip, float renderDistance, float sensitivity) {
        this(new Pos3D(), world, fov, aspectRatio, nearClip, renderDistance, sensitivity);
    }

    public CameraObject(World world, Pos3D pos) {
        this(pos, world, DEFAULT_FOV, DEFAULT_ASPECT, DEFAULT_NEAR_CLIP, DEFAULT_RENDER_DISTANCE, DEFAULT_SENSITIVITY);
    }

    public CameraObject(World world, float sensitivity) {
        this(new Pos3D(), world, DEFAULT_FOV, DEFAULT_ASPECT, DEFAULT_NEAR_CLIP, DEFAULT_RENDER_DISTANCE, sensitivity);
    }

    public CameraObject(World world) {
        this(new Pos3D(), world, DEFAULT_FOV, DEFAULT_ASPECT, DEFAULT_NEAR_CLIP, DEFAULT_RENDER_DISTANCE, DEFAULT_SENSITIVITY);
    }

    @Override
    public void render() {
        super.render();
        updateView();
    }

    private void initProjection() {
        glMatrixMode(GL_PROJECTION); //Set active matrix to projection matrix
        glLoadIdentity(); //Clear the matrix completely
        gluPerspective(fov, aspectRatio, nearClip, renderDistance); //Sets up the perspective projection
        glMatrixMode(GL_MODELVIEW); //Switch back to Modelview Matrix
        glEnable(GL_DEPTH_TEST); //Enables Depth Calculations to determine what to render in front
    }

    public void updateView() {
        glRotatef(pos.getRot().getPitch(), 1, 0, 0);
        glRotatef(pos.getRot().getYaw(), 0, 1, 0);
        glRotatef(pos.getRot().getRoll(), 0, 0, 1);

        glTranslatef(pos.getX(), pos.getY(), pos.getZ());
    }

    public Pos3D getPos() {
        return pos;
    }

    public void setPos(Pos3D pos) {
        this.pos = pos;
    }

    public float getSensitivity() {
        return sensitivity;
    }
}
