package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_Planet extends JPanel {
	private static final long serialVersionUID = 1L;
	static LokalKoordinatsystem aScreen = new LokalKoordinatsystem(500, 500);
	static EclipseTime ec = new EclipseTime();
	static Planet merkur = new Planet();
	static Planet venus = new Planet();
	static Planet earth = new Planet();
	static Planet mars = new Planet();

	public Test_Planet() {
		// TODO Auto-generated constructor stub
	}

	public void drawGrid(Graphics2D g2d) {
		g2d.drawLine(0, 0, aScreen.maxX, aScreen.maxY);
		g2d.drawLine(aScreen.maxX, 0, 0, aScreen.maxY);

		g2d.drawLine(aScreen.relX(0), 0, aScreen.relX(0), aScreen.maxY);
		g2d.drawLine(0, aScreen.relY(0), aScreen.maxX, aScreen.relY(0));
		// g2d.drawLine(0, 0, aScreen.relX(0), aScreen.relY(0));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		earth.drawPlanet(g2d, ec);
        mars.drawPlanet(g2d, ec);
        merkur.drawPlanet(g2d, ec);
        venus.drawPlanet(g2d, ec);
        drawGrid(g2d);
	}
	
	static public void initUniverse(){

		merkur.name = "Merkur";
		merkur.centerX = aScreen.relX(0);
		merkur.centerY = aScreen.relY(0);
		merkur.planetensHastinghed = 1;
		merkur.planetensTilbagelagteAfstand = 1;
		merkur.planetensTilbagelagteAfstandFraStart = 0;
		merkur.omkreds = 100;
		merkur.setRadiusPaaKredsloebet(70);
		merkur.radius = 10;
		merkur.color = Color.PINK;

		venus.name = "Venus";
		venus.centerX = aScreen.relX(0);
		venus.centerY = aScreen.relY(0);
		venus.planetensHastinghed = 1;
		venus.planetensTilbagelagteAfstand = 1;
		venus.planetensTilbagelagteAfstandFraStart = 0;
		venus.omkreds = 100;
		venus.setRadiusPaaKredsloebet(170);
		venus.radius = 10;
		venus.color = Color.CYAN;
		
		
		earth.name = "Jorden";
		earth.centerX = aScreen.relX(0);
		earth.centerY = aScreen.relY(0);
		earth.planetensHastinghed = 1;
		earth.planetensTilbagelagteAfstand = 1;
		earth.planetensTilbagelagteAfstandFraStart = 0;
		earth.omkreds = 100;
		earth.setRadiusPaaKredsloebet(200);
		earth.radius = 10;
		earth.color = Color.GREEN;

		mars.name = "Mars";
		mars.centerX = aScreen.relX(0);
		mars.centerY = aScreen.relY(0);
		mars.planetensHastinghed = 7;
		mars.planetensTilbagelagteAfstand = 121;
		mars.planetensTilbagelagteAfstandFraStart = 0;
		mars.omkreds = 400;
		mars.setRadiusPaaKredsloebet(400);
		mars.radius = 20;
		mars.color = Color.RED;	
		
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// theSun.tilfoejPlanetTilKredloeb(theEarth);		
		JFrame frame = new JFrame("MultiEclipse");
		Test_Planet universe = new Test_Planet();
		initUniverse();
		frame.add(universe);
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
