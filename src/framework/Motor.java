package framework;
import java.util.ArrayList;
import java.util.*;  
 

public class Motor{

public List<MotorHandler> motorlist = new ArrayList<MotorHandler>();
	
public Motor() {
	motorlist.add(new MotorHandler("775Pro", "Vex Pro", "217-4347", 0.8, 19.99, 18730, 0.7, 134, 0.71));
	motorlist.add(new MotorHandler("CIM", "Vex Pro", "217-2000", 2.8, 32.99, 5330, 2.7, 131, 2.41));
	motorlist.add(new MotorHandler("MINI_CIM", "Vex Pro", "217-3371", 2.16, 29.99, 5840, 3, 89, 1.41));
	motorlist.add(new MotorHandler("AM_775_Redline", "AndyMark", "am-0912", 0.80, 18.00, 21020, 3.8, 130, 0.7));
	motorlist.add(new MotorHandler("BAG Motor", "Vex Pro", "217-3351", 0.71, 29.99, 13810, 1.8, 53, 0.4));
	motorlist.add(new MotorHandler("BB RS-550", "Bane Bots", "M5-RS555-12", 0.47, 7.25, 19300, 1.4, 85, 0.488));
	motorlist.add(new MotorHandler("NEO", "Rev Robotics", "REV-21-1650", 0.94, 40.00, 5676, 1.8, 105, 2.6));
	motorlist.add(new MotorHandler("NEO_550", "Rev Robotics", "REV-21-1651", 0.313, 25.00, 11000.0, 1.4, 100.0, 0.97));
	motorlist.add(new MotorHandler("Falcon_500", "Vex Pro", "217-6515", 1.1, 139.99, 6380.0, 1.5, 257, 4.69));
	}
}
	

