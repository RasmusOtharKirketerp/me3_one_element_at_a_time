package me3_one_element_at_a_time;

public class Planet_Tester  {
	static LokalKoordinatsystem aScreen = new LokalKoordinatsystem(GetScreenWorkingWidth(), GetScreenWorkingHeight());
	static EclipseTime ec = new EclipseTime();
	static int analyse = 2;
	static boolean use_real_values = true;

	static UniverseDataTestPlanet ud = new UniverseDataTestPlanet(false);

	public static int GetScreenWorkingWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	public static int GetScreenWorkingHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}

	public Planet_Tester() {
	}

	static public void initUniverse() {
		for (int nyPlanetCounter = 0; nyPlanetCounter < UniverseDataTestPlanet.MAX_PLANETS; nyPlanetCounter++) {
			ud.allPlanetsToSun.get(nyPlanetCounter).centerX = aScreen.relX(0);
			ud.allPlanetsToSun.get(nyPlanetCounter).centerY = aScreen.relY(0);
		}
		ec.reset();

	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public static void main(String[] args) throws InterruptedException {
		initUniverse();
		while (true) {
			ec.click();
			for (Planet planet : ud.allPlanetsToSun) {
//				planet.calcPlanet(ec);
				planet.beregnPlanetensGradIKredsløbet(ec.getSSClick());
				planet.getPlanetX(planet.vinkelFraCenterTilPlanet);
				planet.getPlanetY(planet.vinkelFraCenterTilPlanet);

				System.out.println(planet.name + ": "
				         + " Vinkel : " + round(planet.vinkelFraCenterTilPlanet,1)   
						 + " x,y : " + round(planet.eX,2) + " " + round(planet.eY,2)
				            
						
						
						);
			}
			Thread.sleep(2000);
			
		}

	}

}
