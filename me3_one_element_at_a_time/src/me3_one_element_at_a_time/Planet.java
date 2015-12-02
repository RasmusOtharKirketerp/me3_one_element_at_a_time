package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

// En planet indeholde selv sit eget kredsøb
public class Planet {
	int radiusTilKredsloebCenter = 0;
//	int diameterForPlanet = 0;
	int radius = 0;
	
	// faktisk X,Y er det rigtigt x,y på skærmen
	int faktiskX = 0;
	int faktiskY = 0;
// center X,Y er relativt 
	int centerX = 0;
	int centerY = 0;
	int vinkelFraCenterTilPlanet = 0;
	

	String name;
	public int omkreds;
	public ArrayList<Planet> planeterIkredsloeb = new ArrayList<Planet>();
	public long planetensTilbagelagteAfstand = 0;
	public long planetensTilbagelagteAfstandFraStart = 0 ;
	public long planetensHastinghed = 0;
	public Color color;
	
 	public Planet() {
		// Constructor
	}
	public int getOmkredsPaaKredsloebet() {
		return this.omkreds;
	}

	public void setOmkredsPaaKredsloebet(int o) {
		this.omkreds = o;
		this.radiusTilKredsloebCenter = o / (int) Math.PI / 2;
	}

	public double getRadiusPaaKredsloeb() {
		return radiusTilKredsloebCenter;
	}

	public void setRadiusPaaKredsloebet(int radius) {
		this.radiusTilKredsloebCenter = radius;
		this.omkreds = (int) Math.PI * this.radiusTilKredsloebCenter * 2;
	}

	public double getPlanetX(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelX = Math.cos(radianer);

		faktiskX = (int) (enhedsCirkelX * getRadiusPaaKredsloeb());
		faktiskX += centerX;
		return faktiskX;
	}

	public double getPlanetY(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelY = Math.sin(radianer);

		faktiskY = (int) (enhedsCirkelY * getRadiusPaaKredsloeb());
		faktiskY += centerY;
		return faktiskY;
	}

	public void LogPlanetXY() {
		System.out.println(this.name + "(X,Y) : " + this.faktiskX + "," + this.faktiskY + " Vinkel : "
				+ this.vinkelFraCenterTilPlanet);
	}

	public void tilfoejPlanetTilKredloeb(Planet planet) {
		this.planeterIkredsloeb.add(planet);
	}
	
	public double beregnAfstandTilbagelagtIalt(double click) {
		// afstand tilbagelagt i kredsløbet i alt
		double retVal = click * this.planetensHastinghed;
		return retVal;
	}	
	
	public void beregnPlanetensGradIKredsløbet(double d) {
		// Denne funktin skal retunere den grad planetet er i kredsløbet
		// ud fra den vinkel en linje skulle tegnes fra centrum og ud
		int retVal = 0;
		double afstand = this.planetensTilbagelagteAfstandFraStart
				+ this.beregnAfstandTilbagelagtIalt(d) % this.omkreds;
		retVal = (int) ((afstand / this.omkreds) * 360);

		this.vinkelFraCenterTilPlanet = retVal;
	}	
	

	// DRAW Funktion
	// ---------------------------------------------------------------------------------------
	public void drawPlanet(Graphics2D g, EclipseTime ec) {
		System.out.println("drawplanet");
		beregnPlanetensGradIKredsløbet(ec.getSSClick());
		LogPlanetXY();
		draw(g, 0, 360, color, "PLANET", ec);
	}

	public void drawOrbit(Graphics2D g) {
		System.out.println("drawOrbit");
		
		int realX = this.centerX - (radiusTilKredsloebCenter / 2);
        int realY = this.centerY - (radiusTilKredsloebCenter / 2);
		g.drawArc(realX , realY, radiusTilKredsloebCenter, radiusTilKredsloebCenter, 0, 360);
		
		g.fillArc(realX, realY, radiusTilKredsloebCenter, radiusTilKredsloebCenter, this.vinkelFraCenterTilPlanet, 3);

	}
	
	public void draw(Graphics2D g, int startVinkel, int antalGrader, Color color, String type, EclipseTime ec) {      
		// tegn selve planeten :
		int realX = this.centerX - (radius / 2);
        int realY = this.centerY - (radius / 2);
		
		g.setColor(color);
		if (type == "KREDSLØB")
			g.drawArc(realX, realY, this.radius, this.radius, startVinkel, antalGrader);
		if (type == "PLANET")
			g.fillArc(realX, realY, radius, radius, startVinkel, antalGrader);

		// tegn kredsløb hvis den har et...
		if (this.radiusTilKredsloebCenter > 0)
			drawOrbit(g);
		else
			System.out.println("Intet kredsløb for : " + this.name);
	}

}
