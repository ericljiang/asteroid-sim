package main;

public class Main {
	public SolarSystem mySolarSystem;
	
	public Main() {
		mySolarSystem = new SolarSystem();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.mySolarSystem = new SolarSystem();
		
	}
}
