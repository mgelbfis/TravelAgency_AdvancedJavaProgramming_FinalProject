package finalProjectTravel;

public class InvalidPinException extends Exception{
	public InvalidPinException(){
		super("That was an incorrect PIN. We cannot perform any transactions.");
	}
}
