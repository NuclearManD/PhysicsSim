package com.nti.Matrix;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;

public abstract class BaseObject extends RigidBody {
	float mass=1.0f; 	// mass in grams
	float density=0.7f; // density in g/m3
	float v;			// volume in m3
	public float len;	// cube side length
	Mixture composition;// object composition
	World env;
	boolean hasgravity=false;
	long dt=0;
	Vector3f color;
	// draw cube
	public abstract void Render();
	public void Update(){
		if(hasgravity)
			DoGravTick();
	}
	private void DoGravTick(){
		if(dt==0){
			dt=System.currentTimeMillis();
			return;
		}
		for(BaseObject i : env.objects){
			if(i!=this){
				Vector3f pos=(Vector3f) worldTransform.origin.clone(),tmp=new Vector3f(0,0,0);
				pos.sub(i.worldTransform.origin);
				pos.normalize();
				float k=(float)Math.sqrt(pos.x*pos.x+pos.y*pos.y+pos.z*pos.z);
				float force=6.67f*mass*i.mass*(System.currentTimeMillis()-dt)/((float)Math.pow(10, 11)*(k*k)*1000f);
				float force_a=-force/mass,force_b=force/i.mass;
				getLinearVelocity(tmp);
				tmp.add(new Vector3f(pos.x*force_a,pos.y*force_a,pos.z*force_a));
				setLinearVelocity(tmp);
				i.getLinearVelocity(tmp);
				tmp.add(new Vector3f(pos.x*force_b,pos.y*force_b,pos.z*force_b));

				i.setLinearVelocity(tmp);
			}
		}
		dt=System.currentTimeMillis();
	}
	public void AddGravity(World e){
		hasgravity=true;
		env=e;
	}
	public void RmGravity(){
		hasgravity=false;
	}
	// for recalculating object properties after density/mass/composition change
	public abstract void Recalc();
	
	public BaseObject(float mass, MotionState motionState) {
		super(Math.abs(mass), motionState,null);
		this.mass=mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}

	public BaseObject(float mass, MotionState motionState, Vector3f localInertia) {
		super(Math.abs(mass), motionState, null, localInertia);
		this.mass=mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}
	public BaseObject(float mass, Vector3f pos, Quat4f rot, Vector3f localInertia) {
		super(Math.abs(mass),new DefaultMotionState(new Transform(new Matrix4f(rot, pos, 1.0f))), null, localInertia);
		this.mass=mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}
	public BaseObject(float mass, Vector3f pos, Quat4f rot) {
		super(Math.abs(mass), new DefaultMotionState(new Transform(new Matrix4f(rot, pos, 1.0f))), null);
		this.mass=mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}
	public BaseObject(float mass, Vector3f pos) {
		super(Math.abs(mass), new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), pos, 1.0f))), null);
		this.mass=mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}
	@Deprecated
	public BaseObject(RigidBodyConstructionInfo constructionInfo) {
		super(constructionInfo);
		this.mass=constructionInfo.mass;
		composition=new Mixture();
		Recalc();
		// TODO Auto-generated constructor stub
	}

}
