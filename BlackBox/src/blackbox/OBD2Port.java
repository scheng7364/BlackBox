package blackbox;

/*
 * A simplified OBD2 Port Class
 */
public class OBD2Port {

	private DriverProfile driver = new DriverProfile(null, DrivingStyle.AVERAGE, Gender.UNKNOWN);
	
	CarFacade myCar;
	
	public OBD2Port(CarFacade car) {
		myCar = car;
	}
	
	public double readDoubleData(String name) {
		return myCar.getDataValue(name) * driver.getCoeff();	
	}
	
	
	public String readDataStr(String name) {
		return myCar.getDataStr(name);
	}
	
	public double readAvgDoubleData(String name) {
		return myCar.getAvgDataValue(name) * driver.getCoeff();
	}
}
