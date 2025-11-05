package com.flight.dao;

import org.springframework.beans.factory.annotation.Autowired;
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

}
