package blackbox;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class CarFacade extends Thread {

	Car myCar;
	//May need some Driver profile 

	//A HashMap	
	HashMap<String, CarDataItem> carDataItemMap;
	
	boolean carStopped = false;
	
	public CarFacade(Car carType) {
		myCar = carType;
		carDataItemMap = new HashMap<String,CarDataItem>();
		//Create Map
		createDataMap();
	}

	private void createDataMap() {
		//1. Speed
		addMapItem("Speed", new CarDataItem() {public double fetch(){return myCar.getSpeed();}});
		//2. RPM
		addMapItem("RPM", new CarDataItem() {public double fetch(){return myCar.sysEngine.getRPM();}});
		//3. Fuel Level
		addMapItem("FuelLevel", new CarDataItem() {public double fetch(){return myCar.sysFuel.getFuelLevel();}});
		//4. Internal Temperature
		addMapItem("IntTemp", new CarDataItem() {public double fetch(){return myCar.sysCooling.getTemperature();}});
		//5. Oil Level
		addMapItem("OilLevel", new CarDataItem() {public double fetch(){return myCar.sysEngine.getOilLevelSensor();}});
		//6. Tire Pressures
		addMapItem("TirePressure_LF", new CarDataItem() {public double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_LR", new CarDataItem() {public double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_RF", new CarDataItem() {public double fetch(){return myCar.sysTires.getTirePressure();}});
		addMapItem("TirePressure_RR", new CarDataItem() {public double fetch(){return myCar.sysTires.getTirePressure();}});		
		
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
			System.out.println(key+ "=" + item.getCurrValue());
		}	
	}
	
	public void stopCar() {
		System.out.println("Car is Stopped!");
		carStopped = true;
	}
	
	@Override
	public void run() {
		try {
			//for (;;){
			while(carStopped==false) {
				printDataMap(); // for debug only
				refreshDataMap();
				sleep(500);
			}
		}
		catch (InterruptedException e) {
		      System.out.println(e);
	    }
	}

	//inner class
	abstract class CarDataItem  {
		public String name;
		private double currValue;
		private double prevValue;
		public CarDataItem(String name) {
			this.name = name;
		}
		public CarDataItem(){
			this("");
		}
		public double fetch() {return currValue;}
		public void updateItem() {prevValue=currValue;currValue = fetch();}
		public double getPrevValue() {return prevValue;}
		public double getCurrValue() {return currValue;}
		public void print() {}
	}
			
	
	// for debug only
	public static void main(String [] args) {
		CarFacade myCar = new CarFacade(new Honda());
		
		myCar.start();
		
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myCar.stopCar();
	}
}
