package com.flight.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flight.dao.PassengerDao;
import com.flight.dto.ResponseStructure;
import com.flight.entity.Passenger;
import com.flight.exception.IdNotFoundException;
import com.flight.exception.NoRecordAvailableException;

@Service
public class PassengerService {

    @Autowired
    private PassengerDao passengerDao;

    // Save passenger
    public ResponseEntity<ResponseStructure<Passenger>> savePassnger(Passenger passenger) {
        ResponseStructure<Passenger> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Passenger record added successfully!");
        response.setData(passengerDao.savePassenger(passenger));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get all passengers
    public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
        List<Passenger> passengers = passengerDao.getAllPassengers();
        ResponseStructure<List<Passenger>> response = new ResponseStructure<>();

        if (passengers.isEmpty()) {
            throw new NoRecordAvailableException("No passenger records available!");
        }

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("All passenger records retrieved successfully!");
        response.setData(passengers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get passenger by ID
    public ResponseEntity<ResponseStructure<Passenger>> getPassngerById(Integer id) {
        Optional<Passenger> opt = passengerDao.getPassengerById(id);
        ResponseStructure<Passenger> response = new ResponseStructure<>();

        if (opt.isPresent()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Passenger record found successfully!");
            response.setData(opt.get());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Passenger not found for ID: " + id);
        }
    }

    // Update passenger
    public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger passenger) {
        Optional<Passenger> opt = passengerDao.getPassengerById(passenger.getId());
        ResponseStructure<Passenger> response = new ResponseStructure<>();

        if (opt.isPresent()) {
            Passenger updatedPassenger = passengerDao.updatePassenger(passenger);
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Passenger details updated successfully!");
            response.setData(updatedPassenger);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Cannot update — Passenger not found for ID: " + passenger.getId());
        }
    }

    // Get passenger by contact number
    public ResponseEntity<ResponseStructure<Passenger>> getPassngerByContactNumber(Long number) {
        Passenger passenger = passengerDao.getPassengerByContactNumber(number);
        ResponseStructure<Passenger> response = new ResponseStructure<>();

        if (passenger != null) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Passenger record found for contact number: " + number);
            response.setData(passenger);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Passenger not found for contact number: " + number);
        }
    }

    // Get passengers with pagination & sorting
    public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPageAndSort(
            int pageNumber, int pageSize, String field) {

        Page<Passenger> passengers = passengerDao.getPassengerByPageAndSort(pageNumber, pageSize, field);
        ResponseStructure<Page<Passenger>> response = new ResponseStructure<>();

        if (!passengers.isEmpty()) {
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Passengers retrieved successfully with pagination and sorting by " + field + "!");
            response.setData(passengers);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NoRecordAvailableException("No passenger records found!");
        }
    }
}
