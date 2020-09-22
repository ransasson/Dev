package core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
	private boolean byAirline, byDate,sunday,monday,tuesday,wednesday,thursday,friday,saturday, byCity, byCountry, byAirport;
	private String kind, airline, dateFirst, dateLast, city, country, airport;

	public SearchEngine(Scanner scan) {

		System.out.println("enter: Arrivals / Departures ");
		scan.nextLine();
		kind = scan.nextLine();
		System.out.println("If you would like to search by Airline enter true else false");
		byAirline = scan.nextBoolean();
		if (byAirline) {
			System.out.println("which airline?");
			scan.nextLine();
			airline = scan.nextLine();
		}
		System.out.println("If you would like to search by the day SUNDAY enter true else false");
		sunday = scan.nextBoolean();
		System.out.println("If you would like to search by the day MONDAY enter true else false");
		monday = scan.nextBoolean();
		System.out.println("If you would like to search by the day TUESDAY enter true else false");
		tuesday = scan.nextBoolean();
		System.out.println("If you would like to search by the day WEDNESDAY enter true else false");
		wednesday = scan.nextBoolean();
		System.out.println("If you would like to search by the day THURSDAY enter true else false");
		thursday = scan.nextBoolean();
		System.out.println("If you would like to search by the day FRIDAY enter true else false");
		friday = scan.nextBoolean();
		System.out.println("If you would like to search by the day SATURDAY enter true else false");
		saturday = scan.nextBoolean();
		
		System.out.println("If you would like to search by Date enter true else false");
		byDate = scan.nextBoolean();
		if (byDate) {
			System.out.println("which date first? dd/mm/yyyy hh:mm");
			scan.nextLine();
			dateFirst = scan.nextLine();
			System.out.println("which date last? dd/mm/yyyy hh:mm");
			dateLast = scan.nextLine();
		}
		System.out.println("If you would like to search by City enter true else false");
		byCity = scan.nextBoolean();
		if (byCity) {
			System.out.println("which city?");
			scan.nextLine();
			city = scan.nextLine();
		}
		System.out.println("If you would like to search by Country enter true else false");
		byCountry = scan.nextBoolean();
		if (byCountry) {
			System.out.println("which country?");
			scan.nextLine();
			country = scan.nextLine();
		}
		System.out.println("If you would like to search by Airport enter true else false");
		byAirport = scan.nextBoolean();
		if (byAirport) {
			System.out.println("which airport?");
			scan.nextLine();
			airport = scan.nextLine();
		}
	}

	public SearchEngine(boolean byAirline, boolean byDate, boolean sunday,boolean monday,boolean tuesday,boolean wednesday,
			boolean thursday,boolean friday,boolean saturday, boolean byCity, boolean byCountry,
			boolean byAirport, String kind, String airline, String datefirst, String dateLast,  String city,String country, String airport) {
		this.byAirline = byAirline;
		this.byDate = byDate;
		this.byCity = byCity;
		this.byCountry = byCountry;
		this.byAirport = byAirport;
		this.kind = kind;
		this.airline = airline;
		this.dateFirst = datefirst;
		this.dateLast = dateLast;
		this.city = city;
		this.country = country;
		this.airport = airport;
		this.sunday=sunday;
		this.monday=monday;
		this.tuesday=tuesday;
		this.wednesday= wednesday;
		this.thursday=thursday;
		this.friday=friday;
		this.saturday=saturday;
	}



	public List<Flight> search(List<Flight> flightAfterSearch) {
		flightAfterSearch = searchByKind(flightAfterSearch);

		if (byAirline) {
			flightAfterSearch = searchByAirline(flightAfterSearch);
		}
		if (byDate) {
			flightAfterSearch = searchByDate(flightAfterSearch);
		}
		if (sunday) {
			flightAfterSearch = searchBySunday(flightAfterSearch);
		}
		if (monday) {
			flightAfterSearch = searchByMonday(flightAfterSearch);
		}
		if (tuesday) {
			flightAfterSearch = searchByTuesday(flightAfterSearch);
		}
		if (wednesday) {
			flightAfterSearch = searchByWednesday(flightAfterSearch);
		}
		if (thursday) {
			flightAfterSearch = searchByThursday(flightAfterSearch);
		}
		if (friday) {
			flightAfterSearch = searchByFriday(flightAfterSearch);
		}
		if (saturday) {
			flightAfterSearch = searchBySaturday(flightAfterSearch);
		}
		if (byCity) {
			flightAfterSearch = searchByCity(flightAfterSearch);
		}
		if (byCountry) {
			flightAfterSearch = searchByCountry(flightAfterSearch);
		}
		if (byAirport) {
			flightAfterSearch = searchByAirport(flightAfterSearch);
		}
		return flightAfterSearch;

	}

	public List<Flight> searchByKind(List<Flight> allFlights) {
		if (kind.equalsIgnoreCase("Arrivals")) {
			for (int i = 0; i < allFlights.size(); i++) {
				if (allFlights.get(i).getKind().equalsIgnoreCase("Departures")) {
					allFlights.remove(i);
					i--;
				}
			}
		} else {
			for (int i = 0; i < allFlights.size(); i++) {
				if (allFlights.get(i).getKind().equalsIgnoreCase("Arrivals")) {
					allFlights.remove(i);
					i--;
				}
			}
		}
		return allFlights;
	}

	public List<Flight> searchByAirline(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getAirline().equals(airline))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}

	public List<Flight> searchByDate(List<Flight> allFlights) {
		LocalDateTime fisrtDate = stringToDate(dateFirst);
		LocalDateTime lastDate = stringToDate(dateLast);

		for (int i = allFlights.size() - 1; i >= 0; i--) {
			if (!(allFlights.get(i).getDateAndTime().isAfter(fisrtDate)
					&& allFlights.get(i).getDateAndTime().isBefore(lastDate))) {
				allFlights.remove(i);
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchBySunday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("sunday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchByMonday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("monday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchByTuesday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("tuesday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchByWednesday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("wednesday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchByThursday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("thursday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchByFriday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("friday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	private List<Flight> searchBySaturday(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDay().equalsIgnoreCase("saturday"))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}



	public List<Flight> searchByCity(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getCity().equals(city))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}

	public List<Flight> searchByCountry(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getCountry().equals(country))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}

	public List<Flight> searchByAirport(List<Flight> allFlights) {
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getAirport().equals(airport))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
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
