package astro;

import math.Kepler;
import math.Point;

public class Satellite extends Body {
	// TODO switch to degrees
	private Body myPrimary;
	private double a; // Semi-major axis
	private double e; // Eccentricity
	private double o; // Longitude of ascending node
	private double w; // Longitude of perihelion
	private double i; // Orbital inclination

	/**
	 * @param mass Mass in kg
	 * @param radius Radius in km
	 * @param rotationPeriod Sidereal rotation period in days
	 * @param a Semi-major axis in km
	 * @param e Eccentricity
	 * @param o Longitude of ascending node in radians
	 * @param w Longitude of perihelion in radians
	 * @param i Orbital inclination in radians
	 */
	public Satellite(double mass, double radius, double rotationPeriod,
					 double a, double e, double o, double w, double i) {
		super(mass, radius, rotationPeriod);
		this.a = a;
		this.e = e;
		this.o = o;
		this.w = w;
		this.i = i;
	}
	
	/**
	 * @param mass Mass in kg
	 * @param radius Radius in km
	 * @param rotationPeriod Sidereal rotation period in days
	 * @param a Semi-major axis in km
	 * @param e Eccentricity
	 * @param o Longitude of ascending node in radians
	 * @param w Longitude of perihelion in radians
	 * @param i Orbital inclination in radians
	 * @param primary Parent body
	 */
	public Satellite(double mass, double radius, double rotationPeriod,
					 double a, double e, double o, double w, double i,
					 Body primary) {
		this(mass, radius, rotationPeriod, a, e, o, w, i);
		myPrimary = primary;
	}
	
	public void updatePosition(double t) {
		double P = Kepler.period(a);
		double M = Kepler.meanAnomaly(t, P);
		double E = Kepler.eccenticAnomaly(e, M, .00001);
		double v = Kepler.trueAnomaly(e, E);
		double r = Kepler.radialDistance(a, e, v);
		
		Point ecliptic = Kepler.polarToEcliptic(r, v, o, w, i);
		Point equatorial = Kepler.eclipticToEquatorial(ecliptic, i);
		
		super.setPosition(Point.add(equatorial, myPrimary.getPosition()));
	}
}
