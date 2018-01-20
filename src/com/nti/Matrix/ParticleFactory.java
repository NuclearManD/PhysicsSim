package com.nti.Matrix;

import javax.vecmath.Vector3f;

public class ParticleFactory {
	private World env;
	public ParticleFactory(World env) {
		this.env=env;
	}
	public void makeBasicParticle(Vector3f pos, float[] color){
		Particle p=new Particle(1, pos,600);
		env.addObject(p);
		p.color_projection=color.clone();
		p.Recalc();
	}

}
