package framework;


public class InterfaceHandler {
	
	public String name;
	public String vendor;
	public String partnumber;
	public double price;
	public String interface_type_in;
	public String interface_type_out;
	public double ratiospread;
	public double weight_lbs;
	
	public InterfaceHandler(String name, String vendor, String partnumber,
			double price, String interface_type_in, String interface_type_out, double ratiospread, double weight_lbs) {
		this.name = name;
		this.vendor = vendor;
		this.partnumber = partnumber;
		this.price = price;
		this.interface_type_in = interface_type_in;
		this.interface_type_in = interface_type_out;
		this.ratiospread = ratiospread;
		this.weight_lbs = weight_lbs;
	}
	
	
	
}
