package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;

// En planet indeholde selv sit eget kredsøb
public class Planet {
	double radiusTilKredsloebCenter = 0;
	int radius = 0;
	
	// faktisk X,Y er det rigtigt x,y på skærmen
	int faktiskX = 0;
	int faktiskY = 0;
// center X,Y er relativt 
	int centerX = 0;
	int centerY = 0;
	int vinkelFraCenterTilPlanet = 0;
	

	String name;
	public double omkreds;
//	public ArrayList<Planet> planeterIkredsloeb = new ArrayList<Planet>();
	public long planetensTilbagelagteAfstand = 0;
	public long planetensTilbagelagteAfstandFraStart = 0 ;
	public double planetensHastinghed = 0;
	public Color color;
	
 	public Planet() {
		// Constructor
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

	public double getPlanetX(int vinkel) {
		double radianer = (vinkel * Math.PI) / 180;
		double enhedsCirkelX = Math.cos(radianer);

		faktiskX = (int) (enhedsCirkelX * getRadiusPaaKredsloeb()/ 2);
		faktiskX += centerX ;
		return faktiskX;
	}

	public double getPlanetY(int vinkel) {
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
		g.setColor(this.color);
		beregnPlanetensGradIKredsløbet(ec.getSSClick());
		draw(g, 0, 360, "PLANET", ec);
	}

	public void drawOrbit(Graphics2D g) {
		g.setColor(this.color);
		int realX = (int) (this.centerX - (radiusTilKredsloebCenter / 2));
        int realY = (int) (this.centerY - (radiusTilKredsloebCenter / 2));
        // Selv kredløbs ringen
		g.drawArc(realX , realY, (int)radiusTilKredsloebCenter, (int)radiusTilKredsloebCenter, 0, 360);
		
		// Pilen ud til kredsløbsstregen
		g.fillArc(realX, realY, (int)radiusTilKredsloebCenter, (int)radiusTilKredsloebCenter, this.vinkelFraCenterTilPlanet*-1, 1);

		// planeten PÅ kredsløbstregen
		// x - cos til vinklen * faktiskX
		// y - sin til vinklen * faktiskY
		int cosX = (int) getPlanetX(vinkelFraCenterTilPlanet) - (radius / 2);
		int sinY = (int) getPlanetY(vinkelFraCenterTilPlanet) - (radius / 2);
		g.fillArc(cosX, sinY, radius, radius, 0, 360);
		g.drawString(this.name, cosX+radius, sinY+radius);
	}
	
	public void draw(Graphics2D g, int startVinkel, int antalGrader, String type, EclipseTime ec) {      
		// tegn selve planeten :
		int realX = this.centerX - (radius / 2);
        int realY = this.centerY - (radius / 2);
		
		if (type == "KREDSLØB")
			g.drawArc(realX, realY, this.radius, this.radius, startVinkel, antalGrader);

		// tegn kredsløb hvis den har et...
		if (this.radiusTilKredsloebCenter > 0)
			drawOrbit(g);
		else
			System.out.println("Intet kredsløb for : " + this.name);
	}

}
