package com.anzanama.lwjgl3d.World.Position;

/**
 * Pos3D - Combines Loc3D and Rot3D to provide a rich structure for determining the whereabouts of an
 * object in 3-space. Should probably be used for most visible objects in the world.
 * @author Andrew Graber (AnZaNaMa)
 * @version 8/16/2017
 */
public class Pos3D {
    //Instance Variables
    private Loc3D loc;
    private Rot3D rot;

    /** Begin Constructors */
    public Pos3D(Loc3D pos, Rot3D rot) {
        this.loc = pos;
        this.rot = rot;
    }

    public Pos3D(float x, float y, float z, float pitch, float yaw, float roll) {
        this(new Loc3D(x, y, z), new Rot3D(pitch, yaw, roll));
    }

    public Pos3D(float x, float y, float z) {
        this(new Loc3D(x, y, z), new Rot3D());
    }

    //Copy Constructor
    public Pos3D(Pos3D pos1) {
        this(new Loc3D(pos1.getLoc()), new Rot3D(pos1.getRot()));
    }

    //Default Constructor
    public Pos3D() {
        this(new Loc3D(), new Rot3D());
    }
    /** End Constructors */

    /** Begin Getters/Setters */
    public Loc3D getLoc() {
        return loc;
    }

    public Rot3D getRot() {
        return rot;
    }

    public void setLoc(Loc3D loc1) {
        this.loc = loc1;
    }

    public void setRot(Rot3D rot1) {
        this.rot = rot1;
    }

    /* The following 6 methods (getters and setters for location) are provided
     * as a convenience in order to make location handling take up less space.
     * Although it would be more logical to just force users to use pos.getLoc().getX(),
     * for example, it would also take up a lot more space than just using pos.getX().
     * Because these methods are expected to be used so commonly, they are provided at
     * convenience. Rotation is not included, because it will probably be used far less
     * than position.
     */
    public float getX() {
        return loc.getX();
    }

    public float getY() {
        return loc.getY();
    }

    public float getZ() {
        return loc.getZ();
    }

    public void setX(float x) {
        this.loc.setX(x);
    }

    public void setY(float y) {
        this.loc.setY(y);
    }

    public void setZ(float z) {
        this.loc.setZ(z);
    }
    /** End Getters/Setters */
}
