package com.nti.Matrix;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.linearmath.MotionState;

public class SphereObject extends BaseObject {

	@Override
	public void Render(){
		Renderer.Sphere(worldTransform, len);
	}
	@Override
	public void Recalc(){
		density=composition.getDensity();
		v=mass/density;
		len=(float)Math.cbrt(3*v/(4*Math.PI));
		this.setCollisionShape(new SphereShape(len));
		dt=System.currentTimeMillis();
	}
	public SphereObject(float mass, MotionState motionState) {
		super(mass, motionState);
		
		// TODO Auto-generated constructor stub
	}

	public SphereObject(float mass, MotionState motionState, Vector3f localInertia) {
		super(mass, motionState, localInertia);
		// TODO Auto-generated constructor stub
	}

	public SphereObject(float mass, Vector3f pos, Quat4f rot, Vector3f localInertia) {
		super(mass, pos, rot, localInertia);
		// TODO Auto-generated constructor stub
	}

	public SphereObject(float mass, Vector3f pos, Quat4f rot) {
		super(mass, pos, rot);
		// TODO Auto-generated constructor stub
	}

	public SphereObject(float mass, Vector3f pos) {
		super(mass, pos);
		// TODO Auto-generated constructor stub
	}

}
