package com.example.neoflextesttask.services;

import com.example.neoflextesttask.model.VacationInfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@NoArgsConstructor
public class VacationCalcServiceImpl implements VacationCalcService {
    @Override
    public double calculateVacationPay(@Valid VacationInfo vacationInfo) {
        // Рассчет по формуле Отпускные = Среднедневной зароботок х Количество календарных дней отпуска
        // Среднемесячное число календарных дней считается равным 29.3
        double salary = vacationInfo.getAvgSalary();
        int duration = vacationInfo.getVacationDuration();
        return salary / 29.3 * duration;
    }
}
