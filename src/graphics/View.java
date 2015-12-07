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
	private static final double MAX_DISPLAY_RADIUS = 0.2;
	private static final double MIN_DISPLAY_RADIUS = 0.1;
	private double maxRadius;
	private double minRadius;
	
	private SolarSystem mySolarSystem;
	
	public View(SolarSystem solar) {
		mySolarSystem = solar;

		// Calculate logarithmic radius scale parameters
		maxRadius = solar.getMaxRadius();
		minRadius = solar.getMinRadius();
		System.out.println("====");
		System.out.println(maxRadius);
		System.out.println(minRadius);
		System.out.println(displayRadius(maxRadius));
		System.out.println(displayRadius(minRadius));
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
			glut.glutWireSphere(displayRadius(body.getRadius()), 8, 8);
		} gl.glPopMatrix();
	}
	
	private double displayRadius(double r) {
		return Math.log(r - minRadius + 1)
			 / Math.log(maxRadius - minRadius + 1)
			 * (MAX_DISPLAY_RADIUS - MIN_DISPLAY_RADIUS)
			 + MIN_DISPLAY_RADIUS;
	}

	@Override
	public void setCamera(GL2 gl, GLU glu, GLUT glut) {
		glu.gluLookAt(0, 15, 5,  // from
				  	  0, 0, 0,	 // to
				  	  0, 0, 1);	 // up
	}

}
