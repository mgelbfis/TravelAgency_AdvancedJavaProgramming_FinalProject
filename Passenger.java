package finalProjectTravel;

public class Passenger extends Person{
	
	private Passport passport;
	private Integer passengerID;
	
	public Passenger(Passenger P)throws InvalidDataException, InvalidLocationException{//won't throw invalid location exception but needs it
		//there is no way to create a null passenger and in this program the IDs will not be null--they come from the agency static variables
		//can't use a this because passenger constructors accept data for address not address itself
		this(P.getPassengerID(), P.getFirstName(), P.getLastName(), P.getGender(), P.getAddress().getStreet(),
				P.getAddress().getCity(),P.getAddress().getState(), P.getAddress().getZipcode(), P.getPhoneNumber(), P.getPassport());
	}
	
	public Passenger(Integer ID, String fName, String lName, char gen,
			String st, String ci, String sta, String zip, String pNum, Passport passp)throws InvalidLocationException, InvalidDataException{
		super(fName, lName, new Address(st, ci, sta, zip), pNum, gen);
		this.passport = passp;//allowed to be null
		this.passengerID = ID;//ID is always provided by static variable in agency so will not be null
	}
	
	
	public Passenger(Integer ID, String fName, String lName, char gen, String st,
			String ci, String sta, String zip, String pNum)throws InvalidLocationException, InvalidDataException{
		this(ID, fName, lName, gen, st, ci, sta, zip, pNum, null);
	}
	
	public boolean hasPassport(){
		if(this.passport == null){
			return false;
		}
		else if(this.passport.isExpired()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public void setPassport(Passport p){
		this.passport = p;
	}
	
	public Passport getPassport() throws InvalidDataException{
		return new Passport(this.passport);
	}
	
	public Integer getPassengerID(){
		return this.passengerID;
	}
	
	public String toString(){
		String end;
		if(this.hasPassport()){
			end = this.passport.toString();
		}
		else{
			end = "No Passport";
		}
		return ("\n\nPassenger: " + super.toString() + "\nPassenger ID: " + this.passengerID + "\nPassport: " + end + "\n");
	}
	
	public int compareTo(Passenger P){
		return (this.passengerID).compareTo(P.passengerID);
	}
	
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		else{
			if(o instanceof Passenger){
				Passenger P = (Passenger)o;
				return ((this.passengerID).compareTo(P.passengerID)==0);
			}
			else{
				return false;
			}
		}
	}
	
	/**public static void main(String []args){
		try{
		Passport Pa = new Passport(489865468, "Malka", "Ge", "2015-05-18", "USA", "2015-05-18", "2018-01-18", "American");
		Passenger P = new Passenger(26595, "M", "B", 'F', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253", Pa);
		Passenger Q = new Passenger(P);
		Passenger R = new Passenger(25, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
		System.out.println(P.toString() + Q.toString() + R.toString());
		
		System.out.println(P.hasPassport());
		P.setPassport(null);
		System.out.println(P.hasPassport());
		
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
		catch(InvalidLocationException e){
			System.out.println(e.getMessage());
		}
	}
	*/
}
