package main;

import java.util.ArrayList;

import body.Satellite;
import body.Sun;

public class SolarSystem {
	public Sun mySun;
	public ArrayList<Satellite> myPlanets;
	public int time;
	
	public SolarSystem() {
		mySun = new Sun();
		myPlanets = new ArrayList<Satellite>();
	}
	
	public void update() {
		time++;
		mySun.update(time);
	}

}
