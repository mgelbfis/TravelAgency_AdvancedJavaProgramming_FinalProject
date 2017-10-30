package finalProjectTravel;

public class Seat {
	private boolean available;
	private Passenger passenger;
	private SeatType type;
	private Integer seatID;
	
	private static Integer ID = 0;
	
	public Seat(String t)throws InvalidDataException{
		if(t==null){throw new InvalidDataException("The seat type entered was a null value.");}
		try{this.type = SeatType.valueOf((t.toUpperCase()).replaceAll("\\s", ""));}
		catch(IllegalArgumentException e){throw new InvalidDataException("The seat type entered was invalid.");}
		this.available = true;
		this.passenger = null;
		this.seatID = ++ID;
	}

	
	public void bookSeat(Passenger p)throws SeatNotAvailableException, InvalidDataException{
		if(p==null){throw new InvalidDataException("The passenger entered for the seat was not a valid passenger.");}
		if(isAvailable()){
			this.passenger = p;
			this.available = false;
		}
		else {
			throw new SeatNotAvailableException();
		}
	}
	
	public void cancelReservation(Passenger P)throws InvalidDataException {
		if(isAvailable()||(!this.passenger.equals(P))){
			throw new InvalidDataException("Error: Either the seat is not booked or the"
					+ " passenger whose seat you wish to cancel is not the one in this seat.");
		}
		else{
			this.available = true;
			this.passenger = null;
		}
	}
	
	public SeatType getType(){
		return this.type;
	}
	
	public Passenger getPassenger(){
		return this.passenger;
	}
	
	public boolean isAvailable(){
		return this.available;
	}
	
	public void setAvailable(boolean avail){
		this.available = avail;
	}
	
	public String toString(){
		String psngr;
		if(passenger == null){ 
			psngr = ("empty, no passenger");
		}
		else{
			psngr = (this.passenger).toString();
		}
	
		return ("\nSeat: " + "\nAvailable: " + this.available + "\nPassenger: " + psngr +
				  "\nSeat-Type: " + this.type);
	}
	
	public int compareTo(Seat S){
		return (this.seatID).compareTo(S.seatID);
	}
	
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		else{
			if(o instanceof Seat){
				Seat S = (Seat)o;
				return ((this.seatID).compareTo(S.seatID)==0);
			}
			else{
				return false;
			}
		}
	}
	
/**	public static void main(String []args){
		try{
			Seat S = new Seat("first class");
			System.out.println(S.toString());
			Passenger P = new Passenger(25, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger O = null;
			S.bookSeat(P);
			System.out.println(S.toString());
			Passenger F = new Passenger(27, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			S.cancelReservation(F);
			System.out.println(S.toString());
			Seat T = new Seat("economy");
			System.out.println(S.compareTo(T) + " " + S.equals(T));
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
		catch(SeatNotAvailableException e){
			System.out.println(e.getMessage());
		}
		catch(InvalidLocationException e){
			System.out.println(e.getMessage());
		}
	}
*/	
}
