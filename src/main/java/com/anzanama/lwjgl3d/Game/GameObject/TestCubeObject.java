package com.anzanama.lwjgl3d.Game.GameObject;

import com.anzanama.lwjgl3d.Game.World.Position.Pos3D;
import com.anzanama.lwjgl3d.Game.World.World;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class TestCubeObject extends EmptyObject {

    public TestCubeObject(Pos3D pos, World world) {
        super(pos, world);
    }

    @Override
    public void render(float delta) {
        glPushMatrix();
        glTranslatef(pos.getX(), pos.getY(), pos.getZ());
        glRotatef(pos.getRot().getPitch(), 1, 0, 0);
        glRotatef(pos.getRot().getYaw(), 0, 1, 0);
        glRotatef(pos.getRot().getRoll(), 0, 0, 1);
        glBegin(GL_QUADS);
        {
            //Back Face
            glColor3f(1.0f, 0f, 0f);
            glVertex3f(-1, -1, -1);
            glVertex3f(-1, 1, -1);
            glVertex3f(1, 1, -1);
            glVertex3f(1, -1, -1);

            //Right Face
            glColor3f(0f, 0f, 1.0f);
            glVertex3f(1, -1, -1);
            glVertex3f(1, -1, 1);
            glVertex3f(1, 1, 1);
            glVertex3f(1, 1, -1);

            //Top Face
            glColor3f(1.0f, 0f, 1.0f);
            glVertex3f(-1, 1, -1);
            glVertex3f(1, 1, -1);
            glVertex3f(1, 1, 1);
            glVertex3f(-1, 1, 1);

            //Left Face
            glColor3f(0f, 1.0f, 0f);
            glVertex3f(-1, -1, -1);
            glVertex3f(-1, -1, 1);
            glVertex3f(-1, 1, 1);
            glVertex3f(-1, 1, -1);

            //Bottom Face
            glColor3f(0f, 1.0f, 1.0f);
            glVertex3f(-1, -1, -1);
            glVertex3f(1, -1, -1);
            glVertex3f(1, -1, 1);
            glVertex3f(-1, -1, 1);

            //Front Face
            glColor3f(1.0f, 0.5f, 0f);
            glVertex3f(-1, -1, 1);
            glVertex3f(-1, 1, 1);
            glVertex3f(1, 1, 1);
            glVertex3f(1, -1, 1);
        }
        glEnd();
        glPopMatrix();
    }
}
