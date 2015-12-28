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

		for (int planetCounter = 0; planetCounter < allePlaneterTilSolen.size(); planetCounter++)
			allePlaneterTilSolen.get(planetCounter).drawPlanet(g2d, ec);

		drawGrid(g2d);
	}

	static public void initUniverse() {
		// Alloker plads i array
		for (int nyPlanetCounter = 0; nyPlanetCounter < 9; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allePlaneterTilSolen.add(nyPlanet);
		}
		// tabel indeholder planetes afstand fra solen (AU) = radius fra solen
		//                             jorden              Uranus
		//                        Venus    mars       Saturn     Neptun
		//                  Merkur              Jupiter                Pluto
		int[] kredsloebAll = { 39, 72, 100, 152, 520, 954, 1918, 3006, 3900 };
		int[] speedAll     = { 47, 35,  29,  24,  13,   9,    6,    5,    4 };
        
		int[] sizeAll      = { 4878,	12104,	12756,	6787,	142800,	120000,	51118,	49528, 970};
		
		for (int i = 0; i < sizeAll.length; i++) 
			sizeAll[i] = sizeAll[i] / 800;
		/*
		 * Mercury Venus Earth Mars Jupiter Saturn Uranus Neptune Pluto diameter
		 * (Earth=1) 0.382 0.949 1 0.532 11.209 9.44 4.007 3.883 diameter (km)
		 * 4,878 12,104 12,756 6,787 142,800 120,000 51,118 49,528 mean distance
		 * from Sun (AU) 0.39 0.72 1 1.52 5.20 9.54 19.18 30.06 39 orbital
		 * period (Earth years) 0.24 0.62 1 1.88 11.86 29.46 84.01 164.8 mean
		 * orbital velocity (km/sec) 47.89 35.03 29.79 24.13 13.06 9.64 6.81
		 * 5.43 4.7 rotation period (in Earth days) 58.65 -243* 1 1.03 0.41 0.44
		 * -0.72* 0.72 number of moons 0 0 1 2 63 62 27 13 rings? no no no no
		 * yes yes yes yes
		 */

		// http://www.windows2universe.org/our_solar_system/planets_table.html

		int planetNr = 0;
		allePlaneterTilSolen.get(planetNr).name = "Merkur";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 1;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 100;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.MAGENTA;

		planetNr = 1;
		allePlaneterTilSolen.get(planetNr).name = "Venus";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 1;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 100;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;

		planetNr = 2;
		allePlaneterTilSolen.get(planetNr).name = "Jorden";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 1;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 100;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		;
		allePlaneterTilSolen.get(planetNr).color = Color.GREEN;

		planetNr = 3;
		allePlaneterTilSolen.get(planetNr).name = "Mars";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 121;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		;
		allePlaneterTilSolen.get(planetNr).color = Color.RED;

		planetNr = 4;
		allePlaneterTilSolen.get(planetNr).name = "Jupiter";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 0;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.CYAN;

		planetNr = 5;
		allePlaneterTilSolen.get(planetNr).name = "Saturn";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 0;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.YELLOW;

		planetNr = 6;
		allePlaneterTilSolen.get(planetNr).name = "Uranus";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 0;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.LIGHT_GRAY;

		planetNr = 7;
		allePlaneterTilSolen.get(planetNr).name = "Neptun";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 0;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.WHITE;

		planetNr = 8;
		allePlaneterTilSolen.get(planetNr).name = "Pluto (dwarf)";
		allePlaneterTilSolen.get(planetNr).centerX = aScreen.relX(0);
		allePlaneterTilSolen.get(planetNr).centerY = aScreen.relY(0);
		allePlaneterTilSolen.get(planetNr).planetensHastinghed = speedAll[planetNr];
		;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstand = 0;
		allePlaneterTilSolen.get(planetNr).planetensTilbagelagteAfstandFraStart = 0;
		allePlaneterTilSolen.get(planetNr).omkreds = 400;
		allePlaneterTilSolen.get(planetNr).setRadiusPaaKredsloeb(kredsloebAll[planetNr]);
		allePlaneterTilSolen.get(planetNr).radius = sizeAll[planetNr];
		allePlaneterTilSolen.get(planetNr).color = Color.gray;
	}

	static public MouseAdapter ma = new MouseAdapter() {
		public void mouseClicked(MouseEvent evt) {
			if ((evt.getModifiers() & InputEvent.BUTTON1_MASK) != 0) {
				System.out.println("click A");
				zoom(1.1);
			}

			if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
				System.out.println("Click B");
				zoom(-1.1);
			}

		}

	};

	public static void zoom(double d) {
		for (int i = 0; i < allePlaneterTilSolen.size(); i++) {
			if (d > 0) {
				allePlaneterTilSolen.get(i)
						.setRadiusPaaKredsloeb(allePlaneterTilSolen.get(i).getRadiusPaaKredsloeb() * d);
				allePlaneterTilSolen.get(i).planetensHastinghed = allePlaneterTilSolen.get(i).planetensHastinghed * d;
			} else {
				allePlaneterTilSolen.get(i)
						.setRadiusPaaKredsloeb(allePlaneterTilSolen.get(i).getRadiusPaaKredsloeb() / d);
				allePlaneterTilSolen.get(i).planetensHastinghed = allePlaneterTilSolen.get(i).planetensHastinghed / d;

			}
			System.out.print("\n" + i + " " + allePlaneterTilSolen.get(i).name + " zommer....");

		}
	}

	public static void speed(int speed) {
		for (int i = 0; i < allePlaneterTilSolen.size(); i++) {
			allePlaneterTilSolen.get(i).planetensHastinghed += speed;
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
