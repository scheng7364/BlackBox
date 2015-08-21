/**
 * 
 */
package blackbox;

/**
 * @author kevinchilds
 *
 */
public class Car_TB {

	/**
	 * 
	 */
	public Car_TB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Car honda = new Car();
		
		double[] myBrakePressure = new double[100];
		honda.sysBrake.setBrakeLinePressureAVG(15);
		honda.sysBrake.setBrakeLinePressureSTD(5);
		for (int i=0; i<100; i++){
			myBrakePressure[i] = honda.sysBrake.getBrakeLinePressure();
		}
		GraphingData brakePressurePlot = new GraphingData(myBrakePressure);
		brakePressurePlot.getPlot(myBrakePressure);
		
		double[] myRPM = new double[50];
		honda.sysEngine.setRPM_AVG(3000);
		honda.sysEngine.setRPM_STD(1000);
		for (int i=0; i<50; i++){
			myRPM[i] = honda.sysEngine.getRPM();
		}
		GraphingData rpmPlot = new GraphingData(myRPM);
		rpmPlot.getPlot(myRPM);
		
		double[] myTire = new double[80];
		honda.sysTires.setTirePressureAVG(40);
		honda.sysTires.setTirePressureSTD(20);
		for (int i=0; i<80; i++){
			myTire[i] = honda.sysTires.getTirePressure();
		}
		GraphingData tirePlot = new GraphingData(myTire);
		tirePlot.getPlot(myTire);
		
		honda.setSpeedAVG(50);
		honda.setSpeedSTD(20);

		System.out.println("Max Speed is "+0.95*(honda.getSpeedAVG()+honda.getSpeedSTD()));
		System.out.println("Min Speed is "+0.95*(honda.getSpeedAVG()-honda.getSpeedSTD()));
	}

}
