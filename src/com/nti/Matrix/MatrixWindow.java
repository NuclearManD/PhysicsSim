package com.nti.Matrix;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class MatrixWindow {

	long window;
    public MatrixWindow(int width, int height) {
		init(width,height);
	}
	public MatrixWindow() {
		// TODO Auto-generated constructor stub
		init(800,600);
	}
	public void init(int width, int height) {
		// TODO Auto-generated constructor stub
		GLFWErrorCallback.createPrint(System.err).set();
		if ( !glfwInit() ){
            System.err.println("Critical Error?");
            System.exit(0);
        }
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable


		// Create the window
		window = glfwCreateWindow(width, height, "Matrix Game", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		/*glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
		});*/

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
			window,
			(vidmode.width() - width) / 2,
			(vidmode.height() - height) / 2
		);

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		//*
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		perspective_horizontal(60.0f /* fov of 85 degrees */, (float)width/height, 0.01f, 10000.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);  // */
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
	}
	// Thankyou Edward for this beautiful function!
	void perspective_horizontal(float fov, float aspect, float front, float back)
	{
	    fov = fov*0.0174532925199432957692369076849f;                      // transform fov from degrees to radians
	    fov = 2.0f * (float)Math.atan(Math.tan(fov * 0.5f) / aspect);  // transform from horizontal fov to vertical fov

	    float tangent = (float)Math.tan(fov / 2.0f);               // tangent of half vertical fov
	    float height = front * tangent;                 // half height of near plane
	    float width = height * aspect;                  // half width of near plane

	    GL11.glFrustum(-width, width, -height, height, front, back);
	}
	public void preupdate(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
		GL11.glColor3f(0.5f,0.5f,1.0f);
	}
	public void postupdate(){

		glfwSwapBuffers(window); // swap the color buffers
	
		// Poll for window events. The key callback above will only be
		// invoked during this call.
		glfwPollEvents();
	}
	public void exit(){
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
	}
	public boolean doExit(){
		return glfwWindowShouldClose(window);
	}
}
