package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_Planet extends JPanel {
	private static final long serialVersionUID = 1L;
	static Screen aScreen = new Screen(500, 500);
	static Planet theSun = new Planet("Sun", 500, 500, 20,20, 0, Color.BLUE);

	public Test_Planet() {
		// TODO Auto-generated constructor stub
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

     	theSun.drawPlanet(g2d, "PLANET");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("MultiEclipse");
		Test_Planet universe = new Test_Planet();
		frame.add(universe);
		frame.setSize(aScreen.maxX, aScreen.maxY);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
