package com.codecrafters.travel_management.services;

import com.codecrafters.travel_management.models.Trip;
import com.codecrafters.travel_management.repositories.DestinationRepository;
import com.codecrafters.travel_management.repositories.TripRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final DestinationRepository destinationRepository;

    @Autowired
    public TripService(TripRepository tripRepository, DestinationRepository destinationRepository) {
        this.tripRepository = tripRepository;
        this.destinationRepository = destinationRepository;
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip createTrip(Trip trip) {
        if (!destinationRepository.existsById(trip.getDestination().getId())) {
            throw new IllegalArgumentException("Destination not found");
        }
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip updatedTrip) {
        return tripRepository.findById(id)
                .map(existingTrip -> {
                    if (!destinationRepository.existsById(updatedTrip.getDestination().getId())) {
                        throw new IllegalArgumentException("Destination not found");
                    }
                    BeanUtils.copyProperties(updatedTrip, existingTrip);
                    return tripRepository.save(existingTrip);
                })
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));
    }

    public void deleteTrip(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new IllegalArgumentException("Trip not found");
        }
        tripRepository.deleteById(id);
    }

    public List<Trip> getTripsByDestinationId(Long destinationId) {
        if (!destinationRepository.existsById(destinationId)) {
            throw new IllegalArgumentException("Destination not found");
        }
        return tripRepository.findByDestinationId(destinationId);
    }
}
