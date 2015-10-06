package main;

import framework.JOGLFrame;
import graphics.View;

public class Main {
	public static void main(String[] args) {
		new JOGLFrame(new View());
		/*Simulation simulation = new Simulation();
		for (int i = 0; i < 10; i++) {
			simulation.update();
			System.out.println("update");
		}*/
	}
}
