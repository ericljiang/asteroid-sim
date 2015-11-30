package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import astro.Satellite;
import astro.SolarSystem;
import astro.Sun;

public class FileIO {
	public static SolarSystem readCSV(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner input = new Scanner(file);
		String data = input.nextLine(); // headers		
		
		SolarSystem solar = new SolarSystem();
		Sun sun = parseSun(input.nextLine());
		while(input.hasNextLine()) {
			data = input.nextLine();
			System.out.println(data);
			solar.addPlanet(parsePlanet(data, sun));
		}
		input.close();
		
		return solar;
	}
	
	private static Sun parseSun(String data) {
		String[] strings = data.replaceAll("\\s", "").split(",");
		Double[] params = parseParameters(strings);
		return new Sun(params[0], params[1], params[0]);
	}
	
	private static Satellite parsePlanet(String data, Sun sun) {
		String[] strings = data.replaceAll("\\s", "").split(",");
		Double[] params = parseParameters(strings);
		return new Satellite(params[0], params[1], params[2], params[3],
							 params[4], params[5], params[6], params[7], sun);
	}
	
	private static Double[] parseParameters(String[] strings) {
		Double[] doubles = new Double[strings.length - 1];
		for (int i = 0; i < strings.length - 1; i++) {
			doubles[i] = Double.parseDouble(strings[i + 1]);
			System.out.println(doubles[i]);
		}
		return doubles;
	}
}
