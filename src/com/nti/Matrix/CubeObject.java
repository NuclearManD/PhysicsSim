package com.nti.Matrix;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.MotionState;

public class CubeObject extends BaseObject {
	public void Recalc(){
		density=composition.getDensity();
		v=mass/density;
		len=(float)Math.cbrt(v);
		this.setCollisionShape(new BoxShape(new Vector3f(len,len,len)));
		dt=System.currentTimeMillis();
	}
	public void Render(){
		Renderer.Cube(worldTransform,len);
	}
	public CubeObject(float mass, MotionState motionState) {
		super(mass, motionState);
		// TODO Auto-generated constructor stub
	}

	public CubeObject(float mass, MotionState motionState, Vector3f localInertia) {
		super(mass, motionState, localInertia);
		// TODO Auto-generated constructor stub
	}

	public CubeObject(float mass, Vector3f pos, Quat4f rot, Vector3f localInertia) {
		super(mass, pos, rot, localInertia);
		// TODO Auto-generated constructor stub
	}

	public CubeObject(float mass, Vector3f pos, Quat4f rot) {
		super(mass, pos, rot);
		// TODO Auto-generated constructor stub
	}

	public CubeObject(float mass, Vector3f pos) {
		super(mass, pos);
		// TODO Auto-generated constructor stub
	}

}
