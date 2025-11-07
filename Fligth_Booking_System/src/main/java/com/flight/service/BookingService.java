package com.flight.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.dao.BookingDao;
import com.flight.dao.FlightDao;
import com.flight.dto.ResponseStructure;
import com.flight.entity.Booking;
import com.flight.entity.Flight;
import com.flight.entity.Passenger;
import com.flight.exception.IdNotFoundException;
import com.flight.exception.NoRecordAvailableException;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private FlightDao flightDao;

	// save booking
	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking) {

		ResponseStructure<Booking> response = new ResponseStructure<>();

		if (booking.getFlight() != null && booking.getFlight().getId() != null) {
			Optional<Flight> flightOpt = flightDao.getFlightById(booking.getFlight().getId());
			if (flightOpt.isEmpty()) {
				throw new IdNotFoundException("Flight not found for ID: " + booking.getFlight().getId());
			}
			booking.setFlight(flightOpt.get());
		} else {
			throw new IdNotFoundException("Flight ID is required for booking!");
		}

		if (booking.getPassengers() != null && !booking.getPassengers().isEmpty()) {
			for (Passenger passenger : booking.getPassengers()) {
				passenger.setBooking(booking);
			}
		}

		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("data success..");
		response.setData(bookingDao.saveBooking(booking));

		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.CREATED);
	}

	// save multiple booking
	public ResponseEntity<ResponseStructure<List<Booking>>> saveAllBooking(List<Booking> bookings) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("data success..");
		response.setData(bookingDao.saveAllBooking(bookings));

		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.CREATED);
	}

	// get all booking
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("data success..");
		response.setData(bookingDao.getAllBooking());

		return new ResponseEntity<ResponseStructure<List<Booking>>>(response, HttpStatus.CREATED);
	}

	// get booking by id
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(Integer id) {
		Optional<Booking> opt = bookingDao.getBookingById(id);
		ResponseStructure<Booking> response = new ResponseStructure<>();
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking found successfully!");
			response.setData(opt.get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Booking not found for ID: " + id);
		}
	}

	// Get booking by flight ID
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(Integer flightId) {
		List<Booking> bookings = bookingDao.getBookingByFlightId(flightId);
		if (bookings.isEmpty()) {
			throw new NoRecordAvailableException("No bookings found for flight ID: " + flightId);
		}
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Bookings retrieved successfully by flight ID!");
		response.setData(bookings);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Get booking by date
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(String dateTime) {
		LocalDateTime date = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		List<Booking> bookings = bookingDao.getBookingByDate(date);
		if (bookings.isEmpty()) {
			throw new NoRecordAvailableException("No bookings found for date: " + date);
		}
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Bookings retrieved successfully for date: " + date);
		response.setData(bookings);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Get booking by status
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(String status) {
		List<Booking> bookings = bookingDao.getBookingByStatus(status.toUpperCase());
		if (bookings.isEmpty()) {
			throw new NoRecordAvailableException("No bookings found with status: " + status);
		}
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Bookings retrieved successfully by status!");
		response.setData(bookings);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Delete booking
	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id) {
		Optional<Booking> opt = bookingDao.getBookingById(id);
		if (opt.isEmpty()) {
			throw new IdNotFoundException("Booking not found for ID: " + id);
		}
		bookingDao.deleteBooking(opt.get());
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Booking deleted successfully!");
		response.setData("Booking with ID " + id + " has been deleted.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// paging and sorting
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingByPageAndSort(int pageNumber, int pageSize,
			String field) {
		Page<Booking> bookings = bookingDao.getBookingPageAndSort(pageNumber, pageSize, field);
		ResponseStructure<Page<Booking>> response = new ResponseStructure<>();

		if (!bookings.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking retrieved successfully with pagination and sorting by " + field + "!");
			response.setData(bookings);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No Booking records found!");
		}
	}
}
