package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Main;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Render.Shader.StaticShader;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;

public class ModeledObject extends EmptyObject {
    private TexturedModel model;
    private StaticShader shader;

    public ModeledObject(TexturedModel model, Pos3D pos, World world, StaticShader shader) {
        super(pos, world);
        this.model = model;
        this.shader = shader;
    }

    @Override
    public void render() {
        shader.start();
        Main.getGame().getModelRenderer().render(this, shader);
        shader.stop();
    }

    public TexturedModel getModel() {
        return model;
    }
}
