package astro;

public class Sun extends Body {

	public Sun(double mass, double radius, double rotationPeriod) {
		super(mass, radius, rotationPeriod);
	}

	@Override
	void updatePosition(double time) {
		// position of sun alwasy (0, 0, 0)
	}

}
