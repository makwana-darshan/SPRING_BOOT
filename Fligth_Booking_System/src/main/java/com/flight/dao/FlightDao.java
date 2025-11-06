package com.flight.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flight.entity.Flight;
import com.flight.repository.FlightRepository;


@Repository
public class FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	// add flight
	public Flight saveFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	// add multiple flight
	public List<Flight> saveAllFlight(List<Flight> flight) {
		return flightRepository.saveAll(flight);
	}

	// get all flight
	public List<Flight> getAllFlights() {
		return flightRepository.findAll();
	}

	// get flight by id
	public Optional<Flight> getFlightById(Integer id) {
		return flightRepository.findById(id);
	}

	// get flight by source and destination
	public List<Flight> getFlightBySourceAndDestination(String source, String destination) {
		return flightRepository.findBySourceAndDestination(source, destination);
	}

	// get flight by airline
	public List<Flight> getFlightByAirline(String airline) {
		return flightRepository.findByAirline(airline);
	}

	// update flight
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}

	//delete flight
	public void deleteFlight(Flight flight) {
		flightRepository.delete(flight);
	}
	
	// paging and sorting
	public Page<Flight> getFlightByPageAndSort(int pagenumber, int pagesize, String field) {
		return flightRepository.findAll(PageRequest.of(pagenumber, pagesize, Sort.by(field).ascending()));
	}
}
