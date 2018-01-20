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
		Particle m=null;
		Particle last=null;
		Particle middle=null;
		for(int i=0;i<10;i++){
			m=new Particle(1f, new Vector3f(0,i*.12f,0));
			m.setAngularVelocity(new Vector3f(0.1f,0.1f-i/10f,(float)Math.pow(-1,i)*i/10f));
			m.setFriction(0);
			m.applyCentralImpulse(new Vector3f(0.1f,-0.3f+i%3/6f,0.6f-i%2/5f));
			m.Recalc();
			//m.color_projection[1]=(float)i/30.0f;
			if(i==5){
				middle=m;
				m.applyCentralImpulse(new Vector3f(-0.1f,1,0));
			}
			if(last!=null)
				m.addHeat(last, i-5);
			last=m;
			env.addObject(m);
		}
		QuantumEye Eye=new QuantumEye(.5f,new Vector3f(0,0,0));
		Eye.Recalc();
		
		env.addObject(Eye);
		
		Transform wt=new Transform();
		float rot=0;
		while(!window.doExit()){
			middle.getWorldTransform(wt);
			wt.origin.y+=(float) (3*Math.sin(0.0174533*rot));
			wt.origin.z+=(float) (3*Math.cos(0.0174533*rot));
			Eye.setWorldTransform(wt);
			Eye.camera.pitch=rot;
			window.preupdate();	
			env.update();
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
