package com.flight.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDateTime paymentDate;
	private Double amount;
	private String mode;
	private String status;

	@OneToOne(mappedBy = "payment")
	private Booking booking;
}
