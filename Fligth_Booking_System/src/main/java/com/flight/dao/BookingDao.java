package com.flight.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import com.flight.entity.Booking;
import com.flight.repository.BookingRepository;

@Repository
public class BookingDao {

	@Autowired
	private BookingRepository bookingRepository;

	// add Booking
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	// add multiple booking
	public List<Booking> saveAllBooking(List<Booking> bookings) {
		return bookingRepository.saveAll(bookings);
	}

	// get all booking
	public List<Booking> getAllBooking() {
		return bookingRepository.findAll();
	}

	// get booking by id
	public Optional<Booking> getBookingById(Integer id) {
		return bookingRepository.findById(id);
	}

	// delete booking
	public void deleteBooking(Booking booking) {
		bookingRepository.delete(booking);
	}

	// get booking by flight id
	public List<Booking> getBookingByFlightId(Integer flightId) {
		return bookingRepository.findByFlightId(flightId);
	}

	// get booking by status
	public List<Booking> getBookingByStatus(String status) {
		return bookingRepository.findByStatus(status);
	}

	// get booking by date
	public List<Booking> getBookingByDate(LocalDateTime date) {
		return bookingRepository.findByBookingDate(date);
	}

	// get booking by paging and sorting
	public Page<Booking> getBookingPageAndSort(Integer pageNumber, Integer pageSize, String field) {
		return bookingRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
	}
}
