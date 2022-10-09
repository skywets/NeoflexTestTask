package com.example.neoflextesttask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class VacationInfo {
    @Positive(message = "Average salary must be positive value.")
    private double avgSalary;
    @Positive(message = "Vacation duration must be positive value.")
    private int vacationDuration;
}
