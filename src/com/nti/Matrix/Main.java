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
		BaseObject m=new CubeObject(1.2776e10f, new Vector3f(0,0,0));
		m.AddGravity(env);
		m.setAngularVelocity(new Vector3f(-1f,-1f,1f));
		m.AddGravity(env);
		m.Recalc();
		System.out.println(m.len);
		env.addObject(m);
		BaseObject r=new CubeObject(7000000000f, new Vector3f(-355,-110,0));
		r.setLinearVelocity(new Vector3f(-5,20.02f,0));
		r.Recalc();
		env.addObject(r);
		System.out.println("...");
		for(int i=0;i<1000;i++){
			r=new CubeObject(700000, new Vector3f(-350-((i/20))*6f,-100-(i%23)*6f,-100+(i%11)*6f));
			r.setAngularVelocity(new Vector3f(1,0.1f,2));
			r.setLinearVelocity(new Vector3f(-5+i%7,0.04f*(i+1),i%3-1));
			r.Recalc();
			env.addObject(r);
		}
		System.out.println("...");
		Transform tmp=new Transform();
		Transform wt=new Transform();
		Camera cam=new Camera(tmp);
		tmp.setRotation(new Quat4f(0,0,0,0));
		float rot=0;
		while(!window.doExit()){
			window.preupdate();	
			env.update();
			m.getWorldTransform(wt);
			tmp.origin.x=wt.origin.x;
			tmp.origin.y=wt.origin.y+(float) (1000f*Math.sin(0.0174533*rot));
			tmp.origin.z=wt.origin.z+(float) (1000f*Math.cos(0.0174533*rot));
			cam.pitch=rot;
			cam.Render();
			env.render();
			//Renderer.Plane(g.getMotionState().getWorldTransform(tmpt), 10);
			//Renderer.Plane(w1.getMotionState().getWorldTransform(tmpt), 10);
			window.postupdate();
			// wait to get 60 fps
			while(System.currentTimeMillis()-lt<32);
			lt=System.currentTimeMillis();
			rot+=.33f;
			if(rot>360)
				rot-=360;
		}
		window.exit();
	}

	public static void main(String[] args) {
		new Main();
	}

}
