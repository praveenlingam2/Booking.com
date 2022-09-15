package com.nseit.Booking.com.controller;

import com.nseit.Booking.com.model.Hotel;
import com.nseit.Booking.com.request.HotelRequest;
import com.nseit.Booking.com.response.APIResponse;
import com.nseit.Booking.com.response.HotelResponse;
import com.nseit.Booking.com.response.SuccessResponse;
import com.nseit.Booking.com.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000"})
@RequestMapping("/api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private APIResponse apiResponse;


    @PostMapping
    public ResponseEntity<APIResponse> addRom(@RequestBody HotelRequest hotelRequest){
        Hotel addRoom=hotelService.addRoom(hotelRequest);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addRoom);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<APIResponse> updateRoom(@RequestBody HotelRequest hotelRequest){
        Hotel updateRoom =hotelService.updateRoom(hotelRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updateRoom);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteRoom(@PathVariable Integer id) {
        hotelService.deleteRoom(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewRoom() {
        List<HotelResponse> hotels= hotelService.viewAllRoom();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(hotels);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> viewRoomById(@PathVariable Integer id) {
        Hotel hotel = hotelService.findUserById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(hotel);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
