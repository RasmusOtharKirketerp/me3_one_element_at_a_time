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
	static int analyse = 1;
	static boolean use_real_values = true;
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
		this.setBackground(Color.black);
		for (Planet planet : allePlaneterTilSolen) {
			planet.drawPlanet(g2d, ec);
			if (planet.getPlanetIndex() > 0) {
				for (Planet drawToplanet : allePlaneterTilSolen) {
					if (analyse == 1) {
						if (planet.getPlanetIndex() > drawToplanet.getPlanetIndex() + 1)
							g.drawLine((int) planet.faktiskX, (int) planet.faktiskY, (int) drawToplanet.faktiskX,
									(int) drawToplanet.faktiskY);
					}
					if (analyse == 2) {
						if (planet.getPlanetIndex() == drawToplanet.getPlanetIndex() + 1)
							g.drawLine((int) planet.faktiskX, (int) planet.faktiskY, (int) drawToplanet.faktiskX,
									(int) drawToplanet.faktiskY);

					}
				}
			}

		}
		ec.draw(g2d, allePlaneterTilSolen);
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	static public void initUniverse() {

		// tabel indeholder planetes afstand fra solen (AU) = radius fra solen

		int[] kredsloebAll_real = { 39, 72, 100, 152, 520, 954, 1918, 3006, 3900 };
		int[] kredsloebAll_debug = { 50, 100, 150, 200, 250, 300, 400, 500, 600 };

		int[] speedAll_real = { 47, 35, 29, 24, 13, 9, 6, 5, 4 };
		int[] speedAll_debug = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };

		int[] sizeAll_real = { 4878, 12104, 12756, 6787, 142800, 120000, 51118, 49528, 970 };
		int[] sizeAll_debug = { 40, 40, 40, 40, 40, 40, 40, 40, 40 };

		int[] kredsloebAll, sizeAll, speedAll;

		if (use_real_values) {
			kredsloebAll = kredsloebAll_real;
			sizeAll = sizeAll_real;
			speedAll = speedAll_real;
		} else {
			kredsloebAll = kredsloebAll_debug;
			sizeAll = sizeAll_debug;
			speedAll = speedAll_debug;

		}

		for (int i = 0; i < sizeAll.length; i++)
			sizeAll[i] = sizeAll[i] / 1000;

		ec.reset();
		allePlaneterTilSolen.clear();
		// Alloker plads i array of fyld data fra standard arrays ud...

		for (int nyPlanetCounter = 0; nyPlanetCounter < MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allePlaneterTilSolen.add(nyPlanet);
			allePlaneterTilSolen.get(nyPlanetCounter).setPlanetIndex(nyPlanetCounter);
			allePlaneterTilSolen.get(nyPlanetCounter).centerX = aScreen.relX(0);
			allePlaneterTilSolen.get(nyPlanetCounter).centerY = aScreen.relY(0);
			allePlaneterTilSolen.get(nyPlanetCounter).planetensHastinghed = speedAll[nyPlanetCounter];
			allePlaneterTilSolen.get(nyPlanetCounter).setRadiusPaaKredsloeb(kredsloebAll[nyPlanetCounter]);
			allePlaneterTilSolen.get(nyPlanetCounter).setRadius(sizeAll[nyPlanetCounter]);
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstand = 1;
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = randInt(0,
					(int) allePlaneterTilSolen.get(nyPlanetCounter).getOmkredsPaaKredsloebet());
			// start alle planet i o afstand
			allePlaneterTilSolen.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = 0;
			allePlaneterTilSolen.get(nyPlanetCounter).setDrawRayToPlanet(false);

		}

		// http://www.windows2universe.org/our_solar_system/planets_table.html
		// http://www.fourmilab.ch/cgi-bin/Solar

		int planetNr = 0;
		allePlaneterTilSolen.get(planetNr).name = "Merkur";
		allePlaneterTilSolen.get(planetNr).color = Color.MAGENTA;

		planetNr = 1;
		allePlaneterTilSolen.get(planetNr).name = "Venus";
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;

		planetNr = 2;
		allePlaneterTilSolen.get(planetNr).name = "Jorden";
		allePlaneterTilSolen.get(planetNr).color = Color.GREEN;
		allePlaneterTilSolen.get(planetNr).moonGenerator(1);

		planetNr = 3;
		allePlaneterTilSolen.get(planetNr).name = "Mars";
		allePlaneterTilSolen.get(planetNr).color = Color.RED;
		allePlaneterTilSolen.get(planetNr).moonGenerator(2);

		planetNr = 4;
		allePlaneterTilSolen.get(planetNr).name = "Jupiter";
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;
		allePlaneterTilSolen.get(planetNr).moonGenerator(67);

		planetNr = 5;
		allePlaneterTilSolen.get(planetNr).name = "Saturn";
		allePlaneterTilSolen.get(planetNr).color = Color.YELLOW;
		allePlaneterTilSolen.get(planetNr).moonGenerator(53);

		planetNr = 6;
		allePlaneterTilSolen.get(planetNr).name = "Uranus";
		allePlaneterTilSolen.get(planetNr).color = Color.LIGHT_GRAY;
		allePlaneterTilSolen.get(planetNr).moonGenerator(27);

		planetNr = 7;
		allePlaneterTilSolen.get(planetNr).name = "Neptun";
		allePlaneterTilSolen.get(planetNr).color = Color.WHITE;
		allePlaneterTilSolen.get(planetNr).moonGenerator(13);

		planetNr = 8;
		allePlaneterTilSolen.get(planetNr).name = "Pluto (dwarf)";
		allePlaneterTilSolen.get(planetNr).color = Color.gray;

	}

	static public KeyListener myKL = new KeyListener() {
		// + = Speed +
		// - = Speed -
		// H�jre museklik = Zoom +
		// Venstre museklik = Zoom -
		// Space = pause
		// r = rayToPlanets
		boolean pause = false;

		@Override

		public void keyTyped(KeyEvent e) {

			System.out.println("Code : " + e.getKeyCode());
			System.out.println("Char : " + e.getKeyChar());
			if (e.getKeyChar() == 'r') {
				for (Planet x : allePlaneterTilSolen) {
					x.setDrawRayToPlanet(true);
					for (Planet moon : x.moons) {
						moon.setDrawRayToPlanet(true);
					}
				}
				;
			}
			if (e.getKeyChar() == 'R') {
				for (Planet x : allePlaneterTilSolen) {
					x.setDrawRayToPlanet(false);
					for (Planet moon : x.moons) {
						moon.setDrawRayToPlanet(false);
					}
				}
				;
			}
			if (e.getKeyChar() == '*') {
				initUniverse();
			}
			if (e.getKeyChar() == '+') {
				speed(2);
			}
			if (e.getKeyChar() == '-') {
				speed(0.5);
			}
			if (e.getKeyChar() == 'm') {
				for (Planet x : allePlaneterTilSolen) {
					x.setDrawMoons(false);
					for (Planet moon : x.moons) {
						moon.setDrawMoons(false);
					}
				}
			}
			if (e.getKeyChar() == 'M') {
				for (Planet x : allePlaneterTilSolen) {
					x.setDrawMoons(true);
					for (Planet moon : x.moons) {
						moon.setDrawMoons(true);
					}
				}
			}
			if (e.getKeyChar() == 'p') {
				if (!pause) {
					ec.setUnitPrClick(0);
					pause = true;
				} else {
					ec.setUnitPrClickToStandard();
					pause = false;
				}
			}
			if (e.getKeyChar() == 'a') {
				analyse += 1;
				if (analyse > 2)
					analyse = 0;
			}

			if (e.getKeyChar() == 'd') {
				if (use_real_values == true)
					use_real_values = false;
				else
					use_real_values = true;
				initUniverse();
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_UP:
				shiftUpDown(100);
				break;
			case KeyEvent.VK_DOWN:
				shiftUpDown(-100);
				break;
			case KeyEvent.VK_LEFT:
				shiftLeftRight(100);
				break;
			case KeyEvent.VK_RIGHT:
				shiftLeftRight(-100);
				break;
			}

		}

		private void shiftLeftRight(int i) {
			// TODO Auto-generated method stub
			for (Planet x : allePlaneterTilSolen) {
				x.centerX += i;
			}

		}

		private void shiftUpDown(int i) {
			// TODO Auto-generated method stub
			for (Planet x : allePlaneterTilSolen) {
				x.centerY += i;
			}

		}
	};

	static public MouseAdapter ma = new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				zoom(1.05);
			}
			if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				zoom(0.95);
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
				m.planetensHastinghed = m.planetensHastinghed * d;
			}

		}
	}

	public static void speed(double d) {
		for (Planet x : allePlaneterTilSolen) {
			x.planetensHastinghed *= d;
			if (x.planetensHastinghed <= 0)
				x.planetensHastinghed = 1;
			for (Planet m : x.moons) {
				m.planetensHastinghed = m.planetensHastinghed * d;
				if (m.planetensHastinghed <= 0)
					m.planetensHastinghed = 1;

			}

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
		frame.setSize(aScreen.maxX - 200, aScreen.maxY - 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
			universe.repaint();
			Thread.sleep(5);
			ec.click();
		}

	}

}
