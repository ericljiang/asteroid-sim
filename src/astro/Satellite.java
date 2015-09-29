package astro;

public class Satellite extends Body {
	private Body myPrimary;
	private double a; // Semi-major axis
	private double b; // Semi-minor axis

	protected Satellite(Body primary, double mass, double radius, double rotationPeriod) {
		super(mass, radius, rotationPeriod);
		myPrimary = primary;
	}
}
