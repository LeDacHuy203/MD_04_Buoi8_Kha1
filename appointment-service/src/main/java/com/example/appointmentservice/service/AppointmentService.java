package com.example.appointmentservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentService {
    @Autowired
    private RestTemplate restTemplate;
    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "doctorFallback")
    public String AddAppointment(){
//        Gia lap goi sang Doctor-Service o cong 8082
        String doctorUrl = "http://localhost:8082/doctors/check";
        return restTemplate.getForObject(doctorUrl, String.class);
    }
//    Fallback method khi Doctor-Service sap hoac mach dang OPEN
    public String doctorFallback(Throwable throwable){
        return "Doctor Service dang gap su co! Tam thoi khong the dat lich";
    }
}
