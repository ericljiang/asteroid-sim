package graphics;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.gl2.GLUT;

import astro.Body;
import astro.SolarSystem;
import framework.Scene;
import utility.Kepler;
import utility.Point;

public class View extends Scene {
	// TODO
	private static final double MAX_RADIUS = 2;
	private static final double MIN_RADIUS = 1;
	
	private SolarSystem mySolarSystem;
	
	public View(SolarSystem solar) {
		mySolarSystem = solar;
	}
	
	@Override
	public void init(GL2 gl, GLU glu, GLUT glut) {
		// Calculate logarithmic radius scale parameters
		
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
			
			//double rotation = body.getRotation();
			//gl.glRotated(rotation, 0, 0, 1);

			// TODO implement radius (logarithmic?)
			double radius = Math.log(body.getRadius()) / 70;
			glut.glutWireSphere(radius, 8, 8);
		} gl.glPopMatrix();
	}

	@Override
	public void setCamera(GL2 gl, GLU glu, GLUT glut) {
		glu.gluLookAt(0, 15, 5,  // from
				  	  0, 0, 0,	 // to
				  	  0, 0, 1);	 // up
	}

}
