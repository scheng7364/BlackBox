package blackbox;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CarFacade extends Thread {

	private Car myCar;
	private OBD2Port obdPort;
	
	//May need some Driver profile 

	//A HashMap	
	private HashMap<String, CarDataItem> carDataItemMap;
	private static int PERIOD = 1000;
	private boolean carStopped = true;
	
	// To set the car Stopped status from outside
	public boolean setCarStopped(boolean stoppedornot) {
		return carStopped = stoppedornot;
	}
	
	public boolean getCarStopped(){
		return this.carStopped;
	}
	public CarFacade() {
		myCar = new Honda();
		obdPort = new OBD2Port(this);
		carDataItemMap = new HashMap<String,CarDataItem>();
		//Create Map
		createDataMap();
	}

	private void createDataMap() {
		//1. Speed
		addMapItem("Speed", new CarDataItem() {public Double fetch(){return myCar.getSpeed();}});
		//2. RPM
		addMapItem("RPM", new CarDataItem() {public Double fetch(){return Math.floor(myCar.sysEngine.getRPM());}});
		//3. Fuel Level
		addMapItem("FuelLevel", new CarDataItem() {public Double fetch(){return myCar.sysFuel.getFuelLevel();}});
		//4. Internal Temperature
		addMapItem("IntAirTemp", new CarDataItem() {public Double fetch(){return myCar.sysCooling.getTemperature();}});
		//5. Oil Level
		addMapItem("OilLevel", new CarDataItem() {public Double fetch(){return myCar.sysEngine.getOilLevelSensor();}});
		//6. Tire Pressures
		addMapItem("TirePressure_LF", new CarDataItem() {public Double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_LR", new CarDataItem() {public Double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_RF", new CarDataItem() {public Double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_RR", new CarDataItem() {public Double fetch(){return myCar.sysTires.getTirePressure();}});		
		
	}
	
	private void addMapItem(String name, CarDataItem item) {
		carDataItemMap.put(name, item);
	}
	
	public double getDataValue(String key) {
		if(carDataItemMap.get(key)!=null) {
			return carDataItemMap.get(key).getCurrValue();
		} else {
			System.out.println("Warning: No" + key + "Data is Retrieved!");
			return 0.0;
		}
	}
	
	public String getDataStr(String key) {
		if(carDataItemMap.get(key)!=null) {
			return carDataItemMap.get(key).getCurrDataStr();
		} else {
			System.out.println("Warning: No" + key + "Data is Retrieved!");
			return "0";
		}
	}
	
	public void refreshDataMap() {
		for(Map.Entry<String, CarDataItem> entry : carDataItemMap.entrySet()) {
			//String key = entry.getKey();
			CarDataItem item = entry.getValue();
			item.updateItem();
		}		
	}
	
	// for debug only
	public void printDataMap() {
		for(Map.Entry<String, CarDataItem> entry : carDataItemMap.entrySet()) {
			String key = entry.getKey();
			CarDataItem item = entry.getValue();
			NumberFormat formatter = new DecimalFormat("#0.00"); 
			System.out.println(key+ "=" + formatter.format(item.getCurrValue()));
		}	
	}
	
	public void stopCar() {
		System.out.println("Car is Stopped!");
		carStopped = true;
	}
	
	public void startCar() {
		System.out.println("Car is Started!");
		carStopped = false;
	}
	
	@Override
	public synchronized void run() {
		try {
			for (;;){
				if(carStopped==false) {
					refreshDataMap();
					printDataMap(); // for debug only
					
				}
				sleep(PERIOD);
			}
		}
		catch (InterruptedException e) {
		      System.out.println(e);
	    }
	}

	//inner class
	abstract class CarDataItemBase<T> {
		public String name;
		public String currDataStr;
		public CarDataItemBase(String name) {
			this.name = name;
		}
		public CarDataItemBase(){
			this("");
		}
		public abstract T fetch();
		public String getCurrDataStr() {return currDataStr;}
	}
	
	abstract class CarDataItem extends CarDataItemBase<Double> {
		public String name;
		private double currValue;
		private double prevValue;
		private double peakValue=0.0;
		private double avgValue=0.0;
		public CarDataItem(String name) {super(name);}
		public CarDataItem(){this("");}
		public void updateItem() {
			prevValue=currValue;
			currValue=fetch().doubleValue();
			if(currValue>peakValue) peakValue = currValue;
			}
		public double getPrevValue() {return prevValue;}
		public double getCurrValue() {return currValue;}
		public String getCurrDataStr() {return Double.toString(currValue);}
		//public void print() {}
	}
		
	// Used for Part cards to get Car info
	public Car getCar(){
		return this.myCar;
	}

	public OBD2Port getObdPort() {
		return obdPort;
	}

	
	// for debug only
/*	public static void main(String [] args) {
		CarFacade myCar = new CarFacade();
		
		myCar.start();
		
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myCar.stopCar();
		
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myCar.startCar();
	}*/
}
