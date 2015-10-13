package main;

import astro.Satellite;
import astro.SolarSystem;
import framework.JOGLFrame;
import graphics.View;

public class Main {
	public static void main(String[] args) {
		SolarSystem solar = new SolarSystem();
		Satellite earth = new Satellite(5.97237e24, 6371.0, 0.99726968,
										149598261, 0.0167, 
										Math.toRadians(-11.26064), 
										Math.toRadians(102.947), 
										Math.toRadians(7.155), solar.getSun());
		solar.addPlanet(earth);
		View view = new View(solar);
		new JOGLFrame(view);
	}
}
