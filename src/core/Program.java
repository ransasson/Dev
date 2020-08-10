package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		//// using args////
		boolean isHtml = args.length > 0 && args[0].equalsIgnoreCase("html");
		boolean isText = args.length > 0 && args[0].equalsIgnoreCase("text");
		boolean isDepartures = args.length > 1 && args[1].equalsIgnoreCase("departures");
		boolean byAirline = false;
		boolean byDate = false;
		boolean byDay = false;
		boolean byCountry = false;
		boolean byCity = false;
		boolean byAirport = false;
		String kind = "";
		String city = "";
		String country = "";
		String airportName = "";
		String airLine = "";
		String day = "";
		int day1 = 0;
		int month1 = 0;
		int year1 = 0;
		int day2 = 0;
		int month2 = 0;
		int year2 = 0;
		if (isDepartures) {
			kind = "departures";
		} else {
			kind = "arrivals";
		}
		if (args.length > 2) {
			country = args[2];
			if (country != "" && country != null) {
				byCountry = true;
			}
		}
		if (args.length > 3) {
			city = args[3];
			if (city != "") {
				byCity = true;
			}
		}
		if (args.length > 4) {
			airportName = args[4];
			if (airportName != "") {
				byAirport = true;
			}
		}
		if (args.length > 5) {
			airLine = args[5];
			if (airLine != "") {
				byAirline = true;
			}
		}

		if (args.length > 6) {
			day1 = Integer.parseInt(args[6]);
			if (day1 > 0 && day1 <= 31) {
				byDate = true;
			}
		}
		if (args.length > 7) {
			month1 = Integer.parseInt(args[7]);
		}
		if (args.length > 8) {
			year1 = Integer.parseInt(args[8]);
		}
		if (args.length > 9) {
			day2 = Integer.parseInt(args[9]);
		}
		if (args.length > 10) {
			month2 = Integer.parseInt(args[10]);
		}
		if (args.length > 11) {
			year2 = Integer.parseInt(args[11]);
		}
		StringBuffer firstDate = new StringBuffer();
		firstDate.append(day1 + "/" + month1 + "/" + year1 + " 00:00");
		StringBuffer secondDate = new StringBuffer();
		secondDate.append(day2 + "/" + month2 + "/" + year2 + " 00:00");

		boolean sunday = args.length > 12 && args[12].equalsIgnoreCase("sunday");
		boolean monday = args.length > 13 && args[13].equalsIgnoreCase("monday");
		boolean tuesday = args.length > 14 && args[14].equalsIgnoreCase("tuesday");
		boolean wednesday = args.length > 15 && args[15].equalsIgnoreCase("wednesday");
		boolean thursday = args.length > 16 && args[16].equalsIgnoreCase("thursday");
		boolean friday = args.length > 17 && args[17].equalsIgnoreCase("friday");
		boolean saturday = args.length > 18 && args[18].equalsIgnoreCase("saturday");

		if (sunday || monday || thursday || tuesday || wednesday || friday || saturday) {
			byDay = true;
			if (sunday) {
				day = "sunday";
			}
			if (monday) {
				day += " monday";
			}
			if (tuesday) {
				day += " tuesday";
			}
			if (wednesday) {
				day += " wednesday";
			}
			if (thursday) {
				day += " thursday";
			}
			if (friday) {
				day += " friday";
			}
			if (saturday) {
				day += " saturday";
			}
		}

		Scanner scan = new Scanner(System.in);
		File f = new File("Airport");
		Airport airport = new Airport("Natbag");
		int choice = 0;

		Flight flight1 = new Flight("elal", "London", "Tlv", 2020, 5, 20, 10, 10, "LY315", 3, "Heathrow", "England");
		Flight flight2 = new Flight("elal", "New York", "Tlv", 2020, 5, 20, 00, 45, "LY001", 3, "JFK", "USA");
		Flight flight3 = new Flight("Israir", "Tlv", "Tabilisi", 2020, 6, 1, 14, 35, "6H896", 3,
				"Tabilisi International Airport", "Georgia");
		Flight flight4 = new Flight("United Airlines", "Tlv", "New York", 2020, 5, 13, 12, 45, "UA090", 3, "JFK",
				"USA");
		Flight flight5 = new Flight("elal", "paris", "Tlv", 2020, 6, 14, 00, 00, "LY420", 3, "CDG", "france");
		Flight flight6 = new Flight("elal", "paris", "Tlv", 2020, 7, 15, 00, 00, "LY255", 3, "CDG", "france");
		Flight flight7 = new Flight("elal", "Tlv", "paris", 2020, 7, 15, 00, 00, "LY255", 3, "CDG", "france");

		airport.addFlight(flight1);
		airport.addFlight(flight2);
		airport.addFlight(flight3);
		airport.addFlight(flight4);
		airport.addFlight(flight5);
		airport.addFlight(flight6);
		airport.addFlight(flight7);
		String str = "";
		// from server
		if (isHtml || isText) {
			String flights = airport.searchFlights(isHtml, byAirline, byDate, byDay, byCity, byCountry, byAirport, kind,
					airLine, firstDate.toString(), secondDate.toString(), day, city, country, airportName);
			System.out.println(flights);
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
