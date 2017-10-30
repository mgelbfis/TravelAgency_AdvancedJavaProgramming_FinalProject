package finalProjectTravel;

public class Address {
	
	private String Street;
	private String City;
	private USState State;
	private String Zipcode;
	
	public Address(Address A)throws InvalidDataException, InvalidLocationException{//should never actually throw an InvalidLocationException- 
		//but if using this have to put it in
		//assuming can't give a null address--no way for the user to put that in
		this( A.getStreet(), A.getCity(), A.getState(), A.getZipcode());
		
	}
	
	public Address(String St, String Cit, String Sta, String Zip)throws InvalidLocationException, InvalidDataException
	{
		if(St==null||Cit==null||Sta==null||Zip==null){
			throw new InvalidDataException("The data you entered was invalid.");
		}
		this.Street = St;
		this.City = Cit;
	
		try{
			this.State = USState.valueOf(Sta.toUpperCase().replaceAll("\\s", ""));
		}
		catch(IllegalArgumentException  e){
			throw new InvalidLocationException(Sta);
		}
		this.Zipcode = Zip;
	}
	
	public String getCity(){
		return this.City;
	}
	
	public String getState(){
		return this.State.name();
	}
	
	public String getZipcode(){
		return this.Zipcode;
	}
	
	public String getStreet(){
		return this.Street;
	}
	
	public void setCity(String Cit)throws InvalidDataException{
		if(Cit==null){
			throw new InvalidDataException("The data entered for the new city was invalid.");
		}
		this.City = Cit;
	}
	
	public void setState(String Sta)throws InvalidLocationException{
		try{
		this.State = USState.valueOf(Sta.toUpperCase().replaceAll("\\s", ""));
		}
		catch(IllegalArgumentException e){
			throw new InvalidLocationException(Sta);
		}
	}
	
	public void setStreet(String St)throws InvalidDataException{
		if(St==null){
			throw new InvalidDataException("The data entered for the new street was invalid.");
		}
		this.Street = St;
	}
	
	public void setZipcode(String Zip)throws InvalidDataException{
		if(Zip==null){
			throw new InvalidDataException("The data entered for the new zipcode was invalid.");
		}
		this.Zipcode = Zip;
	}
	
	public String toString(){
		return ("\n\t" + this.Street + "\n\t" + this.City + ", " + this.State.getSymbol() + " " + this.Zipcode);
	}
	
	//to check if the state entered is indeed an enumerated type
	//not necessary with the try/catch
	/**private boolean checkState(String Sta){
		boolean exists = false;
		for(USState s: USState.values()){
			if((Sta.toUpperCase().replaceAll("\\s", "")).equals(s.name())){
				exists = true;
				return exists;
			}
		}
		return exists;
	}
	*/
	
	//main to do a unit test
	/**public static void main(String []args){
		
		try{
			Address A = new Address("907 East 9th Street", "Brooklyn","New York", "11230" );
			Address B = new Address(A);
			
			System.out.println(A.toString() + "\n" + B.toString() + "\n"  + A.getCity() + A.getState() + A.getStreet() + A.getZipcode());
			
			A.setCity("New Port");
			A.setStreet("70 7th Street");
			A.setZipcode("22310");
			A.setState("RhodeInland");
			
			System.out.println(A.toString());
		}
		
		catch(InvalidDataException e1){
			System.out.println(e1.getMessage());
		}
		
		catch(InvalidLocationException e1){
			System.out.println(e1.getMessage());
		}
		
		
	}
	*/
}
