package utility;

public class Kepler {
	// TODO switch to degrees
	
	public static final double KM_PER_AU = 149597871;
	public static final double DAYS_PER_YEAR = 365.26;
	
	public static double toAU(double km) {
		return km / KM_PER_AU;
	}
	
	public static double toKm(double au) {
		return au * KM_PER_AU;
	}
	
	public static double toYears(double days) {
		return days / DAYS_PER_YEAR;
	}
	
	public static double toDays(double years) {
		return years * DAYS_PER_YEAR;
	}
	
	/**
	 * @param a	Semi-major axis in kilometers
	 * @return	Returns P, the period of orbit in days.
	 */
	public static double period(double a) {
		double axis = toAU(a);
		double period = Math.pow(axis, 1.5);
		return toDays(period);
	}
	
	/**
	 * @param t	Time since last perihelion in days
	 * @param P	Period of orbit in days
	 * @return	Returns M, the mean anomaly in degrees. The mean anomaly is the
	 * 			angle between the perihelion and the mean planet.
	 */
	public static double meanAnomaly(double t, double P) {
		return (360 * t / P) % (360);
	}
	
	/**
	 * @param e			Eccentricity of orbit
	 * @param M			Mean anomaly in degrees
	 * @param initial	Initial value for Newton method
	 * @param accuracy	Desired accuracy for Newton method
	 * @return			Returns E, the eccentric anomaly in degrees.
	 */
	public static double eccenticAnomaly(double e, double M, double accuracy) {
		/* https://en.wikipedia.org/wiki/Kepler%27s_equation#Numerical_approximation_of_inverse_problem
		 * For most elliptical orbits an initial value of E0 = M(t) is
		 * sufficient. For orbits with e > 0.8, an initial value of E0 = pi
		 * should be used.
		 */
		double initial = (e < 0.8) ? M : 180;
		return numApproxE(e, M, initial, accuracy);
	}
	
	private static double numApproxE(double e, double M, double E,
									 double accuracy) {
		double numerator = E - e * sinDeg(E) - M;
		if (Math.abs(numerator) < accuracy) {
			return E;
		} else {
			double nextE = E - numerator / (1.0 - e * cosDeg(E));
			return numApproxE(e, M, nextE, accuracy);
		}
	}
	
	/**
	 * @param e	Eccentricity of orbit
	 * @param E	Eccentric anomaly in degrees
	 * @return	Returns v, the true anomaly in degrees. The true anomaly is the
	 * 			angle of the planet from the perihelion.
	 */
	public static double trueAnomaly(double e, double E) {
		return 2 * atanDeg(Math.sqrt((1 + e) / (1 - e)) * tanDeg(E / 2));
	}
	
	/**
	 * @param a	Semi-major axis in kilometers
	 * @param e	Eccentricity of orbit
	 * @param v	True anomaly in degrees
	 * @return	Returns r, the planet's radial distance from the sun in
	 * 			kilometers.
	 */
	public static double radialDistance(double a, double e, double v) {
		double axis = toAU(a);
		double r = axis * (1 - Math.pow(e, 2)) / (1 + e * cosDeg(v));
		return toKm(r);
	}
	
	/**
	 * Creates heliocentric ecliptic coordinates from polar coordinates.
	 * @param r	Radial distance
	 * @param v	True anomaly in degrees
	 * @param o	Longitude of ascending node in degrees
	 * @param w	Longitude of perihelion in degrees
	 * @param i Orbital inclination in degrees
	 * @return	Returns the heliocentric eliptic coordinates for the planet.
	 * 			Heliocentric eliptic coordinates are coordinates in a Cartesian
	 * 			coordinate system centered on the Sun in which the eliptic lies
	 * 			in the XZ-plane.
	 */
	public static Point polarToEcliptic(double r, double v, double o,
										double w, double i) {
		double coso  = cosDeg(o);
		double sino  = sinDeg(o);
		double coswv = cosDeg(w + v);
		double sinwv = sinDeg(w + v);
		double cosi  = cosDeg(i);
		double sini  = sinDeg(i);
		return new Point(r * (coso * coswv - sino * sinwv * cosi),
						 r * (sino * coswv + coso * sinwv * cosi),
						 r * sinwv * sini);
	}
	
	/**
	 * Creates heliocentric equatorial coordinates from heliocentric eliptic
	 * coordinates.
	 * @param p	The heliocentric eliptic coordinates to be transformed.
	 * @param i	Orbital inclination in degrees
	 * @return	Returns the heliocentric equatorial coordinates for the planet.
	 * 			Heliocentric equatorial coordinates are coordinates in a
	 * 			Cartesian coordinate system centered on the Sun in which the
	 * 			Sun's equator lies in the XZ-plane.
	 */
	public static Point eclipticToEquatorial(Point p, double i) {
		double cosi = cosDeg(i);
		double sini = sinDeg(i);
		return new Point(p.getX(),
						 p.getY() * cosi - p.getZ() * sini,
						 p.getY() * sini + p.getZ() * cosi);
	}
	
	private static double cosDeg(double angle) {
		return Math.cos(Math.toRadians(angle));
	}
	
	private static double sinDeg(double angle) {
		return Math.sin(Math.toRadians(angle));
	}
	
	private static double tanDeg(double angle) {
		return Math.tan(Math.toRadians(angle));
	}
	
	private static double atanDeg(double value) {
		return Math.toDegrees(Math.atan(value));
	}
}
