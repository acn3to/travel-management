package com.codecrafters.travel_management.controllers;

import com.codecrafters.travel_management.dto.TripDTO;
import com.codecrafters.travel_management.models.Destination;
import com.codecrafters.travel_management.models.Trip;
import com.codecrafters.travel_management.services.DestinationService;
import com.codecrafters.travel_management.services.TripService;
import com.codecrafters.travel_management.mappers.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final DestinationService destinationService;
    private final TripMapper tripMapper;

    @Autowired
    public TripController(TripService tripService, DestinationService destinationService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.destinationService = destinationService;
        this.tripMapper = tripMapper;
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAllTrips() {
        try {
            List<TripDTO> tripDTOs = tripService.getAllTrips().stream().map(tripMapper::toDTO).toList();
            return ResponseEntity.ok(tripDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDTO> getTripById(@PathVariable Long id) {
        try {
            Optional<Trip> trip = tripService.getTripById(id);
            return trip.map(tripMapper::toDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO tripDTO) {
        try {
            Optional<Destination> destination = destinationService.getDestinationById(tripDTO.destinationId());

            if (destination.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Trip trip = tripMapper.toEntity(tripDTO);
            trip.setDestination(destination.get());

            Trip createdTrip = tripService.createTrip(trip);
            TripDTO createdDTO = tripMapper.toDTO(createdTrip);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDTO> updateTrip(@PathVariable Long id, @RequestBody TripDTO tripDTO) {
        try {
            Optional<Trip> existingTripOpt = tripService.getTripById(id);

            if (existingTripOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Optional<Destination> destination = destinationService.getDestinationById(tripDTO.destinationId());

            if (destination.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Trip updatedTrip = tripMapper.toEntity(tripDTO);
            updatedTrip.setId(id);
            updatedTrip.setDestination(destination.get());

            Trip savedTrip = tripService.updateTrip(id, updatedTrip);
            TripDTO updatedDTO = tripMapper.toDTO(savedTrip);
            return ResponseEntity.ok(updatedDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        try {
            tripService.deleteTrip(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
