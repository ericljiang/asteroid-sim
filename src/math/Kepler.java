package math;

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
	 * @return	Returns M, the mean anomaly in radians. The mean anomaly is the
	 * 			angle between the perihelion and the mean planet.
	 */
	public static double meanAnomaly(double t, double P) {
		return (2 * Math.PI * t / P) % (2 * Math.PI);
	}
	
	/**
	 * @param e			Eccentricity of orbit
	 * @param M			Mean anomaly
	 * @param initial	Initial value for Newton method
	 * @param accuracy	Desired accuracy for Newton method
	 * @return			Returns E, the eccentric anomaly in radians.
	 */
	public static double eccenticAnomaly(double e, double M, double accuracy) {
		/* https://en.wikipedia.org/wiki/Kepler%27s_equation#Numerical_approximation_of_inverse_problem
		 * For most elliptical orbits an initial value of E0 = M(t) is
		 * sufficient. For orbits with e > 0.8, an initial value of E0 = pi
		 * should be used.
		 */
		double initial = (e < 0.8) ? M : Math.PI;
		return numApproxE(e, M, initial, accuracy);
	}
	
	private static double numApproxE(double e, double M, double E,
									 double accuracy) {
		double numerator = E - e * Math.sin(E) - M;
		if (Math.abs(numerator) < accuracy) {
			return E;
		} else {
			double nextE = E - numerator / (1.0 - e * Math.cos(E));
			return numApproxE(e, M, nextE, accuracy);
		}
	}
	
	/**
	 * @param e	Eccentricity of orbit
	 * @param E	Eccentric anomaly in radians
	 * @return	Returns v, the true anomaly in radians. The true anomaly is the
	 * 			angle of the planet from the perihelion.
	 */
	public static double trueAnomaly(double e, double E) {
		return 2 * Math.atan(Math.sqrt((1 + e) / (1 - e)) * Math.tan(E / 2));
	}
	
	/**
	 * @param a	Semi-major axis in kilometers
	 * @param e	Eccentricity of orbit
	 * @param v	True anomaly in radians
	 * @return	Returns r, the planet's radial distance from the sun in kilometers.
	 */
	public static double radialDistance(double a, double e, double v) {
		double axis = toAU(a);
		double r = axis * (1 - Math.pow(e, 2)) / (1 + e * Math.cos(v));
		return toKm(r);
	}
	
	/**
	 * Creates heliocentric ecliptic coordinates from polar coordinates.
	 * @param r	Radial distance
	 * @param v	True anomaly in radians
	 * @param o	Longitude of ascending node in radians
	 * @param w	Longitude of perihelion in radians
	 * @param i Orbital inclination
	 * @return	Returns the heliocentric eliptic coordinates for the planet.
	 * 			Heliocentric eliptic coordinates are coordinates in a Cartesian
	 * 			coordinate system centered on the Sun in which the eliptic lies
	 * 			in the XZ-plane.
	 */
	public static Point polarToEcliptic(double r, double v, double o,
										double w, double i) {
		double coso = Math.cos(o);
		double sino = Math.sin(o);
		double coswv = Math.cos(w + v);
		double sinwv = Math.sin(w + v);
		double cosi = Math.cos(i);
		double sini = Math.sin(i);
		return new Point(r * (coso * coswv - sino * sinwv * cosi),
						 r * (sino * coswv + coso * sinwv * cosi),
						 r * sinwv * sini);
	}
	
	/**
	 * Creates heliocentric equatorial coordinates from heliocentric eliptic
	 * coordinates.
	 * @param p	The heliocentric eliptic coordinates to be transformed.
	 * @param i	Orbital inclination
	 * @return	Returns the heliocentric equatorial coordinates for the planet.
	 * 			Heliocentric equatorial coordinates are coordinates in a
	 * 			Cartesian coordinate system centered on the Sun in which the
	 * 			Sun's equator lies in the XZ-plane.
	 */
	public static Point eclipticToEquatorial(Point p, double i) {
		double cosi = Math.cos(i);
		double sini = Math.sin(i);
		return new Point(p.getX(),
						 p.getY() * cosi - p.getZ() * sini,
						 p.getY() * sini + p.getZ() * cosi);
	}
}
