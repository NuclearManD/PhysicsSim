package com.nti.Matrix;

import javax.vecmath.Vector3f;

public class QuantumEye extends Particle {
	protected Camera camera;
	public QuantumEye(float mass, Vector3f v) {
		super(mass, v, 0);
		camera=new Camera(worldTransform);
		color_projection=new float[3];
	}
	public void tick(){
		camera.Render();
	}
	public void Render(){}
	public void translate(Vector3f t){
		super.translate(t);
		camera.parent=this.worldTransform;
	}
}
