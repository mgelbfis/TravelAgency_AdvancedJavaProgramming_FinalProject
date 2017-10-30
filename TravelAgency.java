package finalProjectTravel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class TravelAgency {
	private ArrayList<ScheduledFlight> flights;
	private ArrayList<Person> persons; 
	private Integer AgencyID;
	
	//static class variables
	private static Integer LastCustomerID = 0;
	private static Integer LastEmployeeID = 0;
	private static Integer LastPassengerID = 0;
	private static Integer LastAgencyID = 0;
	
	public TravelAgency(){
		this.AgencyID = ++LastAgencyID;
		this.flights = new ArrayList<ScheduledFlight>();
		this.persons = new ArrayList<Person>();
	}
	
	public void addCustomer(String fName, String lName, char gen,String st, String ci, String sta, String zip,
			 String pNum, CreditCard cc)throws DuplicateDataException, InvalidLocationException, InvalidDataException{
	
		if(checkCustomer(fName, lName, pNum)){
			throw new DuplicateDataException();
		}
		else{
			persons.add(new Customer(++LastCustomerID, cc, fName, lName, pNum, gen, st, ci, sta, zip));
		}
	}
	
	public void addAgent(String SSN, String fName, String lName,char gen, String st, String ci, String sta,
			 String zip, String pNum)throws DuplicateDataException, InvalidLocationException, InvalidDataException{
		if(checkAgent(fName, lName, pNum)){
			throw new DuplicateDataException();
		}
		else{
			persons.add(new TravelAgent(++LastEmployeeID, SSN, fName, lName, gen, st, ci, sta, zip, pNum));
		}
	}
	
	public void addPassenger(String fName, String lName, char gen,
			String st, String ci, String sta, String zip, String pNum, Passport passp)
					throws DuplicateDataException, InvalidLocationException, InvalidDataException{
		if(checkPassenger(fName, lName, pNum)){
			throw new DuplicateDataException();
		}
		else{
			persons.add(new Passenger(++LastPassengerID, fName, lName, gen, st, ci, sta, zip, pNum, passp));
		}
	}
	
	public Passenger findPassengerInfo(Integer ID)throws InvalidDataException, InvalidLocationException{
		for(int i=0; i<persons.size(); i++){
			if(persons.get(i) instanceof Passenger){
				Passenger P = (Passenger)persons.get(i);
				if(ID.equals(P.getPassengerID())){
					return new Passenger(P);
				}
			}
		}
		throw new InvalidDataException("There is no passenger with an ID of " + ID + ".");
	
	}
	
	public Customer findCustomerInfo(Integer ID)throws InvalidDataException, InvalidLocationException{
		for(int i=0; i<persons.size(); i++){
			if(persons.get(i) instanceof Customer){
				Customer C = (Customer)persons.get(i);
				if(ID.equals(C.getCustomerID())){
					return new Customer(C);
				}
			}
		}
		throw new InvalidDataException("There is no customer with an ID of " + ID + ".");
	
	}
	
	public ArrayList<Passenger> getPassengers(){
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		for(int i=0; i<persons.size(); i++){
			if(persons.get(i) instanceof Passenger){
				passengers.add((Passenger)persons.get(i));
			}
		}
		return passengers;
	}
	
	public void addScheduledFlight(Integer flightNo, String depDate)throws DuplicateDataException, InvalidDataException{
		if(checkFlight(flightNo, depDate)){//in this application no two flights will really have the same number because the flight number is taken from a public static variable
			throw new DuplicateDataException();
		}
		else{
			flights.add(new ScheduledFlight(flightNo, depDate));
		}
	}
	
	//assuming we do not need to add the money to the travel agent or the charge to the customer's card
	public void bookFlight(Integer flightNo, Integer passengerID, int seatNumber, Integer customerID)throws InvalidDataException, InvalidLocationException, 
	SeatNotAvailableException, FullyBookedException, DuplicateDataException{
			int pasNum = findPassenger(passengerID);
			//just using it to see if passenger exists--throws specific InvalidDataException instead of NotFoundException
			//took the following check out of the program because was extremely inefficient and we don't really do anything with it in this application
			//findCustomerInfo(customerID);//to make sure there's someone paying for it--but this application stops the process of payment at this point
			int fliNum = findFlight(flightNo);
			flights.get(fliNum).bookSeat(this.getPassengers().get(pasNum), seatNumber-1);
	}
	
	public void cancelFlight(Integer flightNo, Integer passengerID, int seatNumber)throws InvalidDataException{
		int pasNum = findPassenger(passengerID);
		int flightNum = findFlight(flightNo);
		flights.get(flightNum).cancelReservation(this.getPassengers().get(pasNum), seatNumber-1);
		
	}
	
	public String toString(){
		return persons.toString() + "\n\n" + flights.toString();
	}
	
	public int compareTo(TravelAgency T){
		return this.AgencyID.compareTo(T.AgencyID);
	}
	
	public boolean equals(Object O){
		if(O==null){
			return false;
		}
		else{
			if(O instanceof TravelAgency){
				TravelAgency T = (TravelAgency)O;
				return (this.AgencyID.compareTo(T.AgencyID)==0);
			}
			else{
				return false;
			}
		}
	}
		

	
	private boolean checkCustomer(String fName, String lName, String pNum){
		for(int i=0; i<this.persons.size(); i++){
			if(this.persons.get(i).getFirstName().equals(fName) &&
					this.persons.get(i).getLastName().equals(lName) &&
					this.persons.get(i).getPhoneNumber().equals(pNum) &&
						this.persons.get(i) instanceof Customer)
				{
				return true;
				}
			}
		return false;
	}
	
	private boolean checkAgent(String fName, String lName, String pNum){
		for(int i=0; i<this.persons.size(); i++){
			if(this.persons.get(i).getFirstName().equals(fName) &&
					this.persons.get(i).getLastName().equals(lName) &&
					this.persons.get(i).getPhoneNumber().equals(pNum) &&
						this.persons.get(i) instanceof TravelAgent)
				{
				return true;
				}
			}
		return false;
	}
	
	private boolean checkPassenger(String fName, String lName, String pNum){
		for(int i=0; i<this.persons.size(); i++){
			if(this.persons.get(i).getFirstName().equals(fName) &&
					this.persons.get(i).getLastName().equals(lName) &&
					this.persons.get(i).getPhoneNumber().equals(pNum) &&
						this.persons.get(i) instanceof Passenger)
				{
				return true;
				}
			}
		return false;
	}
	
	private boolean checkFlight(Integer flightID, String depDate)throws InvalidDataException{
		try{
			for(int i=0; i<this.flights.size(); i++){
			if(this.flights.get(i).getFlightID().equals(flightID)&& this.flights.get(i).getDepDate().equals(LocalDate.parse(depDate))){
				return true;
			}
		}
		}
		catch(DateTimeParseException e){
			throw new InvalidDataException("The departure date was not entered in the correct format.");
		}
		return false;
	}
	
	private int findFlight(Integer flightID)throws InvalidDataException{
		for(int i=0; i<this.flights.size(); i++){
			if(this.flights.get(i).getFlightID().equals(flightID)){
				return i;
			}
		}
		throw new InvalidDataException("There is no current flight with that ID in our records.");
		
	}
	
	private int findPassenger(Integer pasID)throws InvalidDataException{
		for(int i=0; i<this.getPassengers().size(); i++){
			if(this.getPassengers().get(i).getPassengerID().equals(pasID)){
				return i;
			}
		}
		throw new InvalidDataException("We cannot find a current passenger with the given passenger ID.");
		
	}
}
