package com.anzanama.lwjgl3d.Render.Model;

public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vectexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vectexCount;
    }


    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }
}
