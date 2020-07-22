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
	private  List <Flight> departures;
	private List <Flight> arrivals;
	
	public Airport(String name) {
		this.name=name;
		departures=new ArrayList<Flight>();
		arrivals=new ArrayList<Flight>();
	}
	
	public void addFlight(Flight flight) {
		if(flight.getKind().equalsIgnoreCase("Arrival")) {
			arrivals.add(flight);
		}else
			departures.add(flight);
	}

	public void addFlight(Scanner scan) throws ParseException {
		System.out.println("Enter flight details");
		scan.nextLine();
		Flight flight =new Flight(scan);
		addFlight(flight);
	}
	

	public void writeToFile(File f) throws FileNotFoundException {
		PrintWriter writer=new PrintWriter(f);
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
		Scanner scan=new Scanner(f);
		this.name=scan.nextLine();
		int sizeDepartues=Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeDepartues; i++) {
			Flight f1= new Flight();
			f1.read(scan);
			this.departures.add(i, f1);
		}
		int sizeArrivals=Integer.parseInt(scan.nextLine());
		for (int i = 0; i < sizeArrivals; i++) {
			Flight f1= new Flight();
			f1.read(scan);
			this.arrivals.add(i, f1);
		}
	}

	public String showDepartures() {
		if (departures.size() == 0) {
			String str="No departures yet";
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
			String str="No arrivals yet";
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
	
	Comparator<Flight> compareByDate=new Comparator<Flight>() {
		public int compare(Flight flight1,Flight flight2) {
			return flight1.getDateAndTime().compareTo(flight2.getDateAndTime());
		 }
	};
	
	private void sortFlights(List <Flight> flights) {
		Collections.sort(flights,compareByDate);
	}
	
	
	public String toString() {
		return "Airport: " + name +"has "+ departures.size()+" departures planned and "+arrivals.size()+
				"arrivals planned";
	}

	public String searchFlights(Scanner scan) {
		SearchEngine helper= new SearchEngine(scan);
		List<Flight> flightAfterSearch=new ArrayList<Flight>();;
		for (int i = 0; i < arrivals.size(); i++) {
			Flight temp=new Flight(arrivals.get(i));
			flightAfterSearch.add(temp);
		}
		for (int i = 0; i < departures.size(); i++) {
			Flight temp=new Flight(departures.get(i));
			flightAfterSearch.add(temp);
		}
		
		if(helper.isByAirline()) {
			flightAfterSearch=searchByAirline(helper.getAirline(), flightAfterSearch);
		}
		if(helper.isByDate()) {
			flightAfterSearch=searchByDate(helper.getDatefirst(),helper.getDatelast(), flightAfterSearch);
		}
		if(helper.isByDestenation()) {
			flightAfterSearch=searchByDestenation(helper.getDestenation(), flightAfterSearch);
		}
		if(helper.isByFlighNumber()) {
			flightAfterSearch=searchByFlightNumner(helper.getFlightNumber(), flightAfterSearch);
		}
		if(helper.isByKind()) {
			flightAfterSearch=searchByKind(helper.getKind(), flightAfterSearch);
		}
		if(helper.isByOrigin()) {
			flightAfterSearch=searchByOrigin(helper.getOrigin(), flightAfterSearch);
		}
		StringBuffer sb= new StringBuffer();
		if(flightAfterSearch.size()!=0) {
			sb.append("Here are all the flights according to your search:\n");
			for (int i = 0; i < flightAfterSearch.size(); i++) {
				sb.append(flightAfterSearch.get(i).toString());
			}
		}else {
			sb.append("No flights found");
		}
		
		return sb.toString();
	}
	public String searchFlights(boolean byKind, boolean byAirline, boolean byOrigin, boolean ByDestenation ,
			boolean byFlighNumber, boolean byDate, String kind, String airline, String origin, String destenation,
			String flightNumber,String datefirst, String dateLast) {
		SearchEngine helper= new SearchEngine( byKind,  byAirline,  byOrigin,  ByDestenation ,
				 byFlighNumber,  byDate,  kind,  airline,  origin,  destenation,
				 flightNumber, datefirst,  dateLast);
		List<Flight> flightAfterSearch=new ArrayList<Flight>();;
		for (int i = 0; i < arrivals.size(); i++) {
			Flight temp=new Flight(arrivals.get(i));
			flightAfterSearch.add(temp);
		}
		for (int i = 0; i < departures.size(); i++) {
			Flight temp=new Flight(departures.get(i));
			flightAfterSearch.add(temp);
		}
		
		if(helper.isByAirline()) {
			flightAfterSearch=searchByAirline(helper.getAirline(), flightAfterSearch);
		}
		if(helper.isByDate()) {
			flightAfterSearch=searchByDate(helper.getDatefirst(),helper.getDatelast(), flightAfterSearch);
		}
		if(helper.isByDestenation()) {
			flightAfterSearch=searchByDestenation(helper.getDestenation(), flightAfterSearch);
		}
		if(helper.isByFlighNumber()) {
			flightAfterSearch=searchByFlightNumner(helper.getFlightNumber(), flightAfterSearch);
		}
		if(helper.isByKind()) {
			flightAfterSearch=searchByKind(helper.getKind(), flightAfterSearch);
		}
		if(helper.isByOrigin()) {
			flightAfterSearch=searchByOrigin(helper.getOrigin(), flightAfterSearch);
		}
		StringBuffer sb= new StringBuffer();
		if(flightAfterSearch.size()!=0) {
			sb.append("Here are all the flights according to your search:\n");
			for (int i = 0; i < flightAfterSearch.size(); i++) {
				sb.append(flightAfterSearch.get(i).toString());
			}
		}else {
			sb.append("No flights found");
		}
		return sb.toString();
	}
	
	public List<Flight> searchByKind(String kind,List<Flight> allFlights ) {
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
	
	
	public List<Flight> searchByAirline(String airline, List<Flight> allFlights ) {
		for (int i = 0; i < allFlights.size();i++) {
			if(!(allFlights.get(i).getAirline().equals(airline))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
		}
	
	
	public List<Flight> searchByOrigin(String origin, List<Flight> allFlights){
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getOrigin().equals(origin))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	public List<Flight> searchByDestenation(String destenation, List<Flight> allFlights){
		for (int i = 0; i < allFlights.size(); i++) {
			if (!(allFlights.get(i).getDestination().equals(destenation))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	public List<Flight> searchByFlightNumner(String flightNumber, List<Flight> allFlights){
		for (int i = 0; i < allFlights.size(); i++) {
			if(!(allFlights.get(i).getFlightNumber().equals(flightNumber))) {
				allFlights.remove(i);
				i--;
			}
		}
		return allFlights;
	}
	
	public List<Flight> searchByDate(String datefirst, String dateLast, List<Flight> allFlights){
		LocalDateTime fisrtDate = stringToDate(datefirst);
		LocalDateTime lastDate = stringToDate(dateLast);
		
		for (int i = allFlights.size()-1; i >= 0; i--) {
			if (!(allFlights.get(i).getDateAndTime().isAfter(fisrtDate)
					&& allFlights.get(i).getDateAndTime().isBefore(lastDate))) {
				allFlights.remove(i);
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
