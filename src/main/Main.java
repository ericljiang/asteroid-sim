package main;

import java.io.FileNotFoundException;

import astro.SolarSystem;
import framework.JOGLFrame;
import graphics.View;
import utility.FileIO;

public class Main {
	public static void main(String[] args) {
		try {
			SolarSystem solar = FileIO.readCSV("configs/solarsystem.csv");
			View view = new View(solar);
			new JOGLFrame(view);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
