package finalProjectTravel;

public class FullyBookedException extends Exception{
	public FullyBookedException(){

		super("This flight is fully booked. There is no more room for the passenger.");
	}
}
