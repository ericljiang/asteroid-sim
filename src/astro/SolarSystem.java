package astro;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem {
	private Sun mySun;
	private List<Satellite> myPlanets;
	private double time;
	
	public SolarSystem(Sun sun, List<Satellite> planets) {
		mySun = sun;
		myPlanets = planets;
	}
	
	public SolarSystem(Sun sun) {
		this(sun, new ArrayList<Satellite>());
	}
	
	public SolarSystem() {
		this(new Sun(1.989e30, 6.96342e5, 25.05));
	}
	
	public void update() {
		time += 10;
		mySun.update(time);
		for (Satellite planet : myPlanets) {
			planet.update(time);
		}
		//System.out.println("Time: " + time);
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
	
	public double getMaxRadius() {
		double r = mySun.getRadius();
		for (Satellite planet : myPlanets) {
			if (planet.getRadius() > r) {
				r = planet.getRadius();
			}
		}
		return r;
	}
	
	public double getMinRadius() {
		double r = mySun.getRadius();
		for (Satellite planet : myPlanets) {
			if (planet.getRadius() < r) {
				r = planet.getRadius();
			}
		}
		return r;
	}
}
