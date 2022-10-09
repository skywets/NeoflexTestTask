package com.example.neoflextesttask.controllers;

import com.example.neoflextesttask.model.VacationInfo;
import com.example.neoflextesttask.services.VacationCalcService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VacationCalcController {
    private final VacationCalcService service;
    private final Logger logger = LoggerFactory.getLogger(VacationCalcController.class);

    @GetMapping("/calculate")
    public Double calculate(@RequestBody VacationInfo vacationInfo) {
        logger.info("Calculate vacation pay with " + vacationInfo.toString());
        return service.calculateVacationPay(vacationInfo);
    }
}
