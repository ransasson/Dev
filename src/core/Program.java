package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		//// using args////
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isDepartures = args.length > 1 && args[1].equalsIgnoreCase("departures");
		
		

		Scanner scan = new Scanner(System.in);
		File f = new File("Airport");
		Airport airport = new Airport("Natbag");
		int choice = 0;

		Flight flight1 = new Flight("El al", "London", "Tlv", 2020, 5, 20, 10, 10, "LY315", 3,"Heathrow","England");
		Flight flight2 = new Flight("El al", "New York", "Tlv", 2020, 5, 20, 00, 45, "LY001", 3,"JFK","USA");
		Flight flight3 = new Flight("Israir", "Tlv", "Tabilisi", 2020, 6, 1, 14, 35, "6H896", 3,"Tabilisi International Airport","Georgia");
		Flight flight4 = new Flight("United Airlines", "Tlv", "New York", 2020, 5, 13, 12, 45, "UA090", 3,"JFK","USA");

		airport.addFlight(flight1);
		airport.addFlight(flight2);
		airport.addFlight(flight3);
		airport.addFlight(flight4);
		String str = "";
		//from server
		if (isHtml) {
			if (isDepartures) {
				String departures = airport.showDepartures();
				System.out.println(departures);
				if (isHtml) {
					System.out.println("<br>");
				}
			} else {
				String arrivals = airport.showArrivals();
				System.out.println(arrivals);
				if (isHtml) {
					System.out.println("<br>");
				}
			}
		} else {
			do {
				menu();
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					airport.addFlight(scan);
					break;
				case 2:
					String departures = airport.showDepartures();
					System.out.println(departures);
					break;
				case 3:
					String arrivals = airport.showArrivals();
					System.out.println(arrivals);
					break;
				case 4:
					airport.writeToFile(f);
					break;
				case 5:
					airport.readFromFile(f);
					break;
				case 6:
					str = airport.searchFlights(scan);
					System.out.println(str);
					break;
				default:
					System.out.println("option unavailable");
				}
			} while (choice != -1);
		}
	}

	public static final int Add_Flight = 1;
	public static final int Show_All_Departures = 2;
	public static final int Show_All_Arrivals = 3;
	public static final int Write_to_file = 4;
	public static final int Read_From_File = 5;
	public static final int Search = 6;

	public static void menu() {
		System.out.println("welcome to the flights app");
		System.out.println("to add flight press " + Add_Flight);
		System.out.println("to show all departures press " + Show_All_Departures);
		System.out.println("to show all arrivals press " + Show_All_Arrivals);
		System.out.println("to save the data to file press " + Write_to_file);
		System.out.println("to read saved data from file press " + Read_From_File);
		System.out.println("to search a flight press " + Search);
	}

}
