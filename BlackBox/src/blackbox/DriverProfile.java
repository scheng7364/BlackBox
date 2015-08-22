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
	
	private String username;
	private String firstName;
	private String lastName;
	private Gender gender = Gender.UNKNOWN;
	private double ratingScore = 0;
	private DrivingStyle style = DrivingStyle.AVERAGE;

	public DriverProfile(String user, DrivingStyle style, Gender gender) {
		super();
		this.setUsername(user);
		this.style = style;
		this.gender = gender;
	}

	public DriverProfile(String username, String style) {
		super();
		this.username = username;
		this.style = DrivingStyle.valueOf(style);
	}
	
	public DriverProfile() {
		super();
	}
	
	public double getCoeff() {
		
		return style.getCoef();}

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
	//	System.out.println(style);
		return style;
	}

	public void setStyle(DrivingStyle style) {
		this.style = style;
	}

	public void setStyle(String style) {
	//	System.out.println(style);
		this.style = DrivingStyle.valueOf(style);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
