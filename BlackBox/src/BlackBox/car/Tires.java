package BlackBox.car;

/**
 * 
 */

import java.util.Random;

/**
 * @author kevinchilds
 *
 */
public class Tires {
	private double tirePressureAVG;
	private double tirePressureSTD;
	private String modelNumber;
	private String brandName;
	private String serviceDate;

	/**
	 * 
	 */
	public Tires() {
		tirePressureAVG = 0;
		tirePressureSTD = 0;
		modelNumber = "";
		brandName = "";
		serviceDate = "";
		
	}
	
	/**
	 * 
	 * @param pressureAVG
	 * @param pressureSTD
	 * @param modelNum
	 * @param brand
	 * @param date
	 */
	public Tires(double pressureAVG, double pressureSTD, String modelNum, String brand, String date) {
		this.tirePressureAVG = pressureAVG;
		this.tirePressureSTD = pressureSTD;
		this.modelNumber = modelNum;
		this.brandName = brand;
		this.serviceDate = date;
		
	}

	/**
	 * @param tirePressureAVG the tirePressureAVG to set
	 */
	public void setTirePressureAVG(double tirePressureAVG) {
		this.tirePressureAVG = tirePressureAVG;
	}

	/**
	 * @param tirePressureSTD the tirePressureSTD to set
	 */
	public void setTirePressureSTD(double tirePressureSTD) {
		this.tirePressureSTD = tirePressureSTD;
	}
	
	/**
	 * @return the tirePressure
	 */
	public double getTirePressure() {
		Random randomGenerator = new Random();
		return this.tirePressureSTD*randomGenerator.nextDouble() + 
				this.tirePressureAVG;
	}
	/**
	 * @return the modelNumber
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/**
	 * @param modelNumber the modelNumber to set
	 */
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * @return the serviceDate
	 */
	public String getServiceDate() {
		return serviceDate;
	}

	/**
	 * @param serviceDate the serviceDate to set
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	

}
