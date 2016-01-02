//
// http://www.windows2universe.org/our_solar_system/planets_table.html
// http://www.fourmilab.ch/cgi-bin/Solar
// http://planets.findthedata.com/ 
//
package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

// En planet indeholde selv sit eget kredsøb
public class Planet {
	double radiusTilKredsloebCenter = 0;
	// planetIndex = plads/nummer fra centrum eks = Jorden = 3;
	private int planetIndex = 0;

	// planet level er en marings for hvilket niveau planet er på
	// level 0 = solen
	// level 1 planeter = Jorden, mars Jupiter osv....
	// level 2 er måner til level 1
	// level 3 er måner til level 2....
	int planetLevel = 0;
	private double radius = 1;

	// orbit real x,y
	int orbitRealX, orbitRealY = 0;

	// faktisk X,Y er det rigtigt x,y på skærmen
	long faktiskX = 0;
	long faktiskY = 0;
	// center X,Y er relativt
	long centerX = 0;
	long centerY = 0;

	// enheds Cirkel x,y
	double eX, eY = 0;
	float vinkelFraCenterTilPlanet = 0;
	// VinklerTilAndrePlaneter bruges til at analyser om planter ligger på samme
	// linje
	long[] vinklerTilAndrePlanet = new long[10];

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

	BufferedImage planetImage = null;

	public void setImage() {
		try {
			planetImage = ImageIO.read(new File(UniverseData.IMAGEPATH + this.name + ".png"));
		} catch (IOException e) {
			try {
				planetImage = ImageIO.read(new File(UniverseData.IMAGEPATH + "Default.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

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
		this.radius = planetsize;
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
		// faktiskX = Math.round(eX * getRadiusPaaKredsloeb() / 2);
		faktiskX = (long) (eX * getRadiusPaaKredsloeb() / 2);
		faktiskX += centerX;
	}

	public void getPlanetY(double d) {
		double radianer = (d * Math.PI) / 180;
		eY = Math.sin(radianer);

		// faktiskY = (int) (eY * getRadiusPaaKredsloeb()) / 2;
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

	public static double calcRotationAngleInDegrees(Point centerPt, Point targetPt) {
		double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);
		double angle = Math.toDegrees(theta);
		if (angle < 0)
			angle += 360;
		angle *= -1;
		return angle;
	}

	public void calcPlanet(EclipseTime ec) {
		getPlanetX((vinkelFraCenterTilPlanet));
		getPlanetY((vinkelFraCenterTilPlanet));
		beregnPlanetensGradIKredsløbet(ec.getSSClick());
		calcOrbit();
	}

	// DRAW Funktion - Kan tegne alle elementer
	// ---------------------------------------------------------------------------------------
	public void draw(Graphics2D g, EclipseTime ec, int analyse, ArrayList<Planet> allPlanets) {
		// Kontrol af hvad der skal tegnes
		drawPlanet(g);
		if (isDrawName())
			drawPlanetName(g);
		if (isDrawOrbit())
			drawOrbit(g);
		if (this.isDrawMoons())
			drawMoons(g, ec, analyse, allPlanets);
		if (isDrawRayToPlanet())
			drawRayToPlanet(g);

		if (planetIndex > -1) {
			calcVinkelTilAndrePlaneter(allPlanets);
			drawAnalyseRays(g, analyse, allPlanets);
		}
	}

	public void drawPlanetName(Graphics2D g) {
		g.drawString(this.name, (int) (faktiskX) - 20, (int) (faktiskY - (radius / 2) - 10));
	}

	public void drawPlanet(Graphics2D g) {
		g.drawImage(planetImage, getCircleCenterX(), getCircleCenterY(), (int) radius, (int) radius, null);
	}

	public void drawRayToPlanet(Graphics2D g) {
		g.drawLine((int) this.centerX, (int) this.centerY, (int) faktiskX, (int) faktiskY);
	}

	public void drawOrbit(Graphics2D g) {
		g.drawArc(orbitRealX, orbitRealY, (int) radiusTilKredsloebCenter, (int) radiusTilKredsloebCenter, 0, 360);
	}

	public void drawMoons(Graphics2D g, EclipseTime ec, int analyse, ArrayList<Planet> allPlanets) {
		for (Planet moons : moons) {
			moons.centerX = this.faktiskX;
			moons.centerY = this.faktiskY;
			moons.calcOrbit();
			moons.calcPlanet(ec);
			moons.draw(g, ec, analyse, allPlanets);
		}
	}

	public void calcVinkelTilAndrePlaneter(ArrayList<Planet> allPlanets) {

		for (int i = 0; i < allPlanets.size(); i++) {
			Planet planet = new Planet();
			planet = allPlanets.get(i);
			Point pointCenter = new Point();
			Point pointTo = new Point();

			pointCenter.x = (int) this.faktiskX;
			pointCenter.y = (int) this.faktiskY;

			pointTo.x = (int) planet.faktiskX;
			pointTo.y = (int) planet.faktiskY;
			vinklerTilAndrePlanet[i] = (long) calcRotationAngleInDegrees(pointCenter, pointTo);
			// System.out.println(name + "(vinkel til " + planet.name+ ") : " +
			// vinklerTilAndrePlanet[i]);
		}

	}

	public void drawAnalyseRays(Graphics2D g, int analyse, ArrayList<Planet> allPlanets) {
		int x, y = 0;
		for (int i = 0; i < vinklerTilAndrePlanet.length - 1; i++) {
			if (planetLevel == 1) {
				g.setColor(color);
				x = ((planetIndex / 1000) * 130) + 240;
				y = 0;
				g.drawString(name, x + 20, y + 20);
				g.drawImage(planetImage, x + 20, y + 40, 40, 40, null);
				g.setColor(allPlanets.get(i).color);
				y = 10;
				g.fillArc(x - 10, y, (int) (100), (int) (100), (int) vinklerTilAndrePlanet[i], 2);
			}
			if (planetLevel == 2) {
				g.setColor(color);
				x = ((planetIndex / 1000) * 130) + 240;
				y = 100;
				g.drawString(name, x + 20, y + 20);
				g.drawImage(planetImage, x + 20, y + 40, 40, 40, null);
				g.setColor(allPlanets.get(i).color);
				y = y + 10;
				g.fillArc(x - 10, y, (int) (100), (int) (100), (int) vinklerTilAndrePlanet[i], 2);
			}
		}

	}

	public void moonGenerator(int GenMoon) {
		for (int i = 0; i < GenMoon; i++) {
			Planet moon = new Planet();
			moon.name = this.name + "'s moon" + i;
			moon.centerX = this.centerX;
			moon.centerY = this.centerY;
			moon.planetIndex = this.planetIndex + (i * 100);
			moon.planetLevel = this.planetLevel + 1;
			moon.planetensHastighed = Util.randInt(0, (int) this.planetensHastighed * 5);
			moon.planetensTilbagelagteAfstand = 0;
			moon.planetensTilbagelagteAfstandFraStart = Util.randInt(0, (int) this.getOmkredsPaaKredsloebet());
			moon.setRadiusPaaKredsloeb(
					this.getRadius() + this.getRadius() / 10 + Util.randInt(1, (int) (this.getRadius() / 3)));
			moon.setRadius(this.radius / Util.randInt(30, 50));
			moon.color = (new Color(Util.randInt(200, 255), Util.randInt(30, 100), Util.randInt(30, 90)));
			moon.setDrawRayToPlanet(false);
			moon.setDrawName(false);
			moon.setDrawOrbit(false);
			moon.setImage();
			this.addMoon(moon);

		}
	}

}
