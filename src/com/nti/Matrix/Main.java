package com.nti.Matrix;

import java.util.Random;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

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
		BaseObject m=new SphereObject(1.2776e10f, new Vector3f(0,0,-2000));
		m.AddGravity(env);
		m.setAngularVelocity(new Vector3f(-1f,-1f,1f));
		m.composition.components=new float[32];
		m.composition.components[30]=30;
		m.composition.components[2]=25;
		m.composition.components[4]=20;
		m.composition.components[10]=15;
		m.composition.components[27]=10;
		m.AddGravity(env);
		m.Recalc();
		System.out.println(m.len);
		env.addObject(m);
		BaseObject r=new SphereObject(7000000000f, new Vector3f(-355,-110,-2000));
		//r.setAngularVelocity(new Vector3f(1,0.1f,2));
		r.setLinearVelocity(new Vector3f(-5,20.02f,0));
		r.composition.components=new float[32];
		r.composition.components[9]=1;
		r.composition.components[3]=1;
		r.composition.components[7]=1;
		r.composition.components[0]=8;
		r.composition.components[5]=1;
		r.composition.components[19]=1;
		r.composition.components[13]=1;
		r.composition.components[17]=1;
		r.composition.components[10]=15;
		r.composition.components[28]=80;
		//r.AddGravity(env);
		r.Recalc();
		env.addObject(r);
		for(int i=0;i<1000;i++){
			r=new CubeObject(700000, new Vector3f(-350-((i/20))*6f,-100-(i%20)*6f,-2000));
			//r.setAngularVelocity(new Vector3f(1,0.1f,2));
			r.setLinearVelocity(new Vector3f(-5+i%7,0.04f*(i+1),0));
			r.composition.components=new float[32];
			r.composition.components[9]=25+(i%22)-10;
			r.composition.components[3]=25-(i%15)-10;
			r.composition.components[7]=25+(i/10%60)-10;
			r.composition.components[0]=20-(i/10%20)-10;
			r.composition.components[5]=25-(i/10%8)-10;
			r.composition.components[19]=20+(i%23)-10;
			r.composition.components[13]=20-(i%17)-10;
			r.composition.components[17]=20+(i/10%67)-10;
			r.composition.components[10]=25-(i/10%21)-10;
			r.composition.components[15]=20-(i/10%7)-10;
			//r.AddGravity(env);
			r.Recalc();
			env.addObject(r);
		}
		Transform tmp=new Transform();
		Transform wt=new Transform();
		Camera cam=new Camera(tmp);
		tmp.setRotation(new Quat4f(0,0,0,0));
		while(!window.doExit()){
			window.preupdate();	
			env.update();
			m.getWorldTransform(wt);
			tmp.origin.x=wt.origin.x;
			tmp.origin.y=wt.origin.y;
			tmp.origin.z=wt.origin.z+1000;
			cam.Render();
			env.render();
			//Renderer.Plane(g.getMotionState().getWorldTransform(tmpt), 10);
			//Renderer.Plane(w1.getMotionState().getWorldTransform(tmpt), 10);
			window.postupdate();
			// wait to get 60 fps
			while(System.currentTimeMillis()-lt<32);
			lt=System.currentTimeMillis();
		}
		window.exit();
	}

	public static void main(String[] args) {
		new Main();
	}

}
