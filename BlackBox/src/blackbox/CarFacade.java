package blackbox;

import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CarFacade extends Thread {

	Car myCar;
	OBD2Port obdPort;
	//May need some Driver profile 

	//A HashMap	
	HashMap<String, CarDataItem> carDataItemMap;
	
	boolean carStopped = false;
	
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
		addMapItem("RPM", new CarDataItem() {public Double fetch(){return myCar.sysEngine.getRPM();}});
		//3. Fuel Level
		addMapItem("FuelLevel", new CarDataItem() {public Double fetch(){return myCar.sysFuel.getFuelLevel();}});
		//4. Internal Temperature
		addMapItem("IntTemp", new CarDataItem() {public Double fetch(){return myCar.sysCooling.getTemperature();}});
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
	public void run() {
		try {
			for (;;){
				if(carStopped==false) {
					printDataMap(); // for debug only
					refreshDataMap();
				}
				sleep(500);
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
		//public String prevDataStr;
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
		public CarDataItem(String name) {
			super(name);
		}
		public CarDataItem(){
			this("");
		}
		public void updateItem() {
			prevValue=currValue;
			currValue=fetch().doubleValue();
			currDataStr=Double.toString(currValue);}
		public double getPrevValue() {return prevValue;}
		public double getCurrValue() {return currValue;}
		//public void print() {}
	}
			
	
	// for debug only
	public static void main(String [] args) {
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
	}
}
