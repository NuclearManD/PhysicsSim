package com.nti.Matrix;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3f;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;

public class World {
	DiscreteDynamicsWorld env;
	private long lt;
	List<BaseObject> objects = new ArrayList<BaseObject>();
	public World() {
		// TODO Auto-generated constructor stub
		BroadphaseInterface broadphase = new DbvtBroadphase();
		DefaultCollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
		CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
		SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();
		env = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
		env.setGravity(new Vector3f(0, 0, 0));
		lt=System.currentTimeMillis();
	}
	// add an ordinary object
	public void addObject(BaseObject r){
		env.addRigidBody(r);
		objects.add(r);
	}
	// Add some strage unnatural object, like a floor
	public void addQuantum(RigidBody r){
		env.addRigidBody(r);
	}
	public void update(){
		int dt=(int) (System.currentTimeMillis()-lt);
		if(dt<1)		// prevent simulations of less than one ms
			return;
		lt=System.currentTimeMillis();
		env.stepSimulation(((float)dt)/1000.0f,10);
		for(BaseObject i : objects){
			i.Update();
		}
	}
	public void render() {
		for(BaseObject i : objects){
			i.Render();
		}
	}
}
