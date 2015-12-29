package me3_one_element_at_a_time;

import java.awt.Color;
import java.util.ArrayList;

public final class UniverseData {

   static final int MAX_PLANETS = 9;	
   static final int MERKUR  = 0;
   static final int VENUS   = 1;
   static final int JORDEN  = 2;
   static final int MARS    = 3;
   static final int JUPITOR = 4;
   static final int SATURN  = 5;
   static final int URANUS  = 6;
   static final int NEPTUN  = 7;
   static final int PLUTO   = 8;   
   
   ArrayList<Planet> allPlanetsToSun = new ArrayList<Planet>();
   
   
   
	public UniverseData(boolean debug) {
		// TODO Auto-generated constructor stub
		scaleSizeToFit();
		for (int nyPlanetCounter = 0; nyPlanetCounter < UniverseData.MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allPlanetsToSun.add(nyPlanet);
			allPlanetsToSun.get(nyPlanetCounter).name = UniverseData.planetNames[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).color = UniverseData.planetColor[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).setPlanetIndex(nyPlanetCounter);
			allPlanetsToSun.get(nyPlanetCounter).planetensHastinghed = planetSpeed[nyPlanetCounter];
			if (!debug)
			  allPlanetsToSun.get(nyPlanetCounter).setRadiusPaaKredsloeb(planetsRadiusFromSunArrInAU[nyPlanetCounter]);
			else
		      allPlanetsToSun.get(nyPlanetCounter).setRadiusPaaKredsloeb(planetsRadiusFromSunArrInAU_DEBUG[nyPlanetCounter]);
			if (!debug)	
			   allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize[nyPlanetCounter]);
			else
		       allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize_DEBUG[nyPlanetCounter]);
				
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstand = 1;
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = Util.randInt(0,
					(int) allPlanetsToSun.get(nyPlanetCounter).getOmkredsPaaKredsloebet());
			// start alle planet i o afstand
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = 0;
			allPlanetsToSun.get(nyPlanetCounter).setDrawRayToPlanet(false);

		}
		allPlanetsToSun.get(UniverseData.JORDEN).moonGenerator(1);
		allPlanetsToSun.get(UniverseData.MARS).moonGenerator(2);
		allPlanetsToSun.get(UniverseData.JUPITOR).moonGenerator(67);
		allPlanetsToSun.get(UniverseData.SATURN).moonGenerator(53);
		allPlanetsToSun.get(UniverseData.URANUS).moonGenerator(27);
		allPlanetsToSun.get(UniverseData.NEPTUN).moonGenerator(13);

	}
	
	static final String[] planetNames                   = {   "Merkur",   "Venus",   "Jorden"
			                                   , "Mars",     "Jupiter", "Saturn"
			                                   , "Uranus"  , "Neptun",  "Pluto"};
	
	static final Color[] planetColor                    = {  Color.MAGENTA, Color.CYAN, Color.GREEN
			                                    ,Color.RED , Color.CYAN, Color.YELLOW
			                                    , Color.LIGHT_GRAY, Color.WHITE, Color.BLUE			
	};
	// Distance from the sun in AU 
	static final int[] planetsRadiusFromSunArrInAU       = { 39, 72, 100, 152, 520, 954, 1918, 3006, 3900 };

	// Speed of the planets in km/s
	static final int[] planetSpeed                       = { 47, 35, 29, 24, 13,  9,  6,  5,  4 };
	
	// Planets size in radius
	static final int[] planetSize                        = { 4878, 12104, 12756, 6787, 142800, 120000, 51118, 49528, 970 };
	
	// Debug Data
	static int[] planetsRadiusFromSunArrInAU_DEBUG = { 50, 100, 150, 200, 250, 300, 400, 500, 600 };
	static int[] planetSpeed_DEBUG                 = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	static int[] planetSize_DEBUG                  = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };	

	static final private void scaleSizeToFit(){
		for (int i = 0; i < planetSize.length; i++)
			planetSize[i] = planetSize[i] / 1000;
		
	}

	static final public void debug()
	{
		
	}
}
