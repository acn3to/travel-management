package com.codecrafters.travel_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TripDTO(

        Long id,

        @NotBlank
        @Size(max = 150)
        String title,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate,

        @NotNull
        Long destinationId
) {}
