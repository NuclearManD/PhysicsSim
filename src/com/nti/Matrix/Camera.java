package com.nti.Matrix;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.lwjgl.opengl.GL11;

import com.bulletphysics.linearmath.Transform;

public class Camera {
	Transform parent=null;
	float pitch=0f;
	float yaw=0;
	public Camera(Transform parent){
		this.parent=parent;
	}
	public void Render(){
		Quat4f r=new Quat4f();
		Vector3f pos=parent.origin;
		parent.getRotation(r);
		GL11.glLoadIdentity();
        GL11.glRotatef(pitch, 1, 0, 0);
        GL11.glRotatef(yaw, 0, 1, 0);
        GL11.glTranslatef(-pos.x, -pos.y, -pos.z);
	}
}