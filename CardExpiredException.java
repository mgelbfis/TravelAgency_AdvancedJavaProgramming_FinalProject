package finalProjectTravel;

public class CardExpiredException extends Exception{
	public CardExpiredException(){
		super("This card has expired. We will not be able to perform any transactions.");
	}
}
