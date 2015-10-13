package graphics;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import astro.Body;
import astro.SolarSystem;
import framework.Scene;
import math.Kepler;
import math.Point;

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
		drawPlanet(mySolarSystem.getSun(), gl, glu, glut);
		
		// Render planets
		for (Body planet : mySolarSystem.getPlanets()) {
			drawPlanet(planet, gl, glu, glut);
		}
		//System.out.println("View rendered");
	}

	private void drawPlanet(Body body, GL2 gl, GLU glu, GLUT glut) {
		gl.glPushMatrix(); {
			Point position = body.getPosition();			
			gl.glTranslated(position.getX() / Kepler.KM_PER_AU,
							position.getY() / Kepler.KM_PER_AU,
							position.getZ() / Kepler.KM_PER_AU);
			
			/*double rotation = body.getRotation();
			gl.glRotated(rotation, 0, 0, 1);*/

			// TODO implement radius (logarithmic?)
			double radius = body.getRadius();
			glut.glutWireSphere(0.1, 8, 8);
		} gl.glPopMatrix();
	}

	@Override
	public void setCamera(GL2 gl, GLU glu, GLUT glut) {
		glu.gluLookAt(0, 15, 5,  // from
				  	  0, 0, 0,	 // to
				  	  0, 0, 1);	 // up
	}

}
