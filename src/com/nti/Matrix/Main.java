package com.nti.Matrix;

import java.util.Random;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import org.lwjgl.opengl.GL11;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

public class Main {
	MatrixWindow window;
	World env;
	public Main() {
		// TODO Auto-generated constructor stub
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		env=new World();
		window=new MatrixWindow();
		long lt=System.currentTimeMillis();
		BaseObject r=new BaseObject(7000000000f, new Vector3f(150,-10,-2000));
		r.AddGravity(env);
		r.setAngularVelocity(new Vector3f(-1f,-1f,1f));
		r.composition.components=new float[32];
		r.composition.components[0]=1;
		r.Recalc();
		env.addObject(r);
		for(int i=0;i<1000;i++){
			r=new BaseObject(700, new Vector3f(-350-((i/20))*6f,-100-(i%20)*6f,-2000));
			//r.setAngularVelocity(new Vector3f(1,0.1f,2));
			r.setLinearVelocity(new Vector3f(-5,15,0));
			r.composition.components=new float[32];
			r.composition.components[9]=11;
			r.Recalc();
			env.addObject(r);
		}
		while(!window.doExit()){
			window.preupdate();	
			env.update();
			env.render();
			//Renderer.Plane(g.getMotionState().getWorldTransform(tmpt), 10);
			//Renderer.Plane(w1.getMotionState().getWorldTransform(tmpt), 10);
			window.postupdate();
			
			// wait to get 60 fps
			while(System.currentTimeMillis()-lt<32);
		}
		window.exit();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m=new Main();
	}

}
