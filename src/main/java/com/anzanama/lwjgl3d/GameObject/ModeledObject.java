package com.anzanama.lwjgl3d.GameObject;

import com.anzanama.lwjgl3d.Main;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.World.Position.Pos3D;
import com.anzanama.lwjgl3d.World.World;

public class ModeledObject extends EmptyObject {
    private TexturedModel model;

    public ModeledObject(TexturedModel model, Pos3D pos, World world) {
        super(pos, world);
        this.model = model;
    }

    @Override
    public void render(float delta) {
        Main.getGame().getRenderer().processModeledObject(this);
    }

    public TexturedModel getModel() {
        return model;
    }
}
