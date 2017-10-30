package finalProjectTravel;

public class Person {
	private Integer PersonID;
	private Address address;
	private String FirstName;
	private String LastName;
	private char gender;
	private String PhoneNumber;
	
	private static Integer LastPersonID =0;
	//didn't pass the ID from each inheriting class because then two people from two different classes will have the same ID and be considered equal
	
	public Person(String fName, String lName, Address add, String pNum, char gen)throws InvalidDataException, InvalidLocationException{
		if(fName==null||lName==null||pNum==null){
			throw new InvalidDataException("The date entered for this person was invalid.");
		}
		if(add==null){
			throw new InvalidDataException("The address entered was not a valid address.");
		}
		if(gen!='M'&&gen!='F'&&gen!='m'&&gen!='f'){
			throw new InvalidDataException("The gender entered was invalid. Enter M or F.");
		}
		this.PersonID = ++LastPersonID;
		//this is for the compareTo and equals methods which do not have any significance when the application is used as we made it but could
		//have use in the future (there would have to be a person copy constructor to have 2 with the same ID or companies would have to merge)
		
		this.address = new Address(add);
		this.FirstName = fName;
		this.LastName = lName;
		this.gender = gen;
		this.PhoneNumber = pNum;
	}
	
	public Person(String fName, String lName, String st, String city, String state, String zip,  String pNum, char gen)
	throws InvalidLocationException, InvalidDataException{
		this(fName, lName, new Address(st, city, state, zip), pNum, gen);
	}
	
	public Address getAddress()throws InvalidDataException, InvalidLocationException{
		return new Address(this.address);
	}
	
	public String getFirstName(){
		return this.FirstName;
	}
	
	public String getLastName(){
		return this.LastName;
	}
	
	public String getPhoneNumber(){
		return this.PhoneNumber;
	}
	
	public char getGender(){
		return this.gender;
	}
	
	public Integer getPersonID(){
		return this.PersonID;
	}
	
	public void setAddress(String st, String city, String state, String zip)throws InvalidLocationException, InvalidDataException{
			this.address = new Address(st, city, state, zip);
		
	}
	
	public void setLastName(String lName)throws InvalidDataException{
		if(lName==null){throw new InvalidDataException("That was not a valid last name.");}
		this.LastName = lName;
	}
	
	public void setPhoneNumber(String pNum)
		throws InvalidDataException{
			if(pNum==null){throw new InvalidDataException("That was not a valid phone number.");}
		this.PhoneNumber = pNum;
	}
	
	public void setStreet(String st, String zip)throws InvalidDataException{
		this.address.setStreet(st);
		this.address.setZipcode(zip);
	}
	
	public String toString(){
		return ("First Name: " + this.FirstName + "\nLastName: " + this.LastName + "\nGender: " + this.gender + 
				"\nAddress: " + (this.address).toString() + "\nPhone Number: " + this.PhoneNumber);
	}
	
	public int compareTo(Person P){
		return (this.PersonID).compareTo(P.PersonID);
	}
	
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		else {
			if(o instanceof Person){
				Person P = (Person)o;
				return ((this.PersonID).compareTo(P.PersonID))==0;
			}
			else{
				return false;
			}
		}
	}
	
	//main for unit test
	/**public static void main(String []args){
		
		try{
			Address A = new Address("907 Ave N", "Brooklyn", "Colorado", "08701");
		
			Person P = new Person("Malka", "Breindel", A, "3476384683", 'w');
			
			Person Q = new Person("Malka", "Breindel", "907 Ave N", "Brooklyn", "Colorado", "08701", "3476384683", 'w');
			//same info be not going to be equal because perID different
			Person R = null;
			System.out.println(Q.compareTo(P) + " " + P.equals(R));
			
			System.out.println(P.toString()+P.getFirstName()+P.getGender()+P.getLastName()+P.getPhoneNumber()+P.getAddress()+P.getPersonID());
			
			P.setAddress("4930 W 17th Ave", "Denver", "Colorado", "08701");
			System.out.println(P.getAddress().toString());
			
			Q.setLastName("Unger");
			Q.setPhoneNumber("3038250070");
			Q.setStreet("Yates", "08702");
			System.out.println(Q.toString());
			
			
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
