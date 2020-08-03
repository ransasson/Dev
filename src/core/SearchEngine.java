package core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
	private boolean byKind, byAirline, byDate, byDay,byCity,byCountry,byAirport;
	private String kind,airline,dateFirst, dateLast, day,city,country,airport;

	public SearchEngine(Scanner scan) {
		System.out.println("If you would like to search by kind enter true else false");
		byKind=scan.nextBoolean();
		if(byKind) {
			System.out.println("enter: Arrival / Departure ");
			scan.nextLine();
			kind=scan.nextLine();
		}
		System.out.println("If you would like to search by Airline enter true else false");
		byAirline=scan.nextBoolean();
		if(byAirline) {
			System.out.println("which airline?");
			scan.nextLine();
			airline=scan.nextLine();
		}
		System.out.println("If you would like to search by day in the week enter true else false");
		byDay=scan.nextBoolean();
		if(byDay) {
			System.out.println("which day?");
			scan.nextLine();
			day=scan.nextLine();
		}
		System.out.println("If you would like to search by Date enter true else false");
		byDate=scan.nextBoolean();
		if(byDate) {
			System.out.println("which date first? dd/mm/yyyy hh:mm");
			scan.nextLine();
			dateFirst=scan.nextLine();
			System.out.println("which date last? dd/mm/yyyy hh:mm");
			dateLast=scan.nextLine();
		}
		System.out.println("If you would like to search by City enter true else false");
		byCity=scan.nextBoolean();
		if(byCity) {
			System.out.println("which city?");
			scan.nextLine();
			city=scan.nextLine();
		}
		System.out.println("If you would like to search by Country enter true else false");
		byCountry=scan.nextBoolean();
		if(byCountry) {
			System.out.println("which country?");
			scan.nextLine();
			country=scan.nextLine();
		}
		System.out.println("If you would like to search by Airport enter true else false");
		byAirport=scan.nextBoolean();
		if(byAirport) {
			System.out.println("which airport?");
			scan.nextLine();
			airport=scan.nextLine();
		}
	}
	public SearchEngine(boolean byKind, boolean byAirline, boolean byDate, boolean byDay,boolean byCity,boolean byCountry,boolean byAirport, String kind, String airline,
			String datefirst, String dateLast, String day,String city,String country,String airport) {
		this.byKind=byKind;
		this.byAirline=byAirline;
		this.byDate=byDate;
		this.byDay=byDay;
		this.byCity=byCity;
		this.byCountry=byCountry;
		this.byAirport=byAirport;
		this.kind=kind;
		this.airline=airline;
		this.dateFirst=datefirst;
		this.dateLast=dateLast;
		this.day=day;
		this.city=city;
		this.country=country;
		this.airport=airport;
	}


	public boolean isByKind() {
		return byKind;
	}


	public boolean isByAirline() {
		return byAirline;
	}

	public boolean isByDate() {
		return byDate;
	}

	public boolean isByDay() {
		return byDay;
	}

	public boolean isByCity() {
		return byCity;
	}

	public boolean isByCountry() {
		return byCountry;
	}

	public boolean isByAirport() {
		return byAirport;
	}

	public String getKind() {
		return kind;
	}

	public String getAirline() {
		return airline;
	}


	public String getDatefirst() {
		return dateFirst;
	}
	public String getDatelast() {
		return dateLast;
	}

	public String getDay() {
		return day;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getAirport() {
		return airport;
	}
	public List<Flight> search(List<Flight> flightAfterSearch) {

		if(isByAirline()) {
			flightAfterSearch=searchByAirline(flightAfterSearch);
		}
		if(isByDate()) {
			flightAfterSearch=searchByDate(flightAfterSearch);
		}
		if(isByKind()) {
			flightAfterSearch=searchByKind(flightAfterSearch);
		}
		if(isByDay()) {
			flightAfterSearch=searchByDay(flightAfterSearch);
		}
		if(isByCity()) {
			flightAfterSearch= searchByCity(flightAfterSearch);
		}
		if(isByCountry()) {
			flightAfterSearch=searchByCountry(flightAfterSearch);
		}
		if(isByAirport()) {
			flightAfterSearch=searchByAirport(flightAfterSearch);
		}
		return flightAfterSearch;

	}

		public List<Flight> searchByKind(List<Flight> allFlights ) {
			if(kind.equals("Arrival")) {
				for (int i = 0; i < allFlights.size(); i++) {
					if(allFlights.get(i).getKind().equals("Departure")) {
						allFlights.remove(i);
						i--;
					}
				}
			}else {
				for (int i = 0; i < allFlights.size(); i++) {
					if(allFlights.get(i).getKind().equals("Arrival")) {
						allFlights.remove(i);
						i--;
					}
				}
			}
			return allFlights;
		}


		public List<Flight> searchByAirline(List<Flight> allFlights ) {
			for (int i = 0; i < allFlights.size();i++) {
				if(!(allFlights.get(i).getAirline().equals(airline))) {
					allFlights.remove(i);
					i--;
				}
			}
			return allFlights;
			}




		public List<Flight> searchByDate(List<Flight> allFlights){
			LocalDateTime fisrtDate = stringToDate(dateFirst);
			LocalDateTime lastDate = stringToDate(dateLast);

			for (int i = allFlights.size()-1; i >= 0; i--) {
				if (!(allFlights.get(i).getDateAndTime().isAfter(fisrtDate)
						&& allFlights.get(i).getDateAndTime().isBefore(lastDate))) {
					allFlights.remove(i);
				}
			}
			return allFlights;
		}

		public List<Flight> searchByDay(List<Flight> allFlights) {
			for (int i = 0; i < allFlights.size(); i++) {
				if (!(allFlights.get(i).getDay().equals(day))) {
					allFlights.remove(i);
					i--;
				}
			}
			return allFlights;
		}
		public List<Flight> searchByCity(List<Flight> allFlights){
			for (int i = 0; i < allFlights.size(); i++) {
				if (!(allFlights.get(i).getCity().equals(city))) {
					allFlights.remove(i);
					i--;
				}
			}
			return allFlights;
		}
		public List<Flight> searchByCountry(List<Flight> allFlights){
			for (int i = 0; i < allFlights.size(); i++) {
				if (!(allFlights.get(i).getCountry().equals(country))) {
					allFlights.remove(i);
					i--;
				}
			}
			return allFlights;
		}
		public List<Flight> searchByAirport(List<Flight> allFlights){
			for (int i = 0; i < allFlights.size(); i++) {
				if (!(allFlights.get(i).getAirport().equals(airport))) {
					allFlights.remove(i);
					i--;
				}
			}
			return allFlights;
		}

		public LocalDateTime stringToDate(String dateandTime) {
			String[] dateAndTimeS= dateandTime.split(" ");
			String date=dateAndTimeS[0];
			String[] dateArray= date.split("/");
			int year= Integer.parseUnsignedInt(dateArray[2]);
			int month=Integer.parseUnsignedInt(dateArray[1]);
			int day=Integer.parseUnsignedInt(dateArray[0]);
			String time=dateAndTimeS[1];
			String[] timeArray= time.split(":");
			int hours=Integer.parseUnsignedInt(timeArray[0]);
			int minutes=Integer.parseUnsignedInt(timeArray[1]);
			LocalDateTime finalDate=LocalDateTime.of(year, month, day, hours, minutes);
			return finalDate;
		}

	}





