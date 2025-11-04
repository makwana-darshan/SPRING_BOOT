package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
