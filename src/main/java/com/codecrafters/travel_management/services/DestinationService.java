package com.codecrafters.travel_management.services;

import com.codecrafters.travel_management.models.Destination;
import com.codecrafters.travel_management.repositories.DestinationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getDestinationById(Long id) {
        return destinationRepository.findById(id);
    }

    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public Destination updateDestination(Long id, Destination updatedDestination) {
        return destinationRepository.findById(id)
                .map(existingDestination -> {
                    BeanUtils.copyProperties(updatedDestination, existingDestination);
                    return destinationRepository.save(existingDestination);
                })
                .orElseThrow(() -> new IllegalArgumentException("Destination not found"));
    }

    public void deleteDestination(Long id) {
        if (!destinationRepository.existsById(id)) {
            throw new IllegalArgumentException("Destination not found");
        }
        destinationRepository.deleteById(id);
    }
}
