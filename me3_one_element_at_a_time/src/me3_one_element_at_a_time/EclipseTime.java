package me3_one_element_at_a_time;

import java.awt.Color;
import java.awt.Graphics2D;

public class EclipseTime {
	// default click = hour;
	private double ss_click;
    private double unitsPrClick        = 0.001;
    private double standardUnitPrClick = 0.001;
    int linje = 20;
    int indryk = 20;
    
    public void newLine() {
    	linje += 20;
    }
	public EclipseTime() {
		ss_click = 0;
	}

	public void click() {
		clickHour();
	}

	public void clickHour() {
		ss_click += unitsPrClick;
	}

	public double getSSClick() {
		return ss_click;
	}

	public void setUnitPrClick(double i)
	{
		this.unitsPrClick = i;
	}
	
	public void setUnitPrClickToStandard(){
		this.unitsPrClick = standardUnitPrClick;
	}
	
	public void reset(){
		ss_click = 0;
	}
	
	public void draw(Graphics2D g){
		g.setColor(Color.white);
		g.drawString("EclipseTime :", indryk, linje);
		g.drawString("Clicks : " + ss_click, indryk, linje * 2);
		g.drawString("Key (*)   = Init Unitverse", indryk, linje * 3);
		g.drawString("Key (r)   = Rays To Moons on", indryk, linje * 4);
		g.drawString("Key (R)   = Rays To Moons off", indryk, linje * 5);
		g.drawString("Key (+)   = Speed +", indryk, linje * 6);
		g.drawString("Key (-)   = Speed", indryk, linje * 7);
		g.drawString("Key (P)   = Pause", indryk, linje * 8);
		g.drawString("ArrowKeys = move", indryk, linje * 9);
		g.drawString("Mouse click Left/Right = zoom in/out", indryk, linje * 10);
		g.drawString("Key (m/M) = Moons on/off", indryk, linje * 11);
		

	}

}
