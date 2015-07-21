package main;

public class Simulation {
	public SolarSystem mySolarSystem;
	
	public Simulation() {
		mySolarSystem = new SolarSystem();
	}
	
	public void update() {
		mySolarSystem.update();
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.mySolarSystem = new SolarSystem();
		for (int i = 0; i < 10; i++) {
			simulation.update();
		}
	}
}
