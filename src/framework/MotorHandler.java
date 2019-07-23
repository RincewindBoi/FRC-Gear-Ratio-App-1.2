package framework;
public class MotorHandler {
	
	public String name;
	public String vendor;
	public String partnumber;
	public double weight_lbs;
	public double price;
	public double freespeed_rpm;
	public double freecurrent_a;
	public double stallcurrent_a;
	public double stalltorque_nm;
	
	
	public MotorHandler(String name, String vendor, String partnumber, double weight_lbs, double price, double freespeed_rpm, double freecurrent_a,
			double stallcurrent_a, double stalltorque_nm){
		this.name = name;
		this.vendor = vendor;
		this.partnumber = partnumber;
		this.weight_lbs = weight_lbs;
		this.price = price;
		this.freespeed_rpm = freespeed_rpm;
		this.freecurrent_a = freecurrent_a;
		this.stallcurrent_a = stallcurrent_a;
		this.stalltorque_nm = stalltorque_nm;
		
	}

}
