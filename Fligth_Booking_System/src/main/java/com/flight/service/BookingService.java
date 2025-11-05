package com.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.dao.BookingDao;
import com.flight.dto.ResponseStructure;
import com.flight.entity.Booking;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(Booking booking) {

		ResponseStructure<Booking> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("data success..");
		response.setData(bookingDao.saveBooking(booking));

		return new ResponseEntity<ResponseStructure<Booking>>(response, HttpStatus.CREATED);
	}
}
