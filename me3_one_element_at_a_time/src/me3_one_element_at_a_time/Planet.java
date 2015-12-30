//
// http://www.windows2universe.org/our_solar_system/planets_table.html
// http://www.fourmilab.ch/cgi-bin/Solar
//
package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

// En planet indeholde selv sit eget kredsøb
public class Planet {
	double radiusTilKredsloebCenter = 0;
	// planetIndex = plads/nummer fra centrum eks = Jorden = 3;
	private int planetIndex = 0;
	private double radius = 0;

	// orbit real x,y
	int orbitRealX, orbitRealY = 0;

	// faktisk X,Y er det rigtigt x,y på skærmen
	long faktiskX = 0;
	long faktiskY = 0;
	// center X,Y er relativt
	long centerX = 0;
	long centerY = 0;
	
	//enheds Cirkel x,y
	double eX, eY = 0;
	float vinkelFraCenterTilPlanet = 0;

	String name;
	private double omkreds;
	public ArrayList<Planet> moons = new ArrayList<Planet>();
	public long planetensTilbagelagteAfstand = 0;
	public long planetensTilbagelagteAfstandFraStart = 0;
	public double planetensHastighed = 0;
	public Color color;
	public boolean drawRayToPlanet = true;
	public boolean drawName = true;
	public boolean drawOrbit = true;
	public boolean drawMoons = false;

	public boolean isDrawMoons() {
		return drawMoons;
	}

	public void setDrawMoons(boolean drawMoons) {
		this.drawMoons = drawMoons;
	}

	// GETTER AND SETTERS *************************************
	public double getRadius() {
		return radius;
	}

	public void setRadius(double planetsize) {
		if (planetsize > 4)
			this.radius = planetsize;
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
	
	public void getPlanetX(double d) {
		double radianer = (d * Math.PI) / 180;
	    eX = Math.cos(radianer);
//		faktiskX = Math.round(eX * getRadiusPaaKredsloeb() / 2);
	    faktiskX = (long) (eX * getRadiusPaaKredsloeb() /2 );
		faktiskX += centerX;
	}

	public void getPlanetY(double d) {
		double radianer = (d * Math.PI) / 180;
		eY = Math.sin(radianer);

//		faktiskY = (int) (eY * getRadiusPaaKredsloeb()) / 2;
		faktiskY = (long) (eY * getRadiusPaaKredsloeb() / 2);
		faktiskY += centerY;
	}

	static private final double calcCircleCenter(long javaCoord, double radius2) {
		return (javaCoord - (radius2 / 2));
	}

	public int getCircleCenterX() {
		return (int) (calcCircleCenter(faktiskX, radius));
	}

	public int getCircleCenterY() {
		return (int) (calcCircleCenter(faktiskY, radius));
	}

	public void LogPlanetXY() {
		System.out.println(this.name + "(X,Y) : " + this.faktiskX + "," + this.faktiskY + " Vinkel : "
				+ this.vinkelFraCenterTilPlanet);
	}

	public double beregnAfstandTilbagelagtIalt(double click) {
		// afstand tilbagelagt i kredsløbet i alt
		double retVal = click * this.planetensHastighed;
		return retVal;
	}

	public void beregnPlanetensGradIKredsløbet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsløbet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		float retVal = 0;
		double afstand = this.planetensTilbagelagteAfstandFraStart
				+ this.beregnAfstandTilbagelagtIalt(d) % this.omkreds;
		retVal = (float) ((afstand / this.omkreds) * 360);

		this.vinkelFraCenterTilPlanet = retVal;
	}

	public void calcOrbit() {
		// calculate Orbit real Center X,Y
		orbitRealX = Util.calcRealCoord(this.centerX, radiusTilKredsloebCenter);
		orbitRealY = Util.calcRealCoord(this.centerY, radiusTilKredsloebCenter);

	}

	public void calcPlanet(EclipseTime ec) {
		getPlanetX((vinkelFraCenterTilPlanet));
		getPlanetY((vinkelFraCenterTilPlanet));
		beregnPlanetensGradIKredsløbet(ec.getSSClick());
		calcOrbit();
	}

	// DRAW Funktion
	// ---------------------------------------------------------------------------------------
	public void drawPlanet(Graphics2D g, EclipseTime ec) {
		g.setColor(this.color);
		draw(g, 0, 360, "PLANET", ec);
	}

	public void draw(Graphics2D g, int startVinkel, int antalGrader, String type, EclipseTime ec) {
		drawOrbit(g);
		drawMoons(g, ec);
	}
	
	
	public void drawOrbit(Graphics2D g) {
		// Selve kredløbs ringen
		if (isDrawOrbit())
			g.drawArc(orbitRealX, orbitRealY, (int) radiusTilKredsloebCenter, (int) radiusTilKredsloebCenter, 0, 360);

		// Tegn selve planeten PÅ kredsløbstregen
		g.fillArc(getCircleCenterX(), getCircleCenterY(), (int)radius, (int)radius, 0, 360);

		if (isDrawName()) {
			g.drawString(this.name, (int)(faktiskX + radius), (int)(faktiskY + radius));
		}
		// Pilen ud til kredsløbsstregen
		if (isDrawRayToPlanet()) {
			g.drawLine((int) this.centerX, (int) this.centerY, (int) faktiskX, (int) faktiskY);
		}

	}


	public void drawMoons(Graphics2D g, EclipseTime ec) {
		if (this.isDrawMoons()) {
			for (Planet moons : moons) {
				moons.centerX = this.faktiskX;
				moons.centerY = this.faktiskY;
				moons.calcOrbit();
				moons.calcPlanet(ec);
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
			moon.planetensHastighed = Util.randInt(0, (int) this.planetensHastighed * 5);
			moon.planetensTilbagelagteAfstand = 0;
			moon.planetensTilbagelagteAfstandFraStart = Util.randInt(0, (int) this.getOmkredsPaaKredsloebet());
			moon.setRadiusPaaKredsloeb(
					this.getRadius() + this.getRadius() / 10 + Util.randInt(1, (int) (this.getRadius() / 3)));
			moon.setRadius(5);
			moon.color = (new Color(Util.randInt(200, 255), Util.randInt(30, 100), Util.randInt(30, 90)));
			moon.setDrawRayToPlanet(false);
			moon.setDrawName(false);
			moon.setDrawOrbit(false);
			this.addMoon(moon);

		}
	}

}
