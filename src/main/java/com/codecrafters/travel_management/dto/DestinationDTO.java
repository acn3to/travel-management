package com.codecrafters.travel_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DestinationDTO(

        Long id,

        @NotBlank
        @Size(max = 50)
        String name,

        @NotBlank
        @Size(max = 50)
        String country
) {}
