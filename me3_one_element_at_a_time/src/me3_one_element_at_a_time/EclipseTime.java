package me3_one_element_at_a_time;
public class EclipseTime {
	// default click = hour;
	private double ss_click;
    private double unitsPrClick = 0.000001;
    private double standardUnitPrClick = 0.000001;
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

}
