package com.flight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.dto.ResponseStructure;
import com.flight.entity.Booking;
import com.flight.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	// add booking
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}

	// add multiple booking
	@PostMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> saveAllBooking(@RequestBody List<Booking> bookings) {
		return bookingService.saveAllBooking(bookings);
	}

	// get all booking
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		return bookingService.getAllBooking();
	}

	// Get booking by ID
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id) {
		return bookingService.getBookingById(id);
	}

	// Get booking by flight ID
	@GetMapping("/flight/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(@PathVariable Integer flightId) {
		return bookingService.getBookingByFlightId(flightId);
	}

	// Get booking by date
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable String date) {
		return bookingService.getBookingByDate(date);
	}

	// Get booking by status
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable String status) {
		return bookingService.getBookingByStatus(status);
	}

	// Delete booking by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer id) {
		return bookingService.deleteBooking(id);
	}

	// paging and sorting
	@GetMapping("/paging/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingPageAndSort(@PathVariable Integer pageNumber,
			@PathVariable Integer pageSize, @PathVariable String field) {
		return bookingService.getFlightByPageAndSort(pageNumber, pageSize, field);
	}

}
