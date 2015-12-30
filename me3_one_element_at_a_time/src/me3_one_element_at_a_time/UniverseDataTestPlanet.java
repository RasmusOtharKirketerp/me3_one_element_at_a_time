package me3_one_element_at_a_time;

import java.awt.Color;
import java.util.ArrayList;

public final class UniverseDataTestPlanet {

   static final int MAX_PLANETS = 1;	
   static final int MERKUR  = 0;
   
   ArrayList<Planet> allPlanetsToSun = new ArrayList<Planet>();

     
	public UniverseDataTestPlanet(boolean debug) {
		// TODO Auto-generated constructor stub
		for (int nyPlanetCounter = 0; nyPlanetCounter < UniverseDataTestPlanet.MAX_PLANETS; nyPlanetCounter++) {
			Planet nyPlanet = new Planet();
			allPlanetsToSun.add(nyPlanet);
			allPlanetsToSun.get(nyPlanetCounter).name = UniverseDataTestPlanet.planetNames[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).color = UniverseDataTestPlanet.planetColor[nyPlanetCounter];
			allPlanetsToSun.get(nyPlanetCounter).setPlanetIndex(nyPlanetCounter);
			allPlanetsToSun.get(nyPlanetCounter).planetensHastighed = planetSpeed[nyPlanetCounter];
			if (!debug)
			  allPlanetsToSun.get(nyPlanetCounter).setRadiusPaaKredsloeb(meanDistanceFromSunAU[nyPlanetCounter]*100);
			else
		      allPlanetsToSun.get(nyPlanetCounter).setRadiusPaaKredsloeb(planetsRadiusFromSunArrInAU_DEBUG[nyPlanetCounter]);
			if (!debug)	
			   allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize[nyPlanetCounter]*10);
			else
		       allPlanetsToSun.get(nyPlanetCounter).setRadius(planetSize_DEBUG[nyPlanetCounter]);
				
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstand = 0;
//			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = Util.randInt(0,
//					(int) allPlanetsToSun.get(nyPlanetCounter).getOmkredsPaaKredsloebet());
			// start alle planet i o afstand
			allPlanetsToSun.get(nyPlanetCounter).planetensTilbagelagteAfstandFraStart = 0;
			allPlanetsToSun.get(nyPlanetCounter).setDrawRayToPlanet(false);

		}
		
/*		allPlanetsToSun.get(UniverseData.JORDEN).moonGenerator(1);
		allPlanetsToSun.get(UniverseData.MARS).moonGenerator(2);
		allPlanetsToSun.get(UniverseData.JUPITOR).moonGenerator(67);
		allPlanetsToSun.get(UniverseData.SATURN).moonGenerator(53);
		allPlanetsToSun.get(UniverseData.URANUS).moonGenerator(27);
		allPlanetsToSun.get(UniverseData.NEPTUN).moonGenerator(13);
		allPlanetsToSun.get(UniverseData.PLUTO).moonGenerator(5);*/

	}
	
	static final String[] planetNames                   = {   "Merkur",   "Venus",   "Jorden"
			                                   , "Mars",     "Jupiter", "Saturn"
			                                   , "Uranus"  , "Neptun",  "Pluto"};
	
	static final Color[] planetColor                    = {  Color.MAGENTA, Color.CYAN, Color.GREEN
			                                    ,Color.RED , Color.CYAN, Color.YELLOW
			                                    , Color.LIGHT_GRAY, Color.WHITE, Color.BLUE			
	};
	// ******************************* REAL DATA ************************************************
	// Meanc distance from the sun earth = 1 AU
	static final double[] meanDistanceFromSunAU           = {  5.39,	0.72,	1
			                                                 , 1.52,	5.20,	9.54
			                                                 ,19.18,	30.06,  39.44 };

	// Speed of the planets in km/s
	static final double[] planetSpeed                       = { 200.89,	35.03,	29.79
			                                                   ,24.13,	13.06,	9.64
			                                                   , 6.81,	 5.43,  4.74};
	
	// Planets size in radius
	static final double[] planetSize                        = { 6.982,	 0.949,	1
			                                                   ,0.532,	11.209,	9.44
			                                                   ,4.007,	 3.883, 0.180 };
	// ***********************************************************************************************
	// Debug Data
	static int[] planetsRadiusFromSunArrInAU_DEBUG = { 50, 100, 150, 200, 250, 300, 400, 500, 600 };
	static int[] planetSpeed_DEBUG                 = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };
	static int[] planetSize_DEBUG                  = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };	


	static final public void debug()
	{
		
	}
}
