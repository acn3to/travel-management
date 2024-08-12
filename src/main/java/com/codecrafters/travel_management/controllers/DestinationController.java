package com.codecrafters.travel_management.controllers;

import com.codecrafters.travel_management.dto.DestinationDTO;
import com.codecrafters.travel_management.dto.TripDTO;
import com.codecrafters.travel_management.models.Destination;
import com.codecrafters.travel_management.services.DestinationService;
import com.codecrafters.travel_management.services.TripService;
import com.codecrafters.travel_management.mappers.DestinationMapper;
import com.codecrafters.travel_management.mappers.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    private final TripService tripService;
    private final DestinationMapper destinationMapper;
    private final TripMapper tripMapper;

    @Autowired
    public DestinationController(DestinationService destinationService, TripService tripService, DestinationMapper destinationMapper, TripMapper tripMapper) {
        this.destinationService = destinationService;
        this.tripService = tripService;
        this.destinationMapper = destinationMapper;
        this.tripMapper = tripMapper;
    }

    @GetMapping
    public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
        try {
            List<DestinationDTO> destinationDTOs = destinationService.getAllDestinations().stream().map(destinationMapper::toDTO).toList();
            return ResponseEntity.ok(destinationDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDTO> getDestinationById(@PathVariable Long id) {
        try {
            Optional<Destination> destination = destinationService.getDestinationById(id);
            return destination.map(destinationMapper::toDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{destinationId}/trips")
    public ResponseEntity<List<TripDTO>> getTripsByDestinationId(@PathVariable Long destinationId) {
        try {
            List<TripDTO> tripDTOs = tripService.getTripsByDestinationId(destinationId).stream().map(tripMapper::toDTO).toList();
            return ResponseEntity.ok(tripDTOs);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
        try {
            Destination destination = destinationMapper.toEntity(destinationDTO);
            Destination createdDestination = destinationService.createDestination(destination);
            DestinationDTO createdDTO = destinationMapper.toDTO(createdDestination);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationDTO> updateDestination(@PathVariable Long id, @RequestBody DestinationDTO destinationDTO) {
        try {
            Optional<Destination> existingDestinationOpt = destinationService.getDestinationById(id);

            if (existingDestinationOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            Destination updatedDestination = destinationMapper.toEntity(destinationDTO);
            updatedDestination.setId(id);

            Destination savedDestination = destinationService.updateDestination(id, updatedDestination);
            DestinationDTO updatedDTO = destinationMapper.toDTO(savedDestination);
            return ResponseEntity.ok(updatedDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
        try {
            destinationService.deleteDestination(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
