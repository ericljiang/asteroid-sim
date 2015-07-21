package main;

public abstract class Body {
	public float mass;
	public float radius;
	public float a; // Semi-major axis
	public float b; // Semi-minor axis
	public float rotPeriod;
	public float rotation; // could be replaced with getRotation() calculation
	
	public void update(int time) {
		// Update position
		// Update rotation
		System.out.println("update body");
	}
}
