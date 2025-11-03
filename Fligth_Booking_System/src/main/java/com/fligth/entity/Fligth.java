package com.fligth.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Fligth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String airline;
	private String source;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Integer totalSeats;
	private Double price;
	
	@JsonIgnore
	@OneToMany
	private List<Booking> bookings;

}
