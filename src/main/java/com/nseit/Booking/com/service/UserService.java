package com.nseit.Booking.com.service;

import com.nseit.Booking.com.exception.ResourceAlreadyExistException;
import com.nseit.Booking.com.exception.ResourceNotFoundException;
import com.nseit.Booking.com.model.BookUser;
import com.nseit.Booking.com.model.Role;
import com.nseit.Booking.com.repository.RoleRepository;
import com.nseit.Booking.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public BookUser registerAsCustomer(BookUser bookUser) {
        BookUser user = userRepository.findByUserName(bookUser.getUserName());
        if (user != null) {
            throw new ResourceAlreadyExistException("User Already Exist");
        }

        Role role = roleRepository.findByName(Role.USER);
        bookUser.setRoles(Set.of(role));
        bookUser.setPassword(bCryptPasswordEncoder.encode(bookUser.getPassword()));
        return userRepository.save(bookUser);
    }

    public List<BookUser> getAllUsers() {
        return userRepository.findAll();
    }

    public BookUser loginAsCustomer(BookUser bookUser) {
        BookUser user = userRepository.findByUserName(bookUser.getUserName());

        if (user != null && bCryptPasswordEncoder.matches(bookUser.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new ResourceNotFoundException("Invalid username or password.");

        }
    }
}


