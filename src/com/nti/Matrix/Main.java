package com.nti.Matrix;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.Random;

import javax.vecmath.Vector3f;

import org.lwjgl.BufferUtils;

public class Main {
	public static MatrixWindow window;
	public static ParticleFactory f;
	World env;
	public Main() {
		// TODO Auto-generated constructor stub
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		env=new World();
		window=new MatrixWindow();
		

		FloatBuffer Sun= BufferUtils.createFloatBuffer(4);
		Sun.put(-8f).put(4f).put(8f).put(0).flip();
		FloatBuffer whiteLight= BufferUtils.createFloatBuffer(4);
		whiteLight.put(1f).put(1f).put(1f).put(1f).flip();
		
		glLightfv(GL_LIGHT0, GL_POSITION, Sun);				// sets light position
		glLightfv(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
		glLightfv(GL_LIGHT0, GL_DIFFUSE, whiteLight);	
		glLightf(GL_LIGHT0,GL11.GL_EMISSION,33f);
		long lt=System.currentTimeMillis();
		QuantumEye Eye=new QuantumEye(.5f,new Vector3f(-8f,4f,8f));
		Eye.Recalc();
		f=new ParticleFactory(env);
		float[] color={1,1,2};
		f.makeBasicParticle(new Vector3f(0,0,0),color);
		color[0]=3;
		f.makeBasicParticle(new Vector3f(1,0,0),color);
		color[0]=0;
		color[1]=0;
		f.makeBasicParticle(new Vector3f(1,1,0),color);
		f.makeBasicParticle(new Vector3f(1,0,1),color);
		color[1]=3;
		f.makeBasicParticle(new Vector3f(1,1,1),color);
		env.objects.get(0).setAngularVelocity(new Vector3f(1,1,6));
		env.objects.get(3).setAngularVelocity(new Vector3f(-1,-5,10));
		//env.objects.get(3).applyCentralForce(new Vector3f(-10,2,10));
		env.addObject(Eye);
		
		while(!window.doExit()){
			window.preupdate();	
			glLightfv(GL_LIGHT0, GL_POSITION, Sun);	
			env.update();
			env.render();
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
