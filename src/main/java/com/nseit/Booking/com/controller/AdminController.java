package com.nseit.Booking.com.controller;

import com.nseit.Booking.com.model.Hotel;
import com.nseit.Booking.com.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private HotelService hotelService;
}
