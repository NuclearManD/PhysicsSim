package com.nti.Matrix;

import java.nio.DoubleBuffer;
import org.lwjgl.system.MemoryStack;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.system.MemoryStack.*;

import javax.vecmath.Vector3f;

public class QuantumEye extends Particle {
	private static final double mouseSensitivity = 0.025;
	private static final float maxLook = 85;
	protected Camera camera;
	MemoryStack stack=stackGet();
	DoubleBuffer xpos,ypos;

	double ldx=0,ldy=0;
	
	public QuantumEye(float mass, Vector3f v) {
		super(mass, v, 0);
		camera=new Camera(worldTransform);
		color_projection=new float[3];
	}
	public void tick(){
		camera.Render();
		xpos = stack.mallocDouble(1);
		ypos = stack.mallocDouble(1);
		glfwGetCursorPos(Main.window.window, xpos, ypos);
		double x=xpos.get(),y=-ypos.get();
		double dx=ldx-x, dy=ldy-y;
		ldx=x;
		ldy=y;
        camera.yaw += dx * mouseSensitivity * delta;
        camera.pitch -= -dy * mouseSensitivity * delta;
        camera.yaw=Math.max(-maxLook, Math.min(maxLook, camera.yaw));
	}
	public void Render(){}// render nothing
	public void translate(Vector3f t){
		super.translate(t);
		camera.parent=this.worldTransform;
	}
}
