package astro;

import math.Point;

public class Sun extends Body {

	public Sun(double mass, double radius, double rotationPeriod) {
		super(mass, radius, rotationPeriod);
		super.setPosition(new Point(0, 0, 0));
	}

	@Override
	void updatePosition(double time) {
		// position of sun alwasy (0, 0, 0)
	}

}
