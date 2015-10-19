package math;

public class Point {
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public static Point add(Point a, Point b) {
		return new Point (a.getX() + b.getX(),
						  a.getY() + b.getY(),
						  a.getZ() + b.getZ());
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f, %f)", x, y , z);
	}
}
