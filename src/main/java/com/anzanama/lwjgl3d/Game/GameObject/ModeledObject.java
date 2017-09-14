package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Main;
import com.anzanama.lwjgl3d.Render.Model.TexturedModel;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

/**
 * A child of {@link EmptyObject} to be used for anything in the game that needs a model. This is probably a majority
 * of the objects you'll have in your game.
 */
public class ModeledObject extends EmptyObject {
    /**
     * The {@link TexturedModel} to render when this object's render function is called.
     */
    private TexturedModel model;

    public ModeledObject(TexturedModel model, Pos3D pos, World world) {
        super(pos, world);
        this.model = model;
    }

    /**
     * Calls the {@link EmptyObject#update(float)} method, then
     * {@link com.anzanama.lwjgl3d.Render.Renderer.GameRenderer#processModeledObject(ModeledObject)} which tells
     * the GameRenderer to render this model.
     *
     * @param delta the time (in seconds) between frames
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        Main.getGame().getRenderer().processModeledObject(this);
    }

    public TexturedModel getModel() {
        return model;
    }
}
