package com.nseit.Booking.com.controller;

import com.nseit.Booking.com.model.BookUser;
import com.nseit.Booking.com.response.APIResponse;
import com.nseit.Booking.com.response.AuthResponse;
import com.nseit.Booking.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000/"})
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public UserService userService;
    @Autowired
    private APIResponse apiResponse;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody BookUser bookUser) {
        BookUser registeredUser = userService.registerAsCustomer(bookUser);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody BookUser bookUser) {
        BookUser loggedInUser = userService.loginAsCustomer(bookUser);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setId(loggedInUser.getId());
        authResponse.setUserName(loggedInUser.getUserName());
        authResponse.setRole((loggedInUser.getRoles().iterator().next().getName()));

        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(authResponse);
        return new ResponseEntity<>( apiResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookUser>> getAllUsers() {
        List<BookUser> bookUsers = userService.getAllUsers();
        if (bookUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookUsers, HttpStatus.OK);
    }

}
