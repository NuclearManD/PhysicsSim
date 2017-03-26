package com.nti.Matrix;

import java.util.Random;

import javax.vecmath.Vector3f;

public class Mixture {
	float[] components;
	public Mixture(){
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		components=new float[32];
		for(int i=0;i<32;i++){
			components[i]=0;
		}
		components[rand.nextInt(32)]=1; // Made of random element
	}
	public float getDensity() {  // in g/m3
		// TODO Auto-generated method stub
		float out=0;
		float total=0;
		// i represents atomic #
		for(int i=1;i<33;i++){
			out+=components[i-1]*(100000*i)/(2*Math.abs(i%8-4)+2);
			total+=components[i-1];
		}
		return out/total;
	}
	public Vector3f GetColor(){
		Vector3f tmp=new Vector3f(0,0,0);
		for(int i=0;i<32;i++){
			byte x=(byte) ((i+1)%8);
			tmp.x+=components[i]*(x&1);
			tmp.y+=components[i]*((x&2)>>1);
			tmp.z+=components[i]*((x&4)>>2);
		}
		tmp.normalize();
		return tmp;
	}
	// Helper functions

	// m3 to pm3
	public static double tonano3(double m3){
		return m3*Math.pow(10, 27);
	}
	// pm3 to m3
	public static double fromnano3(double pm3){
		return pm3*Math.pow(10, -27);
	}
	// m to pm
	public static double tonano(double m){
		return m*Math.pow(10, 9);
	}
	// pm to m
	public static double fromnano(double pm){
		return pm*Math.pow(10, -9);
	}
}
