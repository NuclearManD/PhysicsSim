package com.nti.Matrix;

import java.nio.FloatBuffer;

import javax.vecmath.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import com.bulletphysics.linearmath.Transform;

public class Renderer {

	public static void Cube(Transform x,float size) {
		// These can be pre-allocated.
		float[] matrix = new float[16];
		FloatBuffer transformBuffer = BufferUtils.createFloatBuffer(16);
		x.getOpenGLMatrix(matrix);

		// Put the transformation matrix into a FloatBuffer.
		transformBuffer.clear();
		transformBuffer.put(matrix);
		transformBuffer.flip();
		//size=size/2;
		GL11.glPushMatrix(); // Save the current OpenGL transformation
		GL11.glMultMatrixf(transformBuffer); // Apply the object transformation
		GL11.glBegin(GL11.GL_QUADS);		// Start Drawing The Cube
		GL11.glColor3f(0.0f,1.0f,0.0f);          // Set The Color To Green
		GL11.glVertex3f( size, size,-size);          // Top Right Of The Quad (Top)
		GL11.glVertex3f(-size, size,-size);          // Top Left Of The Quad (Top)
		GL11.glVertex3f(-size, size, size);          // Bottom Left Of The Quad (Top)
		GL11.glVertex3f( size, size, size);          // Bottom Right Of The Quad (Top)
		GL11.glColor3f(1.0f,0.5f,0.0f);          // Set The Color To Orange
		GL11.glVertex3f( size,-size, size);          // Top Right Of The Quad (Bottom)
		GL11.glVertex3f(-size,-size, size);          // Top Left Of The Quad (Bottom)
		GL11.glVertex3f(-size,-size,-size);          // Bottom Left Of The Quad (Bottom)
		GL11.glVertex3f( size,-size,-size);          // Bottom Right Of The Quad (Bottom)
		GL11.glColor3f(1.0f,0.0f,0.0f);          // Set The Color To Red
		GL11.glVertex3f( size, size, size);          // Top Right Of The Quad (Front)
		GL11.glVertex3f(-size, size, size);          // Top Left Of The Quad (Front)
		GL11.glVertex3f(-size,-size, size);          // Bottom Left Of The Quad (Front)
		GL11.glVertex3f( size,-size, size);          // Bottom Right Of The Quad (Front)
		GL11.glColor3f(1.0f,1.0f,0.0f);          // Set The Color To Yellow
		GL11.glVertex3f( size,-size,-size);          // Bottom Left Of The Quad (Back)
		GL11.glVertex3f(-size,-size,-size);          // Bottom Right Of The Quad (Back)
		GL11.glVertex3f(-size, size,-size);          // Top Right Of The Quad (Back)
		GL11.glVertex3f( size, size,-size);          // Top Left Of The Quad (Back)
		GL11.glColor3f(0.0f,0.0f,1.0f);          // Set The Color To Blue
		GL11.glVertex3f(-size, size, size);          // Top Right Of The Quad (Left)
		GL11.glVertex3f(-size, size,-size);          // Top Left Of The Quad (Left)
		GL11.glVertex3f(-size,-size,-size);          // Bottom Left Of The Quad (Left)
		GL11.glVertex3f(-size,-size, size);          // Bottom Right Of The Quad (Left)
		GL11.glColor3f(1.0f,0.0f,1.0f);          // Set The Color To Violet
        GL11.glVertex3f( size, size, size);          // Top Left Of The Quad (Right)
        GL11.glVertex3f( size,-size, size);          // Bottom Left Of The Quad (Right)
        GL11.glVertex3f( size,-size,-size);          // Bottom Right Of The Quad (Right)
        GL11.glVertex3f( size, size,-size);          // Top Right Of The Quad (Right)
        GL11.glEnd();                        // Done Drawing The Quad
        GL11.glPopMatrix(); // Restore the saved transformation
	}
	public static void Plane(Transform x,float size){
		// These can be pre-allocated.
		float[] matrix = new float[16];
		FloatBuffer transformBuffer = BufferUtils.createFloatBuffer(16);
		x.getOpenGLMatrix(matrix);

		// Put the transformation matrix into a FloatBuffer.
		transformBuffer.clear();
		transformBuffer.put(matrix);
		transformBuffer.flip();
		//size=size*2;
		GL11.glPushMatrix(); // Save the current OpenGL transformation
		GL11.glMultMatrixf(transformBuffer); // Apply the object transformation
		GL11.glBegin(GL11.GL_QUADS);		// Start Drawing The Cube
		GL11.glColor3f(0.50f,0.50f,0.50f);  // Set The Color To Grey
		GL11.glVertex3f( size, -10,-size);  // Top Right Of The Quad (Top)
		GL11.glVertex3f(-size, -10,-size);  // Top Left Of The Quad (Top)
		GL11.glColor3f(0.50f,1.00f,0.50f);  // Set The Color To Rand
		GL11.glVertex3f(-size, -10, size);  // Bottom Left Of The Quad (Top)
		GL11.glVertex3f( size, -10, size);  // Bottom Right Of The Quad (Top)
        GL11.glEnd();                       // Done Drawing The Quad
        GL11.glPopMatrix(); // Restore the saved transformation
	}
	public static void Cube(Transform x, float size, Vector3f color) {
		// TODO Auto-generated method stub
		// These can be pre-allocated.
				float[] matrix = new float[16];
				FloatBuffer transformBuffer = BufferUtils.createFloatBuffer(16);
				x.getOpenGLMatrix(matrix);

				// Put the transformation matrix into a FloatBuffer.
				transformBuffer.clear();
				transformBuffer.put(matrix);
				transformBuffer.flip();
				//size=size/2;
				GL11.glPushMatrix(); // Save the current OpenGL transformation
				GL11.glMultMatrixf(transformBuffer); // Apply the object transformation
				GL11.glBegin(GL11.GL_QUADS);		// Start Drawing The Cube
				GL11.glColor3f(color.x, color.y, color.z);
				GL11.glVertex3f( size, size,-size);          // Top Right Of The Quad (Top)
				GL11.glVertex3f(-size, size,-size);          // Top Left Of The Quad (Top)
				GL11.glVertex3f(-size, size, size);          // Bottom Left Of The Quad (Top)
				GL11.glVertex3f( size, size, size);          // Bottom Right Of The Quad (Top)
				GL11.glColor3f(color.x, color.y, color.z);
				GL11.glVertex3f( size,-size, size);          // Top Right Of The Quad (Bottom)
				GL11.glVertex3f(-size,-size, size);          // Top Left Of The Quad (Bottom)
				GL11.glVertex3f(-size,-size,-size);          // Bottom Left Of The Quad (Bottom)
				GL11.glVertex3f( size,-size,-size);          // Bottom Right Of The Quad (Bottom)
				GL11.glColor3f(color.x, color.y, color.z);
				GL11.glVertex3f( size, size, size);          // Top Right Of The Quad (Front)
				GL11.glVertex3f(-size, size, size);          // Top Left Of The Quad (Front)
				GL11.glVertex3f(-size,-size, size);          // Bottom Left Of The Quad (Front)
				GL11.glVertex3f( size,-size, size);          // Bottom Right Of The Quad (Front)
				GL11.glColor3f(color.x, color.y, color.z);
				GL11.glVertex3f( size,-size,-size);          // Bottom Left Of The Quad (Back)
				GL11.glVertex3f(-size,-size,-size);          // Bottom Right Of The Quad (Back)
				GL11.glVertex3f(-size, size,-size);          // Top Right Of The Quad (Back)
				GL11.glVertex3f( size, size,-size);          // Top Left Of The Quad (Back)
				GL11.glColor3f(color.x, color.y, color.z);
				GL11.glVertex3f(-size, size, size);          // Top Right Of The Quad (Left)
				GL11.glVertex3f(-size, size,-size);          // Top Left Of The Quad (Left)
				GL11.glVertex3f(-size,-size,-size);          // Bottom Left Of The Quad (Left)
				GL11.glVertex3f(-size,-size, size);          // Bottom Right Of The Quad (Left)
				GL11.glColor3f(color.x, color.y, color.z);
		        GL11.glVertex3f( size, size, size);          // Top Left Of The Quad (Right)
		        GL11.glVertex3f( size,-size, size);          // Bottom Left Of The Quad (Right)
		        GL11.glVertex3f( size,-size,-size);          // Bottom Right Of The Quad (Right)
		        GL11.glVertex3f( size, size,-size);          // Top Right Of The Quad (Right)
		        GL11.glEnd();                        // Done Drawing The Quad
		        GL11.glPopMatrix(); // Restore the saved transformation
	}
}
