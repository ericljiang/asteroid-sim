package graphics;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import astro.Body;
import astro.SolarSystem;
import framework.Scene;

public class View extends Scene {
	private SolarSystem mySolarSystem;
	
	public View(SolarSystem solar) {
		mySolarSystem = solar;
	}
	
	@Override
	public void animate(GL2 gl, GLU glu, GLUT glut) {
		// consider updating model on separate timeline
		mySolarSystem.update();
	}

	@Override
	public void display(GL2 gl, GLU glu, GLUT glut) {
		// Render sun
		drawPlanet(mySolarSystem.getSun());
		
		// Render planets
		for (Body planet : mySolarSystem.getPlanets()) {
			drawPlanet(planet);
		}
		System.out.println("View rendered");
	}

	private void drawPlanet(Body body) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCamera(GL2 gl, GLU glu, GLUT glut) {
		// TODO Auto-generated method stub
		
	}

}
