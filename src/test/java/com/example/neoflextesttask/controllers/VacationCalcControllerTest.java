package com.example.neoflextesttask.controllers;

import com.example.neoflextesttask.model.VacationInfo;
import com.example.neoflextesttask.services.VacationCalcService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationCalcController.class)
@AutoConfigureMockMvc
class VacationCalcControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VacationCalcService serviceMock;

    @Test
    void calculate_thenCallService() throws Exception {
        VacationInfo vacationInfo = new VacationInfo(50000.0, 31);

        mockMvc.perform(get("/calculate")
                        .content(objectMapper.writeValueAsString(vacationInfo))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(serviceMock, times(1)).calculateVacationPay(vacationInfo);
    }

    @Test
    void calculate_whenServiceThrowException_thenStatusIsBadRequest() throws Exception {
        VacationInfo vacationInfo = new VacationInfo(0.0, 0);
        doThrow(ConstraintViolationException.class).when(serviceMock).calculateVacationPay(vacationInfo);

        mockMvc.perform(get("/calculate"))
                .andExpect(status().isBadRequest());
    }
}