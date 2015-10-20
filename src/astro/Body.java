package astro;

import utility.Point;

public abstract class Body {
	private double mass;			// kg
	private double radius;			// km
	private double rotationPeriod;	// days
	private Point myPosition;		// AU
	private double myRotation;		// degrees
	
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
		myRotation = 360 * ((time / rotationPeriod) % 1);
	}
	
	public Point getPosition() {
		return myPosition;
	}
	
	protected void setPosition(Point position) {
		myPosition = position;
	}
	
	public double getRotation() {
		return myRotation;
	}
	
	public double getRadius() {
		return radius;
	}
}
