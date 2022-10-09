package com.example.neoflextesttask.services;

import com.example.neoflextesttask.model.VacationInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
class VacationCalcServiceImplTest {
    private VacationCalcService service;
    private Validator validator;

    @BeforeEach
    void init() {
        service = new VacationCalcServiceImpl();
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void calculateVacationPay_whenAvgSalaryAndDurationIsCorrect_thenReturnVacationPay() {
        VacationInfo info = new VacationInfo(50000.0, 31);
        Assertions.assertEquals(52901.02389078498, service.calculateVacationPay(info));
    }

    @Test
    void calculateVacationPay_whenAvgSalaryIsZero_thenCreateException() {
        VacationInfo info = new VacationInfo(0.0, 31);
        Assertions.assertFalse(validator.validate(info).isEmpty());
    }

    @Test
    void calculateVacationPay_whenDurationIsZero_thenCreateException() {
        VacationInfo info = new VacationInfo(50000.0, 0);
        Assertions.assertFalse(validator.validate(info).isEmpty());
    }

    @Test
    void calculateVacationPay_whenAvgSalaryIsNegative_thenCreateException() {
        VacationInfo info = new VacationInfo(-50000.0, 31);
        Assertions.assertFalse(validator.validate(info).isEmpty());
    }

    @Test
    void calculateVacationPay_whenAvgDurationIsNegative_thenCreateException() {
        VacationInfo info = new VacationInfo(50000.0, -31);
        Assertions.assertFalse(validator.validate(info).isEmpty());
    }
}