package com.nti.Matrix;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.linearmath.MotionState;

public class CubeObject extends BaseObject {
	public void Recalc(){
		density=100000;
		v=mass/density;
		len=(float)Math.cbrt(v);
		this.setCollisionShape(new BoxShape(new Vector3f(len,len,len)));
		dt=System.currentTimeMillis();
		color=new Vector3f((float)Math.random(),(float)Math.random(),(float)Math.random());
		color.normalize();
	}
	public void Render(){
		Renderer.Cube(worldTransform,len,color);
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
