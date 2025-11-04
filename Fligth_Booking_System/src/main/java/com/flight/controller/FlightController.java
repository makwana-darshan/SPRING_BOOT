package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.ResponseStructure;
import com.flight.entity.Flight;
import com.flight.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	private FlightService flightService;

	// Add flight
	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> saveFlight(@RequestBody Flight flight) {
		return flightService.saveFlight(flight);
	}

	// add multiple flight
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> saveAllFlight(@RequestBody List<Flight> flight) {
		return flightService.saveAllFlight(flight);
	}

	// get all flight
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		return flightService.getAllFlight();
	}

	// get flight by id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable Integer id) {
		return flightService.getFlightById(id);
	}

	// get flight by source and destination
	@GetMapping("/sourceanddestination/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(@PathVariable String source,
			@PathVariable String destination) {
		return flightService.getFlightBySourceAndDestination(source, destination);
	}

	// get flight by airline
	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(
			@PathVariable String airline) {
		return flightService.getFlightByAirline(airline);
	}

	// get flight by id
	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@RequestBody Flight flight) {
		return flightService.updateFlight(flight);
	}

	// delete flight
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBook(@PathVariable Integer id) {
		return flightService.deleteFlight(id);
	}

	// paging and sorting
	@GetMapping("/paging/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Flight>>> getBooksByPaging(@PathVariable int pageNumber,
			@PathVariable int pageSize, @PathVariable String field) {
		return flightService.getFlightByPageAndSort(pageNumber, pageSize, field);
	}
}
