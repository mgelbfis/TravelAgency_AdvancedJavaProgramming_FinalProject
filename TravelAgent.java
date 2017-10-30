package finalProjectTravel;

public class TravelAgent extends Person{
	private Integer EmployeeID;
	private Double Earnings;
	private Integer SSNumber;
	
	private static Double commissionRate=0.0;//just so that if someone books a ticket w/o setting the commission rate it doesn't bomb
	
	public TravelAgent(Integer empID, String SSN, String fName, String lName,
			char gen, String st, String ci, String sta, String zip, String pNum)throws InvalidDataException, InvalidLocationException{
		super(fName, lName, st, ci, sta, zip, pNum, gen);
		this.EmployeeID = empID;
		this.Earnings = 0.00;
		this.SSNumber = Integer.parseInt(SSN);//will have to check for valid SSN in main because can't check before the super
	}
	
	public static void setCommissionRate(Double rate){
		commissionRate = rate;//could technically be 0
	}
	
	public void bookTicket(Double ticketPrice){
		this.Earnings += (ticketPrice * commissionRate);//nothing will happen if 0 is entered
	}
	
	
	public String toString(){
		return ("\nTravel Agent:\n" + super.toString() + "\nEmployee ID: " + this.EmployeeID 
				+ "\nEarnings: " + this.Earnings + "\nSSN: " + this.SSNumber);
	}
	
	public int compareTo(TravelAgent T){
		return (this.EmployeeID).compareTo(T.EmployeeID);
	}
	
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		else{
			if(o instanceof TravelAgent){
				TravelAgent T = (TravelAgent)o;
				return ((this.EmployeeID).compareTo(T.EmployeeID))==0;
			}
			else{
				return false;
			}
		}
	}
	
	public Integer getEmployeeID(){
		return this.EmployeeID;
	}
	
	public Double getEarnings(){
		return this.Earnings;
	}
	
	public String getSSNumber(){
		return (this.SSNumber).toString();
	}

	//main to do unit test
	/**public static void main(String []args){
		try{
			TravelAgent T = new TravelAgent(186548, "15468463", "B", "G", 'F', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			System.out.println(T.toString());
			TravelAgent.setCommissionRate(0.25);
			T.bookTicket(100.0);
			TravelAgent U = new TravelAgent(185548, "15468463", "B", "G", 'F', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			System.out.println(T.toString() + U.toString() + T.compareTo(U) + T.equals(U) + T.getEarnings() + T.getSSNumber());
			
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
