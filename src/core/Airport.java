package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Airport {
	private String name;
	private List<Flight> departures;
	private List<Flight> arrivals;

	public Airport(String name) {
		this.name = name;
		departures = new ArrayList<Flight>();
		arrivals = new ArrayList<Flight>();
	}

	public void addFlight(Flight flight) {
		if (flight.getKind().equalsIgnoreCase("Arrivals")) {
			arrivals.add(flight);
		} else
			departures.add(flight);
	}

	public void addFlight(Scanner scan) throws ParseException {
		System.out.println("Enter flight details");
		scan.nextLine();
		Flight flight = new Flight(scan);
		addFlight(flight);
	}

	public void writeToFile(File f) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(f);
		writer.println(this.name);
		writer.println(departures.size());
		for (int i = 0; i < departures.size(); i++) {
			departures.get(i).write(writer);
		}
		writer.println(arrivals.size());
		for (int i = 0; i < arrivals.size(); i++) {
			arrivals.get(i).write(writer);
		}
		writer.close();
	}

	public void readFromFile(File f) throws FileNotFoundException, ParseException {
		Scanner scan = new Scanner(f);
		this.name = scan.nextLine();
		int sizeDepartues = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeDepartues; i++) {
			Flight f1 = new Flight();
			f1.read(scan);
			this.departures.add(i, f1);
		}
		int sizeArrivals = Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeArrivals; i++) {
			Flight f1 = new Flight();
			f1.read(scan);
			this.arrivals.add(i, f1);
		}
	}

	public String showDepartures() {
		if (departures.size() == 0) {
			String str = "No departures yet";
			return str;
		} else {
			sortFlights(departures);
			StringBuffer sb = new StringBuffer("Here are all the departures:\n");
			for (int i = 0; i < departures.size(); i++) {
				sb.append(departures.get(i).toString());
			}
			String allDepartures = sb.toString();
			return allDepartures;
		}
	}

	public String showArrivals() {
		if (arrivals.size() == 0) {
			String str = "No arrivals yet";
			return str;
		} else {
			sortFlights(arrivals);
			StringBuffer sb = new StringBuffer("Here are all the arrivals:\n");
			for (int i = 0; i < arrivals.size(); i++) {
				sb.append(arrivals.get(i).toString());
			}
			String allArrivals = sb.toString();
			return allArrivals;
		}
	}

	Comparator<Flight> compareByDate = new Comparator<Flight>() {
		public int compare(Flight flight1, Flight flight2) {
			return flight1.getDateAndTime().compareTo(flight2.getDateAndTime());
		}
	};

	private void sortFlights(List<Flight> flights) {
		Collections.sort(flights, compareByDate);
	}

	public String toString() {
		return "Airport: " + name + "has " + departures.size() + " departures planned and " + arrivals.size()
				+ "arrivals planned";
	}

	public String searchFlights(Scanner scan) {
		SearchEngine helper = new SearchEngine(scan);
		List<Flight> flightBeforeSearch = new ArrayList<Flight>();
		for (int i = 0; i < arrivals.size(); i++) {
			Flight temp = new Flight(arrivals.get(i));
			flightBeforeSearch.add(temp);
		}
		for (int i = 0; i < departures.size(); i++) {
			Flight temp = new Flight(departures.get(i));
			flightBeforeSearch.add(temp);
		}

		List<Flight> flightAfterSearch = new ArrayList<Flight>();

		flightAfterSearch = helper.search(flightBeforeSearch);

		StringBuffer sb = new StringBuffer();
		if (flightAfterSearch.size() != 0) {
			sb.append("Here are all the flights according to your search:\n");
			for (int i = 0; i < flightAfterSearch.size(); i++) {
				sb.append(flightAfterSearch.get(i).toString());
			}
		} else {
			sb.append("No flights found");
		}

		return sb.toString();
	}

	public String searchFlights(boolean isHtml, boolean byAirline, boolean byDate, boolean sunday,boolean monday,boolean tuesday,boolean wednesday,
			boolean thursday,boolean friday,boolean saturday,boolean byCity, boolean byCountry, boolean byAirport, 
			String kind, String airline, String datefirst, String dateLast, String city, String country, String airport) {
		SearchEngine helper = new SearchEngine(byAirline, byDate, sunday,monday,tuesday,wednesday,thursday,friday,saturday, byCity, byCountry, byAirport, kind, airline,
				datefirst, dateLast, city, country, airport);
		List<Flight> flightsBeforeSearch = new ArrayList<Flight>();

		for (int i = 0; i < arrivals.size(); i++) {
			Flight temp = new Flight(arrivals.get(i));
			flightsBeforeSearch.add(temp);
		}
		for (int i = 0; i < departures.size(); i++) {
			Flight temp = new Flight(departures.get(i));
			flightsBeforeSearch.add(temp);
		}
		List<Flight> flightAfterSearch = new ArrayList<Flight>();

		flightAfterSearch = helper.search(flightsBeforeSearch);

		StringBuffer sb = new StringBuffer();
		if (flightAfterSearch.size() != 0) {
			if (isHtml) {
				sb.append("Here are all the flights according to your search:<br>");
			} else {
				sb.append("Here are all the flights according to your search:\n");
			}
			for (int i = 0; i < flightAfterSearch.size(); i++) {
				if (isHtml) {
					sb.append(flightAfterSearch.get(i).toString() + "<br>");
				} else {
					sb.append(flightAfterSearch.get(i).toString());
				}
			}
		} else {
			sb.append("No flights found");
		}
		return sb.toString();
	}

	public LocalDateTime stringToDate(String dateandTime) {
		String[] dateAndTimeS = dateandTime.split(" ");
		String date = dateAndTimeS[0];
		String[] dateArray = date.split("/");
		int year = Integer.parseUnsignedInt(dateArray[2]);
		int month = Integer.parseUnsignedInt(dateArray[1]);
		int day = Integer.parseUnsignedInt(dateArray[0]);
		String time = dateAndTimeS[1];
		String[] timeArray = time.split(":");
		int hours = Integer.parseUnsignedInt(timeArray[0]);
		int minutes = Integer.parseUnsignedInt(timeArray[1]);
		LocalDateTime finalDate = LocalDateTime.of(year, month, day, hours, minutes);
		return finalDate;
	}

}
