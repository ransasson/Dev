package core;

import java.util.Scanner;

public class SearchEngine {
	private boolean byKind, byAirline, byOrigin,ByDestenation, byFlighNumber, byDate, byDay,byCity,byCountry,byAirport;
	private String kind,airline,origin,destenation, flightNumber,dateFirst, dateLast, day,city,country,airport;
	
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
		System.out.println("If you would like to search by Origin enter true else false");
		byOrigin=scan.nextBoolean();
		if(byOrigin) {
			System.out.println("which origin?");
			scan.nextLine();
			origin=scan.nextLine();
		}
		System.out.println("If you would like to search by Destenation enter true else false");
		ByDestenation=scan.nextBoolean();
		if(ByDestenation) {
			System.out.println("which destination?");
			scan.nextLine();
			destenation=scan.nextLine();
		}
		System.out.println("If you would like to search by Fligh Number enter true else false");
		byFlighNumber=scan.nextBoolean();
		if(byFlighNumber) {
			System.out.println("which flight number?");
			scan.nextLine();
			flightNumber=scan.nextLine();
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
	public SearchEngine(boolean byKind, boolean byAirline, boolean byOrigin, boolean ByDestenation ,
			boolean byFlighNumber, boolean byDate, boolean byDay,boolean byCity,boolean byCountry,boolean byAirport, String kind, String airline, String origin, String destenation,
			String flightNumber,String datefirst, String dateLast, String day,String city,String country,String airport) {
		this.byKind=byKind;
		this.byAirline=byAirline;
		this.byOrigin=byOrigin;
		this.ByDestenation=ByDestenation;
		this.byFlighNumber=byFlighNumber;
		this.byDate=byDate;
		this.byDay=byDay;
		this.byCity=byCity;
		this.byCountry=byCountry;
		this.byAirport=byAirport;
		this.kind=kind;
		this.airline=airline;
		this.origin=origin;
		this.destenation=destenation;
		this.flightNumber=flightNumber;
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


	public boolean isByOrigin() {
		return byOrigin;
	}


	public boolean isByDestenation() {
		return ByDestenation;
	}


	public boolean isByFlighNumber() {
		return byFlighNumber;
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
	
	public String getOrigin() {
		return origin;
	}
	
	public String getDestenation() {
		return destenation;
	}
	
	public String getFlightNumber() {
		return flightNumber;
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
	
	
	
	
}
