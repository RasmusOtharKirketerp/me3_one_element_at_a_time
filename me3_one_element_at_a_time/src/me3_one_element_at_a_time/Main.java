package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
	private static final long serialVersionUID = 1L;
	static LokalKoordinatsystem aScreen = new LokalKoordinatsystem(GetScreenWorkingWidth(), GetScreenWorkingHeight());
	static EclipseTime ec = new EclipseTime();
	static final int MAX_PLANETS = 9;
	static ArrayList<Planet> allePlaneterTilSolen = new ArrayList<Planet>();

	public static int GetScreenWorkingWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	public Main() {
	}

	public void drawGrid(Graphics2D g2d) {
		// g2d.drawLine(0, 0, aScreen.maxX, aScreen.maxY);
		// g2d.drawLine(aScreen.maxX, 0, 0, aScreen.maxY);

		g2d.drawLine(aScreen.relX(0), 0, aScreen.relX(0), aScreen.maxY);
		g2d.drawLine(0, aScreen.relY(0), aScreen.maxX, aScreen.relY(0));
		// g2d.drawLine(0, 0, aScreen.relX(0), aScreen.relY(0));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(new Color(70, 80, 70));

		for (Planet planet : allePlaneterTilSolen)
			planet.drawPlanet(g2d, ec);

		drawGrid(g2d);
	}

	static public void initUniverse() {
		// tabel indeholder planetes afstand fra solen (AU) = radius fra solen
		// jorden Uranus
		// Venus mars Saturn Neptun
		// Merkur Jupiter Pluto
		int[] kredsloebAll = { 39, 72, 100, 152, 520, 954, 1918, 3006, 3900 };
		int[] speedAll = { 47, 35, 29, 24, 13, 9, 6, 5, 4 };

		int[] sizeAll = { 4878, 12104, 12756, 6787, 142800, 120000, 51118, 49528, 970 };

		for (int i = 0; i < sizeAll.length; i++)
			sizeAll[i] = sizeAll[i] / 800;

		// Alloker plads i array of fyld data fra standard arrays ud...
		
		for (int nyPlanetCounter = 0; nyPlanetCounter < MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allePlaneterTilSolen.add(nyPlanet);
			allePlaneterTilSolen.get(nyPlanetCounter).centerX = aScreen.relX(0);
			allePlaneterTilSolen.get(nyPlanetCounter).centerY = aScreen.relY(0);
			allePlaneterTilSolen.get(nyPlanetCounter).planetensHastinghed = speedAll[nyPlanetCounter];			
			allePlaneterTilSolen.get(nyPlanetCounter).setRadiusPaaKredsloeb(kredsloebAll[nyPlanetCounter]);
			allePlaneterTilSolen.get(nyPlanetCounter).radius = sizeAll[nyPlanetCounter];
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstand = 1;
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = 0;			
		}

		// http://www.windows2universe.org/our_solar_system/planets_table.html

		int planetNr = 0;
		allePlaneterTilSolen.get(planetNr).name = "Merkur";
		allePlaneterTilSolen.get(planetNr).color = Color.MAGENTA;

		planetNr = 1;
		allePlaneterTilSolen.get(planetNr).name = "Venus";
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;

		planetNr = 2;
		allePlaneterTilSolen.get(planetNr).name = "Jorden";
		allePlaneterTilSolen.get(planetNr).color = Color.GREEN;

		planetNr = 3;
		allePlaneterTilSolen.get(planetNr).name = "Mars";
		allePlaneterTilSolen.get(planetNr).color = Color.RED;

		planetNr = 4;
		allePlaneterTilSolen.get(planetNr).name = "Jupiter";
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;

		planetNr = 5;
		allePlaneterTilSolen.get(planetNr).name = "Saturn";
		allePlaneterTilSolen.get(planetNr).color = Color.YELLOW;

		planetNr = 6;
		allePlaneterTilSolen.get(planetNr).name = "Uranus";
		allePlaneterTilSolen.get(planetNr).color = Color.LIGHT_GRAY;

		planetNr = 7;
		allePlaneterTilSolen.get(planetNr).name = "Neptun";
		allePlaneterTilSolen.get(planetNr).color = Color.WHITE;

		planetNr = 8;
		allePlaneterTilSolen.get(planetNr).name = "Pluto (dwarf)";
		allePlaneterTilSolen.get(planetNr).color = Color.gray;

		// moons for planet Earth
		Planet moon = new Planet();
		moon.name = "Moon";
		moon.centerX = allePlaneterTilSolen.get(2).centerX;
		moon.centerY = allePlaneterTilSolen.get(2).centerY;
		moon.planetensHastinghed = 4;
		moon.planetensTilbagelagteAfstand = 0;
		moon.planetensTilbagelagteAfstandFraStart = 0;
		moon.setRadiusPaaKredsloeb(sizeAll[2] + 10);
		moon.radius = 10;
		moon.color = Color.YELLOW;
		allePlaneterTilSolen.get(2).addMoon(moon);

		// moons for planet mars(1)
		Planet mars_moon1 = new Planet();
		mars_moon1.name = "";
		mars_moon1.centerX = allePlaneterTilSolen.get(3).centerX;
		mars_moon1.centerY = allePlaneterTilSolen.get(3).centerY;
		mars_moon1.planetensHastinghed = 4;
		mars_moon1.planetensTilbagelagteAfstand = 0;
		mars_moon1.planetensTilbagelagteAfstandFraStart = 0;
		mars_moon1.setRadiusPaaKredsloeb(sizeAll[2] + 10);
		mars_moon1.radius = 10;
		mars_moon1.color = Color.YELLOW;
		allePlaneterTilSolen.get(3).addMoon(mars_moon1);
		// moons for planet mars(2)
		Planet mars_moon2 = new Planet();
		mars_moon2.name = "";
		mars_moon2.centerX = allePlaneterTilSolen.get(3).centerX;
		mars_moon2.centerY = allePlaneterTilSolen.get(3).centerY;
		mars_moon2.planetensHastinghed = 2;
		mars_moon2.planetensTilbagelagteAfstand = 0;
		mars_moon2.planetensTilbagelagteAfstandFraStart = 15;
		mars_moon2.setRadiusPaaKredsloeb(sizeAll[2] + 10);
		mars_moon2.radius = 10;
		mars_moon2.color = Color.GRAY;
		allePlaneterTilSolen.get(3).addMoon(mars_moon2);

	}

	static public MouseAdapter ma = new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				zoom(1.1);
				for (Planet x : allePlaneterTilSolen)
					x.radius *= 1.1;
			}
			if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				zoom(-1.1);
				for (Planet x : allePlaneterTilSolen)
					x.radius /= 1.1;
			}
		}
	};

	public static void zoom(double d) {
		for (Planet x : allePlaneterTilSolen) {
			if (d > 0) {
				x.setRadiusPaaKredsloeb(x.getRadiusPaaKredsloeb() * d);
				x.planetensHastinghed = x.planetensHastinghed * d;
			} else {
				x.setRadiusPaaKredsloeb(x.getRadiusPaaKredsloeb() / d);
				x.planetensHastinghed = x.planetensHastinghed / d;
			}
		}
	}

	public static void speed(int speed) {
		for (Planet x : allePlaneterTilSolen)
			x.planetensHastinghed += speed;
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// theSun.tilfoejPlanetTilKredloeb(theEarth);
		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		initUniverse();

		frame.add(universe);
		frame.addMouseListener(ma);
		frame.setSize(aScreen.maxX, aScreen.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			universe.repaint();
			Thread.sleep(100);
			ec.click();
		}

	}

}
