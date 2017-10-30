package finalProjectTravel;

public class SeatNotAvailableException extends Exception{
	public SeatNotAvailableException(){
		super("Sorry, the seat is already taken.");
	}

}
