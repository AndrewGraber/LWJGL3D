package com.anzanama.lwjgl3d.Render;

import com.anzanama.lwjgl3d.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Render.Model.RawModel;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Render.Shader.TerrainShader;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Render.Texture.TerrainTexturePack;
import com.anzanama.lwjgl3d.Util.Math;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.TerrainChunk;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import java.util.List;

public class TerrainRenderer {
    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.connectTextureUnits();
        shader.stop();
    }

    public void render(List<TerrainChunk> terrainChunks) {
        for(TerrainChunk terrainChunk : terrainChunks) {
            prepareTerrain(terrainChunk);
            loadModelMatrix(terrainChunk);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrainChunk.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }

    private void prepareTerrain(TerrainChunk terrainChunk) {
        RawModel rawModel = terrainChunk.getModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        bindTextures(terrainChunk);
        shader.loadShineVariables(1, 0);
    }

    private void bindTextures(TerrainChunk terrainChunk) {
        TerrainTexturePack texturePack = terrainChunk.getTexturePack();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getBackgroundTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getrTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getgTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getbTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrainChunk.getBlendMap().getTextureID());
    }

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void loadModelMatrix(TerrainChunk terrainChunk) {
        Matrix4f transformationMatrix = Math.createTransformationMatrix(Pos3D.fromChunkPos(terrainChunk.getPos()));
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
