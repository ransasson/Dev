package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import core.Airport;
import core.Flight;

public class ArrivalsTest {

	@Test
	public void sortingTest() {
		Airport airport=createAirport();
		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("Here are all the arrivals:\n");

		expectedResult.append("Arrival: airline name=United Airlines, origin city=New York, origin country=USA,origin airport=JFK, date and time=13/05/2020 12:45, day=WEDNESDAY, flight number=UA090, terminal=3\n");
		expectedResult.append("Arrival: airline name=Israir, origin city=Tabilisi, origin country=Georgia,origin airport=Tabilisi International Airport, date and time=01/06/2020 14:35, day=MONDAY, flight number=6H896, terminal=3\n");
		assertEquals(expectedResult.toString(),airport.showArrivals());
	}

	private Airport createAirport() {
		Airport airport= new Airport("Hithrow");
		Flight flight1= new Flight("Israir", "Tlv", "Tabilisi", 2020, 6, 1, 14, 35, "6H896", 3,"Tabilisi International Airport","Georgia");
		Flight flight2= new Flight("United Airlines", "Tlv", "New York", 2020, 5, 13, 12, 45, "UA090", 3,"JFK","USA");
		airport.addFlight(flight1);
		airport.addFlight(flight2);
		return airport;
	}
}
