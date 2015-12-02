package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_Screen extends JPanel {
	private static final long serialVersionUID = 1L;
	static Screen planetCenter = new Screen(400, 600);
	static Screen planetCenter2 = new Screen(200, 300);

	public Test_Screen() {
		// TODO Auto-generated constructor stub
	}

	static public void show(String arg0) {
		System.out.println(arg0);
	}

	static public void show(int arg0) {
		System.out.println(arg0);
	}

	static boolean val(int a, int b) {
		if (a == b) {
			show(" ok");
			return true;
		} else {
			show(" error");
			return false;
		}
	}

	static boolean val(double a, int b) {
		if (a == b) {
			show(" ok");
			return true;
		} else {
			show(" error");
			return false;
		}

	}
	
	public void drawCircle(Graphics2D g, int x, int y, int hoejde, int bredde, int startVinkel, int antalGrader, Color color, String type){
		x = x - (bredde / 2);
		y = y - (bredde / 2);
		g.setColor(color);
		if (type == "KREDSLØB")
		  g.drawArc(x, y, hoejde, bredde, startVinkel, antalGrader);
		if (type == "PLANET")
	      g.fillArc(x, y, hoejde, bredde, startVinkel, antalGrader);

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	       // tegn fra rel 0,0 til alle hjørner
			g.drawLine(planetCenter.relX(0), planetCenter.relY(0), 0,0);
			g.drawLine(planetCenter.relX(0), planetCenter.relY(0), 0,planetCenter.maxY);
			g.drawLine(planetCenter.relX(0), planetCenter.relY(0), planetCenter.maxX,planetCenter.maxY);
			g.drawLine(planetCenter.relX(0), planetCenter.relY(0), planetCenter.maxX,0);
			
			drawCircle(g2d, planetCenter.relX(0), planetCenter.relY(0), 100, 100, 0, 360, Color.GREEN, "PLANET");
			drawCircle(g2d, 0, 0, 100, 100, 0, 360, Color.BLUE,"KREDSLØB");
			drawCircle(g2d, planetCenter.relX(0), planetCenter.relY(0), 200, 200, 0, 180, Color.PINK,"PLANET");
		       // tegn fra rel 0,0 til alle hjørner
			g.drawLine(planetCenter2.relX(0), planetCenter2.relY(0), 0,0);
			g.drawLine(planetCenter2.relX(0), planetCenter2.relY(0), 0,planetCenter2.maxY);
			g.drawLine(planetCenter2.relX(0), planetCenter2.relY(0), planetCenter2.maxX,planetCenter2.maxY);
			g.drawLine(planetCenter2.relX(0), planetCenter2.relY(0), planetCenter2.maxX,0);
			
			drawCircle(g2d, planetCenter2.relX(0), planetCenter2.relY(0), 100, 100, 0, 360, Color.GREEN, "PLANET");
			drawCircle(g2d, 0, 0, 100, 100, 0, 360, Color.BLUE,"KREDSLØB");
			drawCircle(g2d, planetCenter2.relX(0), planetCenter2.relY(0), 200, 200, 0, 180, Color.PINK,"PLANET");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// test all functions :
		show("MaxX            : " + planetCenter.maxX);
		val(planetCenter.maxX, 400);
		show("maxY            : " + planetCenter.maxY);
		val(planetCenter.maxY, 600);
		show("Center X        : " + planetCenter.center_x);
		val(planetCenter.center_x, 200);
		show("Center Y        : " + planetCenter.center_y);
		val(planetCenter.center_y, 200);
		show("convertedX(100) : " + planetCenter.relX(100));
		val(planetCenter.relX(100), 300);
		show("convertedX(-100) : " + planetCenter.relX(-100));
		val(planetCenter.relX(-100), 100);
		show("convertedY(100) : " + planetCenter.relY(100));
		val(planetCenter.relY(100), 400);
		show("convertedY(-100) : " + planetCenter.relY(-100));
		val(planetCenter.relY(-100), 200);
		show("");
		
		

		JFrame frame = new JFrame("MultiEclipse");
		Test_Screen universe = new Test_Screen();
		frame.add(universe);
		frame.setSize(planetCenter.maxX, planetCenter.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
