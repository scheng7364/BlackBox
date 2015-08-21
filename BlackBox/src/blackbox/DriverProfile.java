package blackbox;

enum DrivingStyle {
	CRAZY(1.4),
	AGGRESSIVE(1.2),
	AVERAGE(1.0),
	MILD(0.8);
	
	private final double Coefficient;
	
	DrivingStyle(double coef) {
		this.Coefficient = coef;
	}
	
	public double getCoef() {return Coefficient;}
}

enum Gender {
    MALE, FEMALE, UNKNOWN
}

public class DriverProfile {
	
	private String firstName;
	private String lastName;
	private Gender gender = Gender.UNKNOWN;
	private double ratingScore = 0;
	private DrivingStyle style = DrivingStyle.AVERAGE;

	public DriverProfile(String firstName, String lastName, DrivingStyle style, Gender gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.style = style;
		this.gender = gender;
	}

	public DriverProfile(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public double getCoeff() {return style.getCoef();}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getRatingScore() {
		return ratingScore;
	}

	public void setRatingScore(double ratingScore) {
		this.ratingScore = ratingScore;
	}

	public DrivingStyle getStyle() {
		return style;
	}

	public void setStyle(DrivingStyle style) {
		this.style = style;
	}
	
}
