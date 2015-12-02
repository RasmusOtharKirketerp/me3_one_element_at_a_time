package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;

public class Planet {
	String name           = "Planet";
	int curXRelToMainStar = 0;
	int curYRelToMainStar = 0;
	int speedOfplanet     = 0;
	int hoejde            = 0;
	int bredde            = 0;
	Color color;

	Screen screenForPlanet;

	public Planet(String inName, int curXRelToMainStar, int curYRelToMainStar, int hoejde, int bredde,
			int SpeedOfPlanet, Color color) {
		// Constructor
		this.name              = inName;
		this.curXRelToMainStar = curXRelToMainStar;
		this.curYRelToMainStar = curYRelToMainStar;
		this.screenForPlanet   = new Screen(curXRelToMainStar, curYRelToMainStar);
		this.hoejde            = hoejde;
		this.bredde            = bredde;
		this.color              = color;

	}

	public void drawPlanet(Graphics2D g, String type) {
		draw(g, 0, 360, color, "PLANET");
	}

	public void draw(Graphics2D g, int startVinkel, int antalGrader, Color color, String type) {
		int x = this.screenForPlanet.relX(0);
		int y = this.screenForPlanet.relY(0);

		System.out.println("x, y, h, b, sv, ag : " + x + " " + y + " " + hoejde + " " + bredde + " " + startVinkel + " "
				+ antalGrader);
		x = x - (bredde / 2);
		y = y - (bredde / 2);
		g.setColor(color);
		if (type == "KREDSLØB")
			g.drawArc(x, y, hoejde, bredde, startVinkel, antalGrader);
		if (type == "PLANET")
			g.fillArc(x, y, hoejde, bredde, startVinkel, antalGrader);

	}

}
