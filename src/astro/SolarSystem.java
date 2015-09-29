package astro;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
	private Sun mySun;
	private List<Satellite> myPlanets;
	private int time;
	
	public SolarSystem() {
		mySun = new Sun(1.989e30, 6.96342e5, 25.05);
		myPlanets = new ArrayList<Satellite>();
	}
	
	public void update() {
		time++;
		mySun.update(time);
	}

}
