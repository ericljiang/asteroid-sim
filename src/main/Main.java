package main;

import astro.SolarSystem;
import framework.JOGLFrame;
import graphics.View;

public class Main {
	public static void main(String[] args) {
		SolarSystem solar = new SolarSystem();
		View view = new View(solar);
		new JOGLFrame(view);
	}
}
