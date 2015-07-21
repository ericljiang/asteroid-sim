package main;

public class Simulation {
	public SolarSystem mySolarSystem;
	
	public Simulation() {
		mySolarSystem = new SolarSystem();
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.mySolarSystem = new SolarSystem();
		
	}
}
