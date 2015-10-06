package astro;

import math.Point;

public abstract class Body {
	private double mass;			// kg
	private double radius;			// km
	private double rotationPeriod;	// days
	protected Point myPosition;		
	private double myRotation;		// radians
	
	protected Body(double mass, double radius, double rotationPeriod) {
		this.mass = mass;
		this.radius = radius;
		this.rotationPeriod = rotationPeriod;
	}
	
	public void update(double time) {
		updatePosition(time);
		updateRotation(time);
	}
	
	abstract void updatePosition(double time);
	
	private void updateRotation(double time) {
		myRotation += 2 * Math.PI * time / rotationPeriod;
	}
	
}
