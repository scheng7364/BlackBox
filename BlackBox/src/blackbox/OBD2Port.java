package blackbox;

/*
 * A simplified OBD2 Port Class
 */
public class OBD2Port {

	CarFacade myCar;
	
	public OBD2Port(CarFacade car) {
		myCar = car;
	}
	
	public double readDoubleData(String name) {
		return myCar.getDataValue(name);	
	}
	
	
	public String readDataStr(String name) {
		return myCar.getDataStr(name);
	}
	
}
