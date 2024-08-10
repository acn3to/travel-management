package com.codecrafters.travel_management.repositories;

import com.codecrafters.travel_management.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDestinationId(Long destinationId);
}
