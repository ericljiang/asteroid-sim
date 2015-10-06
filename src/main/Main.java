package main;

import math.Kepler;
import math.Point;

public class Main {
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		for (int i = 0; i < 10; i++) {
			simulation.update();
			System.out.println("update");
		}
	}
}
