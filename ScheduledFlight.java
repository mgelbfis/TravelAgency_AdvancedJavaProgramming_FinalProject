package finalProjectTravel;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ScheduledFlight {
	private ArrayList<Passenger> passengers; 
	private Seat[] seats = new Seat[10];
	private LocalDate departureDate;
	private Integer flightID;
	private int availFCSeats;
	private int availESeats;
	//static class variable
	public static Integer lastFlightID=0;
	
	public ScheduledFlight(Integer nextFlightID, String dpDate)throws InvalidDataException{
		
		try{
			if(LocalDate.parse(dpDate).isBefore(LocalDate.now()))
			{throw new InvalidDataException("The departure date passed already. Could not process.");}
			this.departureDate = LocalDate.parse(dpDate);}
		catch(DateTimeParseException e){
			throw new InvalidDataException("The date was not entered in the correct format.");
		}
		for(int i=0; i<this.seats.length; i++){
			if(i<5){
				this.seats[i]= new Seat("First Class");
			}
			if(i>4){
				this.seats[i]=new Seat("Economy");
			}
		}
	
		this.flightID = nextFlightID;//can't be null---coming from a static variable in this app
		this.availFCSeats = 5;
		this.availESeats = 5;
		this.passengers = new ArrayList<Passenger>();
		
	}
	
	//ATTENTION:
	//the seatNumber passed in should be the actual number in the array when it gets to this class
	//just so that no confusion and don't end up subtracting twice
	
	public SeatType getSeatType(int seatNumber)throws InvalidDataException{
		if(seatNumber>9){
			throw new InvalidDataException("That was not a valid seat number. Enter a number from 1 to 10.");
			//will be thrown until user so 1-10, not 0-9
		}
		return seats[seatNumber].getType();
	}
	
	public Integer getFlightID(){
		return this.flightID;
	}
	
	public LocalDate getDepDate(){
		return this.departureDate;
	}
	
	public void bookSeat(Passenger P, int seatNumber)throws DuplicateDataException, SeatNotAvailableException, InvalidDataException, FullyBookedException{
		if(P==null||seatNumber>9)
		{
			throw new InvalidDataException("The passenger and/or seat number were invalid.");
		}
		boolean bookedAlready = checkPassenger(P);
		if(bookedAlready){
			throw new DuplicateDataException();
		}
		else{
			if(this.seats[seatNumber].isAvailable()){
				this.seats[seatNumber].bookSeat(P);
				this.passengers.add(P);
				String type = (this.seats[seatNumber].getType()).name();
				//checking according to type not number as extra safety--might change # of each, want to make sure decrementing right one
				if(type.equals("FIRSTCLASS")){
					this.availFCSeats = this.availFCSeats-1;
				}
				else {
					this.availESeats = this.availESeats-1;
				}
				//A seat can't get through this class or its constructor if its type is not one of the two
				//else{
				//	throw new InvalidDataException();
				//}
			}
			else if(flightFullyBooked()){
				throw new FullyBookedException();
			}
			else{
				throw new SeatNotAvailableException();
			}
		}
	}
	
	public void cancelReservation(Passenger P, int seatNumber)throws InvalidDataException{
			if(P==null){throw new InvalidDataException("The passenger entered was invalid.");}
				int it = findPassenger(P);
				this.seats[seatNumber].cancelReservation(P);
				this.passengers.remove(P);
				String type = (this.seats[it].getType()).name();
				if(type.equals("FIRSTCLASS")){
					this.availFCSeats++;
				}
				else if(type.equals("ECONOMY")){
					this.availESeats++;
				}
		}
	
	
	//prints out the whole plane, even the ones that are not booked
	public String viewPlaneLayout(){
		StringBuffer endDisplay = new StringBuffer("\nFlight #:" + this.flightID + "\n" + "--------------------------\n");
		for(int i=0; i<this.seats.length; i++){
			endDisplay.append("Seat #" + (i+1) + "=" + seats[i].toString()+ "\n\n");
		}
		return endDisplay.toString();
	}
	
	public boolean checkPassenger(Passenger P){
		for(int i=0; i<this.passengers.size(); i++){
			if(P.equals(this.passengers.get(i))){
				return true;
			};
		}
		return false;
	}
	
	public int findPassenger(Passenger P) throws InvalidDataException{
		for(int i=0; i<this.seats.length; i++){
			if(P.equals(seats[i].getPassenger())){
				return i;
			}
		}
		throw new InvalidDataException("The passenger entered is not on this flight.");
	
	}
	
	public boolean flightFullyBooked(){
		for(int i=0; i<seats.length; i++){
			if(seats[i].isAvailable()){
				return false;
			}
		}
		return true;
	}
	
	public String toString(){
		StringBuffer endDisplay = new StringBuffer("\n");
		//can use a for each because with every instance of ScheduledFlight, the whole array is filled with seats  
		for(Seat s: this.seats){
			endDisplay.append(s.toString() + "\n");
		}
		return ("\n\nFlight ID: " + this.flightID + "\nDeparture Date: " + this.departureDate 
				+ "\nFirst Class Seats Available: " + this.availFCSeats + "\nEconomy Seats Available: " 
				+ this.availESeats + "\n\t\t" + endDisplay);
	}
	
	//compareTo and equals only compare with flight ID because in this program there are no two flights with the same number
	public int compareTo(ScheduledFlight F){
		return this.flightID.compareTo(F.flightID);
	}
	
	public boolean equals(Object o){
		if(o==null){
			return false;
		}
		else{
			if(o instanceof ScheduledFlight){
				ScheduledFlight F = (ScheduledFlight)o;
				return ((this.flightID).compareTo(F.flightID))==0;
			}
			else{
				return false;
			}
		}
	}
	
/**	public static void main(String []args){
		try{
			ScheduledFlight F = new ScheduledFlight(++ScheduledFlight.lastFlightID, "2016-06-26");
			System.out.println(F.toString());
			System.out.println(F.getSeatType(9));//obviously get the wrong message in this main (1-10) because wasn't switched by the preceding class
			Passenger P = new Passenger(25, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger Q = new Passenger(27, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger R = new Passenger(28, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger S = new Passenger(29, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger T = new Passenger(23, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger U = new Passenger(22, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger V = new Passenger(21, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger W = new Passenger(20, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger X = new Passenger(26, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger Y = new Passenger(24, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			Passenger Z = new Passenger(30, "G", "l", 'M', "901 Ave N", "Brookyn", "Delaware", "15248", "781-459-6253");
			F.bookSeat(P, 2);
			System.out.println(F.viewPlaneLayout());
			//F.cancelReservation(P, 2);
			F.bookSeat(Q, 7);
			F.bookSeat(R, 0);
			F.bookSeat(S, 1);
			F.bookSeat(T, 3);
			F.bookSeat(U, 4);
			F.bookSeat(V, 5);
			F.bookSeat(W, 6);
			F.bookSeat(X, 8);
			F.bookSeat(Y, 9);
			System.out.println(F.viewPlaneLayout());
			F.bookSeat(Z, 6);
			
			//ScheduledFlight Qu = new ScheduledFlight(++ScheduledFlight.lastFlightID, "2016-07-26");
			//System.out.println(Qu.toString());
			
		}
		catch(InvalidDataException e){
			System.out.println(e.getMessage());
		}
		catch(InvalidLocationException e){
			System.out.println(e.getMessage());
		}
		catch(SeatNotAvailableException e){
			System.out.println(e.getMessage());
		}
		catch(DuplicateDataException e){
			System.out.println(e.getMessage());
		}
		catch(FullyBookedException e){
			System.out.println(e.getMessage());
		}
	}
*/	
}
