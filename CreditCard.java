package finalProjectTravel;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CreditCard {
	
	private Integer creditCardID;
	private Double creditLimit;
	private Double balance;
	private LocalDate expirationDate;
	private Integer pin;
	
	public CreditCard(Integer ID, String expDate, Integer p)throws InvalidDataException, CardExpiredException{
		try{
			this.expirationDate = LocalDate.parse(expDate);
		}
		catch(DateTimeParseException e){
			throw new InvalidDataException("The date was not entered in the correct format.");
		}
		//even though in this application this takes away the need for the expired exception in addCharge,
		//because we use it in one day--there's no database
		//i put this in because we would have no reason to put an expired credit card on file initially
		
		if(expirationDate.isBefore(LocalDate.now())){
			throw new CardExpiredException();
		}
		if(ID==null||p==null){
			throw new InvalidDataException("The data you entered was invalid.");
		}
		this.creditCardID = ID;
		this.pin = p;
		this.balance = 0.00;
		this.creditLimit = 5000.00;
	}
	
	public void addCharge(Double amount, Integer p)throws InsufficientFundsException, InvalidPinException, CardExpiredException{
		if(!(this.pin.equals(p))){
			throw new InvalidPinException();
		}
		else if( amount > (this.creditLimit-this.balance) ){
			throw new InsufficientFundsException();
		}
		else if((this.expirationDate).isBefore(LocalDate.now())){
			throw new CardExpiredException();
		}
		else{
			this.balance = this.balance + amount;
		}
	}
	
	public String toString(){
		return ("\n\tCredit Card ID:" + this.creditCardID + "\n\tCredit Limit: " + this.creditLimit + "\n\tBalance: " + this.balance
				+ "\n\tExpiration Date: " + this.expirationDate);//not PIN because don't want to give it to customer
	}
	
	
	public Integer getPin(){
		return this.pin;
	}
	
	public int compareTo(CreditCard C){
		return (this.creditCardID).compareTo(C.creditCardID);
	}
	
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		else{
			if (o instanceof CreditCard){
				CreditCard C = (CreditCard)o;
				return ((this.creditCardID).compareTo(C.creditCardID))==0;
			}
			else{
				return false;
			}
		}
	}
	
	/**public static void main(String []args){
		try{
			CreditCard A = new CreditCard(1524869, "2017-12-30", 5689);
			System.out.println(A.toString());
			
			
			System.out.println(A.getPin());
			
			A.addCharge(15.0,5689);
			
			
			
			System.out.println(A.toString());
			
			CreditCard B = new CreditCard(152486, "2016-06-10", 5689);
			
			CreditCard C = null;
			
			System.out.println(B.compareTo(A));
			System.out.println(A.compareTo(B));
			
			System.out.println(B.equals(C));
			
			A.addCharge(4975.0, 5689);
			System.out.println(A.toString());
			
			//A.addCharge(11.0, 5689);
			B.addCharge(5001.0, 5689);
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
		catch(CardExpiredException e){
			System.out.println(e.getMessage());
		}
		
		catch(InvalidPinException e){
			System.out.println(e.getMessage());
		}
		catch(InsufficientFundsException e){
			System.out.println(e.getMessage());
		}

		
	
	}
		
	*/	
		
}
