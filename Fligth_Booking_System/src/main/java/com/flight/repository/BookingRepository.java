package com.flight.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findByFlightId(Integer flightId);

	List<Booking> findByStatus(String status);

	List<Booking> findByBookingDate(LocalDateTime bookingDate);
}
