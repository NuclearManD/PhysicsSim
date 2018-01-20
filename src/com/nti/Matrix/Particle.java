package com.nti.Matrix;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.collision.shapes.BoxShape;

public /*abstract*/ class Particle extends CubeObject {
	private float heat=100;// Joules
	private float qE=1;  // Joules
	protected float[] color_projection={.5f,.5f,.5f};
	protected float delta=0;
	private long timeLast;
	@Deprecated
	public Particle(float mass, Vector3f pos, Quat4f rot, Vector3f localInertia) {
		super(mass, pos, rot, localInertia);
		timeLast=System.currentTimeMillis();
	}
	public Particle(float mass, Vector3f v) {
		super(mass,v);
		this.mass=mass;
		timeLast=System.currentTimeMillis();
	}
	public Particle(float mass, Vector3f v, float h) {
		super(mass,v);
		this.mass=mass;
		heat=h;
		timeLast=System.currentTimeMillis();
	}
	//public abstract void tick();
	public void tick(){
		heatToQE(.16666f); // 10 W for 60 FPS
	}
	public final void Update(){
		tick();
		delta=System.currentTimeMillis()-timeLast;
		timeLast=System.currentTimeMillis();
	}
	private final void calcColor(){
		if(color_projection==null){
			color_projection=new float[3];
			color_projection[2]=1;
		}
		color=new Vector3f(color_projection[0]+heat/100,color_projection[1],color_projection[2]);
		color.normalize();
		//System.out.println(color.toString());
	}
	public void Recalc(){
		v=1.25e-4f;
		density=mass/v;
		len=(float)Math.cbrt(v);
		this.setCollisionShape(new BoxShape(new Vector3f(len,len,len)));
		dt=System.currentTimeMillis();
		calcColor();
	}
	protected final void heatToQE(float joules){
		if(heat<joules)
			return;
		heat-=joules;
		qE+=joules;
		calcColor();
	}
	public void addHeat(Particle x, float amt){
		x.antiAddHeat(amt);
		heat+=amt;
		calcColor();
	}
	public final void antiAddHeat(float amt){
		heat-=amt;
		calcColor();
	}
}