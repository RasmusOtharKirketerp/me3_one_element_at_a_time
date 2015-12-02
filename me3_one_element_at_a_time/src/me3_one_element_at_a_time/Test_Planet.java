package me3_one_element_at_a_time;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_Planet extends JPanel {
	private static final long serialVersionUID = 1L;
	static LokalKoordinatsystem aScreen = new LokalKoordinatsystem(500, 500);
	static EclipseTime ec = new EclipseTime();


	static Planet earth = new Planet();
	
	
	public Test_Planet() {
		// TODO Auto-generated constructor stub
	}

	public void drawGrid(Graphics2D g2d) {
		g2d.drawLine(0, 0, aScreen.maxX, aScreen.maxY);
		g2d.drawLine(aScreen.maxX, 0, 0, aScreen.maxY);

		g2d.drawLine(aScreen.relX(0), 0, aScreen.relX(0), aScreen.maxY);
		g2d.drawLine(0, aScreen.relY(0), aScreen.maxX, aScreen.relY(0));
//        g2d.drawLine(0, 0, aScreen.relX(0), aScreen.relY(0));
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawGrid(g2d);
		//theSun.drawPlanet(g2d, ec);
		earth.drawPlanet(g2d, ec);

	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//theSun.tilfoejPlanetTilKredloeb(theEarth);
        earth.name = "Jorden";
        earth.centerX = aScreen.relX(0);
        earth.centerY = aScreen.relY(0);
		earth.planetensHastinghed = 1;
		earth.planetensTilbagelagteAfstand = 1;
		earth.planetensTilbagelagteAfstandFraStart = 0;
		earth.omkreds = 100;
        earth.setRadiusPaaKredsloebet(100);
        earth.radius = 10;
        
        //earth.setOmkredsPaaKredsloebet(100);
		
		
		JFrame frame = new JFrame("MultiEclipse");
		Test_Planet universe = new Test_Planet();
		frame.add(universe);
		frame.setSize(aScreen.maxX, aScreen.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		while (true) {
		

			universe.repaint();

			Thread.sleep(400);

			ec.click();

		}

	}

}
