package finalProjectTravel;

public class InvalidLocationException extends Exception{
	public InvalidLocationException(String input){
		super(input + " is not a valid state.");
	}
}
