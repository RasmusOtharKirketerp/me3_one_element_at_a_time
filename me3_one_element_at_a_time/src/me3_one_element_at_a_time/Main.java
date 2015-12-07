package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

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
		g2d.setColor(Color.yellow);
		// g2d.drawLine(0, 0, aScreen.relX(0), aScreen.relY(0));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.setBackground(new Color(70, 80, 70));

		for (Planet planet : allePlaneterTilSolen)
			planet.drawPlanet(g2d, ec);

		// drawGrid(g2d);
	}

	public static int randInt(int min, int max) {

		// NOTE: This will (intentionally) not run as written so that folks
		// copy-pasting have to think about how to initialize their
		// Random instance. Initialization of the Random instance is outside
		// the main scope of the question, but some decent options are to have
		// a field that is initialized once and then re-used as needed or to
		// use ThreadLocalRandom (if using at least Java 1.7).
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
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

		allePlaneterTilSolen.clear();
		// Alloker plads i array of fyld data fra standard arrays ud...

		for (int nyPlanetCounter = 0; nyPlanetCounter < MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allePlaneterTilSolen.add(nyPlanet);
			allePlaneterTilSolen.get(nyPlanetCounter).centerX = aScreen.relX(0);
			allePlaneterTilSolen.get(nyPlanetCounter).centerY = aScreen.relY(0);
			allePlaneterTilSolen.get(nyPlanetCounter).planetensHastinghed = speedAll[nyPlanetCounter];
			allePlaneterTilSolen.get(nyPlanetCounter).setRadiusPaaKredsloeb(kredsloebAll[nyPlanetCounter]);
			allePlaneterTilSolen.get(nyPlanetCounter).setRadius(sizeAll[nyPlanetCounter]);
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstand = 1;
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = randInt(0,
					(int) allePlaneterTilSolen.get(nyPlanetCounter).getOmkredsPaaKredsloebet());
			allePlaneterTilSolen.get(nyPlanetCounter).setDrawRayToPlanet(false);
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
		moonGenerator(allePlaneterTilSolen.get(planetNr), 1);

		planetNr = 3;
		allePlaneterTilSolen.get(planetNr).name = "Mars";
		allePlaneterTilSolen.get(planetNr).color = Color.RED;
		moonGenerator(allePlaneterTilSolen.get(planetNr), 2);


		planetNr = 4;
		allePlaneterTilSolen.get(planetNr).name = "Jupiter";
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;
		moonGenerator(allePlaneterTilSolen.get(planetNr), 67);

		planetNr = 5;
		allePlaneterTilSolen.get(planetNr).name = "Saturn";
		allePlaneterTilSolen.get(planetNr).color = Color.YELLOW;
		moonGenerator(allePlaneterTilSolen.get(planetNr), 53);


		planetNr = 6;
		allePlaneterTilSolen.get(planetNr).name = "Uranus";
		allePlaneterTilSolen.get(planetNr).color = Color.LIGHT_GRAY;
		moonGenerator(allePlaneterTilSolen.get(planetNr), 27);


		planetNr = 7;
		allePlaneterTilSolen.get(planetNr).name = "Neptun";
		allePlaneterTilSolen.get(planetNr).color = Color.WHITE;
		moonGenerator(allePlaneterTilSolen.get(planetNr), 13);


		planetNr = 8;
		allePlaneterTilSolen.get(planetNr).name = "Pluto (dwarf)";
		allePlaneterTilSolen.get(planetNr).color = Color.gray;


	}

	static public void moonGenerator(Planet x, int GenMoon) {
		for (int i = 0; i < GenMoon; i++) {
			Planet moon = new Planet();
			moon.name = "Moon" + i;
			moon.centerX = x.centerX;
			moon.centerY = x.centerY;
			moon.planetensHastinghed = x.planetensHastinghed * 10;
			moon.planetensTilbagelagteAfstand = 0;
			moon.planetensTilbagelagteAfstandFraStart = randInt(0,(int) x.getOmkredsPaaKredsloebet());
			moon.setRadiusPaaKredsloeb(x.getRadius() + x.getRadius() / 10);
			moon.setRadius(5);
			moon.color = x.color;
			moon.setDrawRayToPlanet(false);
			moon.setDrawName(false);
			moon.setDrawOrbit(false);
			x.addMoon(moon);
          
		}
	}

	static public KeyListener myKL = new KeyListener() {
		// + = Speed +
		// - = Speed -
		// Højre museklik = Zoom +
		// Venstre museklik = Zoom -
		// Space = pause
		boolean pause = false;

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '*') {
				initUniverse();
			}
			if (e.getKeyChar() == '+') {
				speed(1.5);
			}
			if (e.getKeyChar() == '-') {
				speed(0.5);
			}
			if (e.getKeyChar() == 'p') {
				System.out.println("p key");
				if (!pause) {
					ec.setUnitPrClick(0);
					pause = true;
				} else {
					ec.setUnitPrClickToStandard();
					pause = false;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	};

	static public MouseAdapter ma = new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				zoom(1.1);
			}
			if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				zoom(0.9);
			}
		}
	};

	public static void zoom(double d) {
		for (Planet x : allePlaneterTilSolen) {
			x.setRadiusPaaKredsloeb(x.getRadiusPaaKredsloeb() * d);
			x.setRadius((int) (x.getRadius() * d));
			x.planetensHastinghed = x.planetensHastinghed * d;
			for (Planet m : x.moons) {
				m.setRadiusPaaKredsloeb(m.getRadiusPaaKredsloeb() * d);
				m.setRadius((int) (m.getRadius() * d));
			}

		}
	}

	public static void speed(double d) {
		for (Planet x : allePlaneterTilSolen) {
			x.planetensHastinghed *= d;
			if (x.planetensHastinghed <= 0)
				x.planetensHastinghed = 1;

		}

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// theSun.tilfoejPlanetTilKredloeb(theEarth);
		JFrame frame = new JFrame("MultiEclipse");
		Main universe = new Main();
		initUniverse();

		frame.add(universe);
		frame.addMouseListener(ma);
		frame.addKeyListener(myKL);
		frame.setSize(aScreen.maxX, aScreen.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			universe.repaint();
			Thread.sleep(10);
			ec.click();
		}

	}

}
