package Tests;

import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;
import core.Airport;
import core.Flight;


public class SearchTest {
	Scanner scan= new Scanner(System.in);

	@Test
	public void searchByKindArrivalTest() {
		Airport airport=createAirport();
		StringBuffer expectedResult = new StringBuffer("Here are all the flights according to your search:\n");
		expectedResult.append("Arrival: airline name=Israir, origin city=Tabilisi, origin country=Georgia,origin airport=Tabilisi International Airport, date and time=01/06/2020 14:35, day=MONDAY, flight number=6H896, terminal=3\n" );
		
		assertEquals(expectedResult.toString(),airport.searchFlights(true,true,false,false,false,false,false,false,false,false,"Arrival","Israir","","","","","","","","",""));
	}
	
	
	

	private Airport createAirport() {
		Airport airport= new Airport("Hithrow");
		Flight flight1= new Flight("Israir", "Tlv", "Tabilisi", 2020, 6, 1, 14, 35, "6H896", 3,"Tabilisi International Airport","Georgia");
		Flight flight2= new Flight("United Airlines", "Tlv", "New York", 2020, 5, 13, 12, 45, "UA090", 3,"JFK","USA");
		Flight flight3= new Flight("El al", "London", "Tlv", 2020, 5, 20, 10, 10, "LY315", 3,"Heathrow","England");
		Flight flight4= new Flight("El al", "New York", "Tlv", 2020, 5, 20, 00, 45, "LY001", 3,"JFK","USA");
		airport.addFlight(flight1);
		airport.addFlight(flight2);
		airport.addFlight(flight4);
		airport.addFlight(flight3);
		
		return airport;
	}

}
