package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

// En planet indeholde selv sit eget kreds�b
public class Planet {
	double radiusTilKredsloebCenter = 0;
	// planetIndex = plads/nummer fra centrum eks = Jorden = 3;
	private int planetIndex = 0;
	private int radius = 0;

	// faktisk X,Y er det rigtigt x,y p� sk�rmen
	long faktiskX = 0;
	long faktiskY = 0;
	// center X,Y er relativt
	long centerX = 0;
	long centerY = 0;
	float vinkelFraCenterTilPlanet = 0;

	String name;
	private double omkreds;
	public ArrayList<Planet> moons = new ArrayList<Planet>();
	public long planetensTilbagelagteAfstand = 0;
	public long planetensTilbagelagteAfstandFraStart = 0;
	public double planetensHastinghed = 0;
	public Color color;
	public boolean drawRayToPlanet = true;
	public boolean drawName = true;
	public boolean drawOrbit = true;
	public boolean drawMoons = true;

	public boolean isDrawMoons() {
		return drawMoons;
	}

	public void setDrawMoons(boolean drawMoons) {
		this.drawMoons = drawMoons;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	// GETTER AND SETTERS *************************************
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		if (radius > 4)
			this.radius = radius;
		else
			this.radius = 5;
	}

	public int getPlanetIndex() {
		return planetIndex;
	}

	public void setPlanetIndex(int planetIndex) {
		this.planetIndex = planetIndex;
	}

	public boolean isDrawOrbit() {
		return drawOrbit;
	}

	public void setDrawOrbit(boolean drawOrbit) {
		this.drawOrbit = drawOrbit;
	}

	public boolean isDrawName() {
		return drawName;
	}

	public void setDrawName(boolean drawName) {
		this.drawName = drawName;
	}

	public boolean isDrawRayToPlanet() {
		return drawRayToPlanet;
	}

	public void setDrawRayToPlanet(boolean drawRayToPlanet) {
		this.drawRayToPlanet = drawRayToPlanet;
	}

	public Planet() {
		// Constructor
	}

	public void addMoon(Planet moon) {
		this.moons.add(moon);
	}

	public double getOmkredsPaaKredsloebet() {
		return this.omkreds;
	}

	public void setOmkredsPaaKredsloebet(int o) {
		this.omkreds = o;
		this.radiusTilKredsloebCenter = o / (int) Math.PI / 2;
	}

	public double getRadiusPaaKredsloeb() {
		return radiusTilKredsloebCenter;
	}

	public void setRadiusPaaKredsloeb(double d) {
		this.radiusTilKredsloebCenter = d;
		this.omkreds = (int) Math.PI * this.radiusTilKredsloebCenter * 2;
	}

	public double getPlanetX(float vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelX = Math.cos(radianer);
		faktiskX = Math.round(enhedsCirkelX * getRadiusPaaKredsloeb() / 2);
		faktiskX += centerX;
		return faktiskX;
	}

	public double getPlanetY(float vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelY = Math.sin(radianer);

		faktiskY = (int) (enhedsCirkelY * getRadiusPaaKredsloeb()) / 2;
		faktiskY += centerY;
		return faktiskY;
	}

	public void LogPlanetXY() {
		System.out.println(this.name + "(X,Y) : " + this.faktiskX + "," + this.faktiskY + " Vinkel : "
				+ this.vinkelFraCenterTilPlanet);
	}

	public double beregnAfstandTilbagelagtIalt(double click) {
		// afstand tilbagelagt i kredsl�bet i alt
		double retVal = click * this.planetensHastinghed;
		return retVal;
	}

	public void beregnPlanetensGradIKredsl�bet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsl�bet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		float retVal = 0;
		double afstand = this.planetensTilbagelagteAfstandFraStart
				+ this.beregnAfstandTilbagelagtIalt(d) % this.omkreds;
		retVal = (float) ((afstand / this.omkreds) * 360);

		this.vinkelFraCenterTilPlanet = retVal;
	}

	// DRAW Funktion
	// ---------------------------------------------------------------------------------------
	public void drawPlanet(Graphics2D g, EclipseTime ec) {
		g.setColor(this.color);
		beregnPlanetensGradIKredsl�bet(ec.getSSClick());
		draw(g, 0, 360, "PLANET", ec);
	}

	public void drawOrbit(Graphics2D g) {
		g.setColor(this.color);
		int realX = (int) (this.centerX - (radiusTilKredsloebCenter / 2));
		int realY = (int) (this.centerY - (radiusTilKredsloebCenter / 2));
		// Selv kredl�bs ringen
		if (isDrawOrbit())
			g.drawArc(realX, realY, (int) radiusTilKredsloebCenter, (int) radiusTilKredsloebCenter, 0, 360);

		// Tegn selve planeten P� kredsl�bstregen
		// x - cos til vinklen * faktiskX
		// y - sin til vinklen * faktiskY
		int cosX = (int) getPlanetX(vinkelFraCenterTilPlanet) - (radius / 2);
		int sinY = (int) getPlanetY(vinkelFraCenterTilPlanet) - (radius / 2);
		g.fillArc(cosX, sinY, radius, radius, 0, 360);
		if (isDrawName()) {
			g.drawString(this.name, cosX + radius, sinY + radius);
		}
		// Pilen ud til kredsl�bsstregen
		if (isDrawRayToPlanet()) {
			// g.fillArc(realX, realY, (int) radiusTilKredsloebCenter, (int)
			// radiusTilKredsloebCenter,
			// this.vinkelFraCenterTilPlanet * -1, 1);
			g.drawLine((int) this.centerX, (int) this.centerY, cosX + radius / 2, sinY + radius / 2);
		}

	}

	public void draw(Graphics2D g, int startVinkel, int antalGrader, String type, EclipseTime ec) {
		// tegn kredsl�b hvis den har et...
		drawOrbit(g);
		// tegn m�ner til planeten..
		drawMoons(g, ec);
	}

	public void drawMoons(Graphics2D g, EclipseTime ec) {
		if (this.isDrawMoons()) {
			for (Planet moons : moons) {
				moons.centerX = this.faktiskX;
				moons.centerY = this.faktiskY;
				moons.drawPlanet(g, ec);
			}
		}

	}

	public void moonGenerator(int GenMoon) {
		for (int i = 0; i < GenMoon; i++) {
			Planet moon = new Planet();
			moon.name = "Moon" + i;
			moon.centerX = this.centerX;
			moon.centerY = this.centerY;
			moon.planetensHastinghed = randInt(0, (int) this.planetensHastinghed * 5);
			moon.planetensTilbagelagteAfstand = 0;
			moon.planetensTilbagelagteAfstandFraStart = randInt(0, (int) this.getOmkredsPaaKredsloebet());
			moon.setRadiusPaaKredsloeb(this.getRadius() + this.getRadius() / 10 + randInt(1, this.getRadius() / 3));
			moon.setRadius(5);
			moon.color = (new Color(randInt(200, 255), randInt(30, 100), randInt(30, 90)));
			moon.setDrawRayToPlanet(false);
			moon.setDrawName(false);
			moon.setDrawOrbit(false);
			this.addMoon(moon);

		}
	}

}
