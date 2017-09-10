package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.ChunkPos;
import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

import java.util.ArrayList;

/**
 * Serves as the base class for *almost* any {@link GameObject} that you should need in your world. It provides a
 * {@link Pos3D} which serves as the position, rotation, and scale of the {@link GameObject}. It also provides a
 * list of its {@link #children} which are also {@link GameObject}s. So you can have as many nested {@link GameObject}s
 * as you desire.
 *
 * Also, you don't need to worry about updating children, because they are automatically updated in the
 * {@link #update(float)} function. Just make sure that if you override the {@link #update(float)} or
 * {@link #render(float)} functions, that you call these functions in your override. Otherwise, children will not
 * be properly updated and rendered.
 */
public class EmptyObject extends GameObject {
    /**
     * The {@link Pos3D} of the object. Provides a {@link com.anzanama.lwjgl3d.Game.World.Position.Loc3D} (xyz position),
     * a {@link com.anzanama.lwjgl3d.Game.World.Position.Rot3D} (pitch, yaw, roll), and a scale factor.
     *
     * @see Pos3D
     */
    protected Pos3D pos;

    /**
     * The objects that are children of this object, or are inside of the object. You can nest infinitely many
     * children inside children (theoretically). TODO: provide support for relative positioning of children
     */
    protected ArrayList<GameObject> children;

    /**
     * Basic constructor for the class. Adds the object to its respective chunk, so that it will automatically
     * be updated when the rest of the world updates, so that you don't have to track all your game objects.
     *
     * @param pos position to start object at
     * @param world the {@link World} in which the object lives
     * @param children child objects to add to this object. More can be added at a later time.
     */
    public EmptyObject(Pos3D pos, World world, ArrayList<GameObject> children) {
        this.pos = pos;
        this.children = children;
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }

    /**
     * Same as above constructor, just takes infinitely many GameObjects as children instead of an ArrayList
     *
     * @param pos position to put the object at
     * @param world the world the object lives in
     * @param children the children to add to the object's {@link #children} list
     */
    public EmptyObject(Pos3D pos, World world, GameObject... children) {
        this.pos = pos;
        this.children = new ArrayList<>();
        for(GameObject obj : children) {
            this.children.add(obj);
        }
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }


    public EmptyObject(World world, GameObject... children) {
        this.pos = new Pos3D();
        this.children = new ArrayList<>();
        for(GameObject obj : children) {
            this.children.add(obj);
        }
        world.getChunk(ChunkPos.fromPos3D(pos)).addGameObject(this);
    }

    public EmptyObject(Pos3D pos, World world) {
        this(pos, world, new ArrayList<GameObject>());
    }

    public EmptyObject(World world) {
        this(new Pos3D(), world, new ArrayList<GameObject>());
    }

    @Override
    public Pos3D getPos() {
        return pos;
    }

    @Override
    public void setPos(Pos3D pos) {
        this.pos = pos;
    }

    /**
     * Calls the {@link GameObject#update(float)} function for every child object of this one.
     *
     * @param delta the time (in seconds) between frames
     */
    @Override
    public void update(float delta) {
        for(GameObject obj : children) {
            obj.update(delta);
        }
    }

    /**
     * Calls the {@link GameObject#render(float)} function for every child object of this one.
     *
     * @param delta the time (in seconds) between frames
     */
    @Override
    public void render(float delta) {
        for(GameObject obj : children) {
            obj.render(delta);
        }
    }
}
