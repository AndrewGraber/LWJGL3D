package com.anzanama.lwjgl3d.Render.Model;

import com.anzanama.lwjgl3d.GameObject.ModeledObject;
import com.anzanama.lwjgl3d.Render.Shader.StaticShader;
import com.anzanama.lwjgl3d.Render.Texture.ModelTexture;
import com.anzanama.lwjgl3d.Util.Config;
import com.anzanama.lwjgl3d.Util.Math;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

public class ModelRenderer {
    private Matrix4f projectionMatrix;

    public ModelRenderer(StaticShader shader) {
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(ModeledObject object, StaticShader shader) {
        TexturedModel texturedModel = object.getModel();
        RawModel rawModel = texturedModel.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        Matrix4f transformationMatrix = Math.createTransformationMatrix(object.getPos());
        shader.loadTransformationMatrix(transformationMatrix);
        ModelTexture texture = texturedModel.getTexture();
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth()/(float)Display.getHeight();
        float y_scale = (float) (1f / java.lang.Math.tan(java.lang.Math.toRadians(Config.getFloat("camera_fov") / 2f))) * aspectRatio;
        float x_scale = y_scale / aspectRatio;
        float frustum_length = Config.getFloat("render_distance") - Config.getFloat("near_clip");

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((Config.getFloat("render_distance") + Config.getFloat("near_clip"))/frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * Config.getFloat("near_clip") * Config.getFloat("render_distance"))/frustum_length);
        projectionMatrix.m33 = 0;
    }
}
