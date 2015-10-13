package astro;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
	private Sun mySun;
	private List<Satellite> myPlanets;
	private double time;
	
	public SolarSystem() {
		mySun = new Sun(1.989e30, 6.96342e5, 25.05);
		myPlanets = new ArrayList<Satellite>();
	}
	
	public void update() {
		time += 0.1;
		mySun.update(time);
		for (Satellite planet : myPlanets) {
			planet.update(time);
		}
		//System.out.println("Model updated");
	}

	public void addPlanet(Satellite planet) {
		myPlanets.add(planet);
	}
	
	public Sun getSun() {
		return mySun;
	}

	public List<Satellite> getPlanets() {
		return myPlanets;
	}
}
