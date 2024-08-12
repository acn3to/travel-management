package com.codecrafters.travel_management.mappers;

import com.codecrafters.travel_management.dto.TripDTO;
import com.codecrafters.travel_management.models.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {

    public TripDTO toDTO(Trip trip) {
        return new TripDTO(
                trip.getId(),
                trip.getTitle(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getDestination().getId()
        );
    }

    public Trip toEntity(TripDTO tripDTO) {
        Trip trip = new Trip();
        trip.setTitle(tripDTO.title());
        trip.setStartDate(tripDTO.startDate());
        trip.setEndDate(tripDTO.endDate());
        return trip;
    }
}
