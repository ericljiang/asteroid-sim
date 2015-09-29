package main;

import java.util.ArrayList;

import body.Planet;
import body.Sun;

public class SolarSystem {
	public Sun mySun;
	public ArrayList<Planet> myPlanets;
	public int time;
	
	public SolarSystem() {
		mySun = new Sun();
		myPlanets = new ArrayList<Planet>();
	}
	
	public void update() {
		time++;
		mySun.update(time);
	}

}
