package me3_one_element_at_a_time;

import java.awt.Color;

public class UniElement {
	Color color;
	String name = "";
	int aktuelVikelTilCenter = 0;
	int aktuelXRelTilCenter = 0;
	int aktuelYRelTilCenter = 0;
	int hoejde = 0;
	int bredde = 0;
	int centerXRel = 0;
	int centerYRel = 0;
	int radius = 0;
	int omkreds = 0;

	public UniElement(Color color, String name, int aktuelVikelTilCenter, int aktuelXRelTilCenter,
			int aktuelYRelTilCenter, int hoejde, int bredde, int centerXRel, int centerYRel) {
		this.color = color;
		this.name = name;
		this.aktuelVikelTilCenter = aktuelVikelTilCenter;
		this.aktuelXRelTilCenter = aktuelXRelTilCenter;
		this.aktuelYRelTilCenter = aktuelYRelTilCenter;
		this.hoejde = hoejde;
		this.bredde = bredde;
		this.centerXRel = centerXRel;
		this.centerYRel = centerYRel;
		setRadius(radius);

	}

	public int getOmkreds() {
		return this.omkreds;
	}

	public void setOmkreds(int o) {
		this.omkreds = o;
		this.radius = o / (int) Math.PI / 2;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
		this.omkreds = (int) Math.PI * this.radius * 2;
	}

	public double getPlanetX(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelX = Math.cos(radianer);

		aktuelXRelTilCenter = (int) (enhedsCirkelX * getRadius());
		aktuelXRelTilCenter += centerXRel;
		return aktuelXRelTilCenter;
	}

	public double getPlanetY(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelY = Math.sin(radianer);

		aktuelYRelTilCenter = (int) (enhedsCirkelY * getRadius());
		aktuelYRelTilCenter += centerYRel;
		return aktuelYRelTilCenter;
	}

	public void LogPlanetXY() {
		System.out.println(this.name + "(X,Y) : " + this.aktuelXRelTilCenter + "," + this.aktuelYRelTilCenter + " Vinkel : "
				+ this.aktuelVikelTilCenter);
	}

}
