package astro;

import math.Point;

public abstract class Body {
	private double mass;			// kg
	private double radius;			// km
	private double rotationPeriod;	// days
	
	private Point myPosition;
	private double myRotation;
	
	protected Body(double mass, double radius, double rotationPeriod) {
		this.mass = mass;
		this.radius = radius;
		this.rotationPeriod = rotationPeriod;
	}
	
	public void update(int time) {
		// Update position
		// myPosition = ;
		// Update rotation
		// myRotation = ;
	}
	
	public Point getPosition() {
		return myPosition;		
	}
	
	public double getRotation() {
		return myRotation;
	}
	
	public double getMass() {
		return mass;
	}
}
