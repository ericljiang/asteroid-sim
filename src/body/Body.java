package body;

import math.Point;

public abstract class Body {
	private double mass;
	private double radius;
	private double a; // Semi-major axis
	private double b; // Semi-minor axis
	private double rotPeriod;
	
	private Point myPosition;
	private double myRotation;
	
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
}
