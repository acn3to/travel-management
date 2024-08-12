package com.codecrafters.travel_management.mappers;

import com.codecrafters.travel_management.dto.DestinationDTO;
import com.codecrafters.travel_management.models.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationMapper {

    public DestinationDTO toDTO(Destination destination) {
        return new DestinationDTO(
                destination.getId(),
                destination.getName(),
                destination.getCountry()
        );
    }

    public Destination toEntity(DestinationDTO destinationDTO) {
        Destination destination = new Destination();
        destination.setName(destinationDTO.name());
        destination.setCountry(destinationDTO.country());
        return destination;
    }
}
