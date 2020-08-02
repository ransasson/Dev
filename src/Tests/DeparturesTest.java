package Tests;
import static org.junit.Assert.*;
import org.junit.Test;

import core.Airport;
import core.Flight;

public class DeparturesTest {

	@Test
	public void sortingTest() {
		Airport airport=createAirport();
		StringBuffer expectedResult = new StringBuffer();
		expectedResult.append("Here are all the departures:\n");
		
		expectedResult.append("Departure: airlineName=El al, destination=New York, date and time= 20/05/2020 00:45,"
				+ " flight number=LY001, terminal=3\n");
		expectedResult.append("Departure: airlineName=El al, destination=London, date and time= 20/05/2020 10:10,"
				+ " flight number=LY315, terminal=3\n");
		assertEquals(expectedResult.toString(),airport.showDepartures());
	}

	private Airport createAirport() {
		Airport airport= new Airport("Hithrow");
		Flight flight1= new Flight("El al", "London", "Tlv", 2020, 5, 20, 10, 10, "LY315", 3,"Heathrow","England");
		Flight flight2= new Flight("El al", "New York", "Tlv", 2020, 5, 20, 00, 45, "LY001", 3,"JFK","USA");
		airport.addFlight(flight1);
		airport.addFlight(flight2);
		return airport;
	}

}
