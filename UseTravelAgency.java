package finalProjectTravel;

import java.util.Scanner;


public class UseTravelAgency {
	
	public static void main(String []args){
		TravelAgency KwikTravel = new TravelAgency();
		
		int choice;
		do{
			choice = menu();
			Scanner user = new Scanner(System.in);
			String fName;
			String lName;
			char gen;
			String st;
			String ci;
			String sta;
			String zip;
			String pNum;
			
			switch(choice){
		
			case 1: System.out.println("Enter first name:");
					fName = user.nextLine();       
					System.out.println("Enter last name:");
					lName = user.nextLine();
					System.out.println("Enter gender--Enter just the letter M or F");
					gen = user.nextLine().charAt(0);
					System.out.println("Enter street address:");
					st = user.nextLine();
					System.out.println("Enter city:");
					ci = user.nextLine();
					System.out.println("Enter state--enter the full name of the state, ex: New York and not NY:");
					sta = user.nextLine();
					System.out.println("Enter zip code:");
					zip = user.nextLine();
					System.out.println("Enter phone number: (ex: 718-837-8007)");
					pNum = user.nextLine();
					System.out.println("Credit Card Information:\nEnter credit card number:");
					Integer ID = user.nextInt();
					user.nextLine();
					System.out.println("Enter the card expiration date in the CORRECT FORMAT: YYYY-MM-DD");
					String expDate = user.nextLine();
					System.out.println("Enter the credit card pin:");
					Integer pin = user.nextInt();
					user.nextLine();
					try{
						CreditCard cc = new CreditCard(ID, expDate, pin);
						KwikTravel.addCustomer(fName, lName, gen, st, ci, sta, zip, pNum, cc);
						System.out.println("A new customer has been set up.");
					}
					catch(CardExpiredException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					catch(DuplicateDataException e){
						System.out.println(e.getMessage());
					}
					break;
			case 2: System.out.println("Enter first name:");
					fName = user.nextLine();       
					System.out.println("Enter last name:");
					lName = user.nextLine();
					System.out.println("Enter gender--Enter just the letter M or F");
					gen = user.nextLine().charAt(0);
					System.out.println("Enter street address:");
					st = user.nextLine();
					System.out.println("Enter city:");
					ci = user.nextLine();
					System.out.println("Enter state--enter the full name of the state, ex: New York and not NY:");
					sta = user.nextLine();
					System.out.println("Enter zip code:");
					zip = user.nextLine();
					System.out.println("Enter phone number: (ex: 718-837-8007)");
					pNum = user.nextLine();
					System.out.println("Enter employee Social Security Number:");
					String SSN = user.nextLine();
					try{
					KwikTravel.addAgent(SSN, fName, lName, gen, st, ci, sta, zip, pNum);
					System.out.println("A new travel agent has been set up.");
					}
					catch(InvalidDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					catch(DuplicateDataException e){
						System.out.println(e.getMessage());
					}
					break;
			case 3: System.out.println("Enter first name:");
					fName = user.nextLine();       
					System.out.println("Enter last name:");
					lName = user.nextLine();
					System.out.println("Enter gender--Enter just the letter M or F");
					gen = user.nextLine().charAt(0);
					System.out.println("Enter street address:");
					st = user.nextLine();
					System.out.println("Enter city:");
					ci = user.nextLine();
					System.out.println("Enter state--enter the full name of the state, ex: New York and not NY:");
					sta = user.nextLine();
					System.out.println("Enter zip code:");
					zip = user.nextLine();
					System.out.println("Enter phone number: (ex: 718-837-8007)");
					pNum = user.nextLine();
					System.out.println("Passport Information: \nEnter Passport ID");
					Integer passID = user.nextInt();
					user.nextLine();
					System.out.println("Enter Birth Date in CORRECT FORMAT: YYYY-MM-DD");
					String bDate = user.nextLine();
					System.out.println("Enter Place of Birth:");
					String bPlace = user.nextLine();
					System.out.println("Enter Passport Issue Date in CORRECT FORMAT: YYYY-MM-DD");
					String iDate = user.nextLine();
					System.out.println("Enter Passport Expiration Date in CORRECT FORMAT: YYYY-MM-DD");
					String exprDate = user.nextLine();
					System.out.println("Enter Nationality:");
					String Nat = user.nextLine();
					try{
						Passport passp = new Passport(passID, fName, lName, bDate, bPlace, iDate, exprDate, Nat);
						KwikTravel.addPassenger(fName, lName, gen, st, ci, sta, zip, pNum, passp);
						System.out.println("A new passenger has been set up.");
					}
					catch(InvalidDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					catch(DuplicateDataException e){
						System.out.println(e.getMessage());
					}
					break;
			case 4: System.out.println("Enter passenger ID:");
					Integer pID = user.nextInt();
					try{
						System.out.println(KwikTravel.findPassengerInfo(pID).toString());
					}
					catch(InvalidDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					break;
			case 5:  System.out.println("Enter customer ID:");
					Integer cID = user.nextInt();
					try{
						System.out.println(KwikTravel.findCustomerInfo(cID).toString());
					}
					catch(InvalidDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					break;
			case 6: System.out.println(KwikTravel.getPassengers());
					break;
			case 7: System.out.println("Enter the departure date in the CORRECT FORMAT: YYYY-MM-DD");
					String depDate = user.nextLine();
					Integer flightNo = ++ScheduledFlight.lastFlightID;
					try{
						KwikTravel.addScheduledFlight(flightNo, depDate);
						System.out.println("A new flight has been set up.");
					}
					catch(InvalidDataException e)
					{
						--ScheduledFlight.lastFlightID;
						System.out.println(e.getMessage());
					}
					catch(DuplicateDataException e)
					{
						System.out.println(e.getMessage());
					}
					break;
			case 8:	System.out.println("Enter the flight number:");
					Integer flightNum = user.nextInt();
					System.out.println("Enter the passenger ID");
					Integer passengerID = user.nextInt();
					System.out.println("Enter the seat number:");
					Integer seatNumber = user.nextInt();
					System.out.println("Enter the customer ID of the customer responsible for payment:");
					Integer customerID = user.nextInt();
					try{
						KwikTravel.bookFlight(flightNum, passengerID, seatNumber, customerID);
						System.out.println("Seat number " + seatNumber + " has been booked for the passenger.");
					}
					catch(InvalidDataException e)
					{
						System.out.println(e.getMessage());
					}
					catch(FullyBookedException e)
					{
						System.out.println(e.getMessage());
					}
					catch(SeatNotAvailableException e){
						System.out.println(e.getMessage());
					}
					catch(DuplicateDataException e){
						System.out.println(e.getMessage());
					}
					catch(InvalidLocationException e){
						System.out.println(e.getMessage());
					}
					break;
			case 9: System.out.println("Enter the flight number:");
					Integer flightNumb = user.nextInt();
					System.out.println("Enter the passenger ID");
					Integer passengeID = user.nextInt();
					System.out.println("Enter the seat number:");
					Integer seatNumb = user.nextInt();
					try{
						KwikTravel.cancelFlight(flightNumb, passengeID, seatNumb);
						System.out.println("The reservation has been successfully canceled.");
					}
					catch(InvalidDataException e)
					{
						System.out.println(e.getMessage());
					}

					break;
			case 10: System.out.println(KwikTravel.toString());
					break;
			}
		}while(choice!=11);
		
		System.out.println("Exiting application...Have a good day!");
		System.exit(0);
	}
	
	public static int menu(){
		int choice;
		do{
			System.out.println("Welcome to Kwik Travel! Choose from the following menu options and enter the NUMBER of your choice:"
				+ "\n1. Add a Customer \n2. Add a Travel Agent \n3. Add a Passenger "
				+ "\n4. Get information for specific passenger \n5. Get information for specific customer"
				+ "\n6. Get a list of all the passengers and their information \n7. Schedule a flight \n8. Make a reservation"
				+ "\n9. Cancel a reservation \n10. View Travel Agency Information \n11. Exit");
			Scanner input = new Scanner(System.in);
			choice = input.nextInt();
			
			if(choice<1||choice>11){
				System.out.println("That was not a valid choice. Enter a number from 1 to 10. ");
			}
			
		}while(choice<1||choice>11);
			
		return choice;
	}

}
