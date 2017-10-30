package finalProjectTravel;

public class InsufficientFundsException extends Exception{
	public InsufficientFundsException(){
		super("There is not enough money in the account to make that charge.");
	}
}
