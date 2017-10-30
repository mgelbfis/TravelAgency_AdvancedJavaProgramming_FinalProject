package finalProjectTravel;

public class Customer extends Person{
	
	private Integer CustomerID;
	private CreditCard CreditCard;
	
	public Customer(Customer C)throws InvalidDataException, InvalidLocationException{
		//there is no way that I know to check for a null customer if I use the super constructor
		//--but really there is no way to make a null customer in my interface
		this(C.getCustomerID(), C.getCreditCard(), C.getFirstName(), C.getLastName(), C.getPhoneNumber() , C.getGender(), C.getAddress());
		
	}
	
	public Customer(Integer ID, CreditCard cc, String fName, String lName, String pNum, char gen, Address add)throws InvalidDataException, 
	InvalidLocationException
	{
		super(fName, lName, add, pNum, gen);
		this.CustomerID = ID;
		if(cc==null){
			throw new InvalidDataException("The object given for this customer's credit card was not a valid credit card.");
			}
		else{
			this.CreditCard = cc;
			//doesn't check if Credit Card expired because there will have to be a credit card created before the customer is and the cc class will 
			//take care of this check--we don't have a file of credit cards to just choose from
		}
	}
	
	public Customer(Integer ID, CreditCard cc, String fName, String lName, String pNum, char gen, String St, String Ci, String Sta, String Zip)
	throws InvalidLocationException, InvalidDataException{
			
				this(ID, cc, fName, lName, pNum, gen, 
						new Address(St, Ci, Sta, Zip)); 
					
			
	}
	
	public void chargeCard(Double amount)throws InsufficientFundsException, CardExpiredException, InvalidPinException{
	
		this.CreditCard.addCharge(amount, (this.CreditCard.getPin()));
		
	}
	
	public int compareTo(Customer C){
		return (this.CustomerID).compareTo(C.CustomerID);
	}
	
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
		else{
			if(o instanceof Customer){
				Customer C = (Customer)o;
				return (this.CustomerID.compareTo(C.CustomerID))==0;
			}
			else{
				return false;
			}
		}
	}
	
	public String toString(){
		return ("\n\nCustomer:\n" + super.toString() + "\nCustomer ID: " + this.CustomerID + "\nCredit Card" + this.CreditCard.toString()+"\n\n");
	}
	
	public Integer getCustomerID(){
		return this.CustomerID;
	}
	
	public CreditCard getCreditCard(){
		return this.CreditCard;
	}
	
	//main for unit test
	/**public static void main(String []args){
		
		try{
			CreditCard CC = new CreditCard(68956656, "2017-05-08", 6895);
			Address Ad = new Address("49 South Clover St.", "Lakewood", "New jersey", "08701");
			Customer C = new Customer(58749, CC, "Malka", "Gelbfish", "718-589-8694", 'O', Ad);
			
			System.out.println(C.toString());
			
			Customer A = null;
			Customer D = new Customer(C);
			Customer E = new Customer(25485, CC, "Meir", "Tovey", "917-972-4516", 'M', "49 Idaho St", "Passaic", "NewJersey", "24589");
			
			System.out.println(C.toString() + "\n" + D.toString() + "\n" + E.toString());
			
			System.out.println(D.compareTo(C));
			System.out.println(D.equals(A));
			
			C.chargeCard(600.00);
			System.out.println(C.toString());
			D.chargeCard(4401.00);
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
		catch(InvalidLocationException e){
			System.out.println(e.getMessage());;
		}
		catch(CardExpiredException e){
			System.out.println(e.getMessage());
		}
		catch(InsufficientFundsException e){
			System.out.println(e.getMessage());
		}
		//this class should not ever throw the following exception because it is getting the pin from the getPin method
		catch(InvalidPinException e){
			System.out.println(e.getMessage());
		}
	}
	*/
}
