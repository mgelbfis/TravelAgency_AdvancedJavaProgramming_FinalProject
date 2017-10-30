package finalProjectTravel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Passport {
	private Integer PassportID;
	private String FirstName;
	private String LastName;
	private LocalDate BirthDate;
	private String BirthPlace;
	private LocalDate IssueDate;
	private LocalDate ExpirationDate;
	private String Nationality;
	
	public Passport(Passport P)throws InvalidDataException{
		//assuming having modularized code is more important than not calling all these toStrings to convert dates back to strings
		this( P.getPassportID(), P.getFirstName(), P.getLastName(), P.getBirthDate().toString(),
				P.getBirthPlace(),P.getIssueDate().toString(),P.getExpirationDate().toString(),
				P.getNationality());
	}
	
	
	public Passport(Integer ID, String fName, String lName, String bDate, 
			String bPlace, String iDate, String expDate, String Nat)throws InvalidDataException
	{
		if(fName==null||lName==null||bDate==null||bPlace==null||iDate==null||expDate==null||Nat==null){
			throw new InvalidDataException("The data entered was invalid.");
		}
		try{if((LocalDate.parse(expDate)).isBefore(LocalDate.parse(iDate))){
			throw new InvalidDataException("The expiration date entered is before the issue date entered. Could not process.");
		}
		else if(LocalDate.parse(bDate).isAfter(LocalDate.now())){
			throw new InvalidDataException("The birthdate entered is in the future. Could not process.");
		}
		else if((LocalDate.parse(bDate)).isAfter(LocalDate.parse(iDate))){
			throw new InvalidDataException("The birthdate entered is after the initialization date entered. Could not process.");
		}
		//does not check if already expired because might be reasons to put an expired passport on file (until get new one)
		else{
			this.BirthDate = LocalDate.parse(bDate);
			this.IssueDate = LocalDate.parse(iDate);
			this.ExpirationDate = LocalDate.parse(expDate);
		}}
		catch(DateTimeParseException e){
			throw new InvalidDataException("One or more dates were not entered in the correct format");
		}
		this.PassportID = ID;
		this.FirstName = fName;
		this.LastName = lName;
		this.BirthPlace = bPlace;
		this.Nationality = Nat;
	}
	
	public LocalDate getExpirationDate(){
		return this.ExpirationDate;
	}
	
	public Integer getPassportID(){
		return this.PassportID;
	}
	public String getFirstName(){
		return this.FirstName;
	}
	
	public String getLastName(){
		return this.LastName;
	}
	
	public String getName(){
		return (this.FirstName + " " + this.LastName);
	}
	
	public String getNationality(){
		return this.Nationality;
	}
	
	public LocalDate getBirthDate() {
		return this.BirthDate;
	}

	public String getBirthPlace() {
		return this.BirthPlace;
	}

	public LocalDate getIssueDate() {
		return this.IssueDate;
	}

	
	public boolean isExpired(){
		boolean expired = false;
		if((this.ExpirationDate).isBefore(LocalDate.now())){
			expired = true;
		}
		return expired;
	}
	
	public String toString(){
		return ("\n\tFirst Name: " + this.FirstName + "\n\tLastName: " + this.LastName + "\n\tPassport ID: " + this.PassportID
				+ "\n\tBirthDate: " + this.BirthDate + "\n\tBirthPlace: " + this.BirthPlace + "\n\tNationality: " + this.Nationality
				+ "\n\tIssue Date: " + this.IssueDate + "\n\tExpiration Date: " + this.ExpirationDate);
	}
	
	public int compareTo(Passport P){
		int nat;
		nat = this.Nationality.compareTo(P.Nationality);
		if(nat == 0){
			return this.PassportID.compareTo(P.PassportID);
		}
		else{
			return nat;
		}
		
	}

	public boolean equals(Object o) {
		if (o == null){
			return false;
		}
		else{
			
			if(o instanceof Passport){
				Passport P = (Passport)o;
				if ((this.Nationality).compareTo(P.Nationality)!=0){
					return false;
				}
				else if((this.PassportID).compareTo(P.PassportID)!=0){
					return false;
				}
				else{
					return true;
				}
			}
			else{
				return false;
			}
			
		}
	}
	
	/**public static void main(String []args){
		try{
			Passport P = new Passport(489865468, "Malka", "Ge", "2015-11-05", "USA", "2015-05-18", "2016-01-18", "American");
			Passport Q = new Passport(P);
			 
			System.out.println(P.toString() + Q.toString());
			
			System.out.println(P.isExpired());
			System.out.println(P.compareTo(Q));
			System.out.println(P.equals(Q));
			
		
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
	}
	*/
}
