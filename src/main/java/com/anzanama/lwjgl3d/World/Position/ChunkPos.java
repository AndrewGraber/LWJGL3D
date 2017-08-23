package main.java.com.anzanama.lwjgl3d.World.Position;

public class ChunkPos {
    private int x, y, z;

    public ChunkPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean matches(ChunkPos pos2) {
        return (x == pos2.getX() && y == pos2.getY() && z == pos2.getZ());
    }

    public static ChunkPos fromPos3D(Pos3D pos3D) {
        return new ChunkPos(((int)pos3D.getX())/16, ((int)pos3D.getY())/16, ((int)pos3D.getZ())/16);
    }
}
