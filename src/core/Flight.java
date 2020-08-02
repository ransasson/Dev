package core;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Flight  {
	private enum eFlight {Arrival,Departure};
	
	private eFlight kind;
	private String flightNumber;
	private String airline;
	private String airport;
	private String city;
	private String country;
	private LocalDateTime dateAndTime;
	private int terminal;
	private String origin;
	private String destination;
	private String dayOfWeek;
	
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
	
	public Flight(String airline, String destination,String origin, int year, int month, int day, int hours,
			int minutes, String flightNumber, int terminal,String airport,String country  ) {
		this.airline=airline;
		this.destination=destination;
		this.origin=origin;
		this.dateAndTime=LocalDateTime.of(year, month, day, hours, minutes); 
		this.flightNumber=flightNumber;
		setTerminal(terminal);
		setKind();
		this.dayOfWeek=this.dateAndTime.getDayOfWeek().toString();
		this.country=country;
		this.airport=airport;
	}
	
	 public Flight(Scanner scan) throws ParseException {
		 System.out.println("enter Arrival/Departure?");
		 kind=eFlight.valueOf(scan.nextLine());
		 if(kind.name().equals("Arrival")){
			 System.out.println("enter origin");
			 this.origin=scan.nextLine();
			 this.destination="TLV";
			 this.city=origin;
			 System.out.println("enter origin country");
			 this.country=scan.nextLine();
			 System.out.println("enter origin airport");
			 this.airport=scan.nextLine();
		 }
		 else {
			 System.out.println("enter destination");
			 this.destination=scan.nextLine();
			 this.origin="TLV";
			 this.city=destination;
			 System.out.println("enter destination country");
			 this.country=scan.nextLine();
			 System.out.println("enter destination airport");
			 this.airport=scan.nextLine();
		 }
		 System.out.println("enter airline");
		 this.airline=scan.nextLine();
		 System.out.println("enter year");
		 int year=scan.nextInt();
		 System.out.println("enter month");
		 int month=scan.nextInt();
		 System.out.println("enter day");
		 int day=scan.nextInt();
		 System.out.println("enter hours");
		 int hours=scan.nextInt();
		 System.out.println("enter minutes");
		 int minutes=scan.nextInt();
		 this.dateAndTime=LocalDateTime.of(year, month, day, hours, minutes);
		 System.out.println("enter flight number");
		 this.flightNumber=scan.next();
		 System.out.println("enter terminal");
		 int terminalNum= scan.nextInt();
		 setTerminal(terminalNum);
		 this.dayOfWeek=this.dateAndTime.getDayOfWeek().toString();
		 
	 }
	 
	public Flight(Flight flight) {
		this.airline=flight.airline;
		this.destination=flight.destination;
		this.origin=flight.origin;
		this.dateAndTime=flight.dateAndTime; 
		this.flightNumber=flight.flightNumber;
		setTerminal(flight.terminal);
		this.kind=flight.kind;
		this.dayOfWeek=this.dateAndTime.getDayOfWeek().toString();
		this.city=flight.city;
		this.country=flight.country;
		this.airport=flight.airport;
	}
	
	public Flight() {
		
	}

	public LocalDateTime getDateAndTime() {
		 return this.dateAndTime;
	 }
	
	public String getKind() {
		return this.kind.toString();
	}
	
	public String toString() {
		String formatDateTime = dateAndTime.format(format);  
		if(kind.name().equals("Arrival")) {
			return "Arrival: airline name="+airline +", origin city="+origin+", origin country="+country+",origin airport="+airport+", date and time="+formatDateTime+", day="+dayOfWeek+", flight number="+flightNumber+", terminal="+terminal+"\n";
		}
		return "Departure: airlineName=" + airline+", destination="+destination+", destination city="+origin+", destination country="+country+",destination airport="+airport+", date and time= "+formatDateTime+", day="+dayOfWeek+", flight number="+flightNumber+", terminal="+terminal+"\n";
	}
	
	private void setTerminal(int term) {
		if(term==3 || term==1) {
			this.terminal=term;
		}else
			this.terminal=3;
	}
	
	private void setKind() {
		if(this.destination.equalsIgnoreCase("tlv")) {
			this.city=origin;
			this.kind=eFlight.Arrival;
		}
		else {
			this.city=destination;
			this.kind=eFlight.Departure;
		}
	}
	
	public void read(Scanner scan) throws ParseException {
		this.airline=scan.nextLine();
		this.destination=scan.nextLine();
		this.origin=scan.nextLine();
		String dateAndTime=scan.nextLine();
		String[] dateAndTimeS= dateAndTime.split(" ");
		String date=dateAndTimeS[0];
		String[] dateArray= date.split("/");
		int year= Integer.parseUnsignedInt(dateArray[2]);
		int month=Integer.parseUnsignedInt(dateArray[1]);
		int day=Integer.parseUnsignedInt(dateArray[0]);
		String time=dateAndTimeS[1];
		String[] timeArray= time.split(":");
		int hours=Integer.parseUnsignedInt(timeArray[0]);
		int minutes=Integer.parseUnsignedInt(timeArray[1]);
		this.dateAndTime=LocalDateTime.of(year, month, day, hours, minutes);
		this.dayOfWeek=scan.nextLine();
		this.flightNumber=scan.nextLine();
		this.terminal=scan.nextInt();
		setKind();
		scan.nextLine();
	}

	public void write(PrintWriter writer) {
		writer.println(this.airline);
		writer.println(this.destination);
		writer.println(this.origin);
		String formatDateTime = dateAndTime.format(format);  
		writer.println(formatDateTime);
		writer.println(this.dayOfWeek);
		writer.println(this.flightNumber);
		writer.println(this.terminal);
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	

	public String getAirline() {
		return airline;
	}

	

	public String getOrigin() {
		return origin;
	}

	

	public String getDestination() {
		return destination;
	}

	public String getDay() {
		return dayOfWeek;
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

