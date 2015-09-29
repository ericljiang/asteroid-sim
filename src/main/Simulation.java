package main;

public class Simulation {
	private SolarSystem mySolarSystem;
	
	public Simulation() {
		mySolarSystem = new SolarSystem();
	}
	
	public void update() {
		mySolarSystem.update();
	}
}
