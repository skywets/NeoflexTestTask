package com.example.neoflextesttask.services;

import com.example.neoflextesttask.model.VacationInfo;

import javax.validation.Valid;

public interface VacationCalcService {
    double calculateVacationPay(@Valid VacationInfo vacationInfo);
}
