package graphics;

import astro.Body;
import astro.SolarSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FXView extends Pane {
	private SolarSystem mySolarSystem;
	private Timeline myTimeline;
	double maxRadius;
	double minRadius;

	private static final double MAX_DISPLAY_RADIUS = 15;
	private static final double MIN_DISPLAY_RADIUS = 3;
	
	public FXView() {
		myTimeline = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(1000/60.0), e -> update());
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
	}

	public void setSystem(SolarSystem system) {
		mySolarSystem = system;
		maxRadius = system.getMaxRadius();
		minRadius = system.getMinRadius();
	}
	
	public void start() {
		myTimeline.play();
	}
	
	public void update() {
		mySolarSystem.update();
		clear();
		render();
	}
	
	private void render() {
		drawBody(mySolarSystem.getSun());
		
		for (Body b : mySolarSystem.getPlanets()) {
			drawBody(b);
		}
	}
	
	private void drawBody(Body body) {
		Circle circle = new Circle();
		circle.setRadius(displayRadius(body.getRadius()));
		circle.setCenterX(body.getPosition().getX() * 1E-6);
		circle.setCenterY(body.getPosition().getY() * 1E-6);
		circle.setFill(Color.RED);
		this.getChildren().add(circle);
	}

	private void clear() {
		this.getChildren().clear();
	}
	
	private double displayRadius(double r) {
		return Math.log(r - minRadius + 1)
			 / Math.log(maxRadius - minRadius + 1)
			 * (MAX_DISPLAY_RADIUS - MIN_DISPLAY_RADIUS)
			 + MIN_DISPLAY_RADIUS;
	}

}
