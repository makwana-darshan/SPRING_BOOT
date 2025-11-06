package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.ResponseStructure;
import com.flight.entity.Passenger;
import com.flight.service.PassengerService;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;

	// add passenger
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> savePassnger(@RequestBody Passenger passenger) {
		return passengerService.savePassnger(passenger);
	}

	// get all passenger
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
		return passengerService.getAllPassenger();
	}

	// get passenger by id
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassngerById(@PathVariable Integer id) {
		return passengerService.getPassngerById(id);
	}

	// update passenger
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatePassnger(@RequestBody Passenger passenger) {
		return passengerService.updatePassenger(passenger);
	}

	// get passenger by contact number
	@GetMapping("/contactnumber/{number}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(@PathVariable Long number) {
		return passengerService.getPassngerByContactNumber(number);
	}
	// get passenger page and sort

	@GetMapping("/pageandsort/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPageAndSort(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return passengerService.getPassengerByPageAndSort(pageNumber, pageSize, field);
	}
}
