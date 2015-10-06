package main;

import astro.Satellite;
import astro.SolarSystem;

public class Simulation {
	private SolarSystem mySolarSystem;
	
	public Simulation() {
		mySolarSystem = new SolarSystem();
		Satellite earth = new Satellite(5.97237e24,
										6371.0,
										0.99726968,
										1,
										0.01671123,
										Math.toRadians(-11.26064),
										Math.toRadians(102.94719),
										Math.toRadians(7.155));
		mySolarSystem.addPlanet(earth);
	}
	
	public void update() {
		mySolarSystem.update();
	}
}
