package com.nseit.Booking.com.service;

import com.nseit.Booking.com.exception.ResourceNotFoundException;
import com.nseit.Booking.com.model.BookOrder;
import com.nseit.Booking.com.model.BookUser;
import com.nseit.Booking.com.model.Hotel;
import com.nseit.Booking.com.repository.HotelRepository;
import com.nseit.Booking.com.repository.OrderRepository;
import com.nseit.Booking.com.repository.UserRepository;
import com.nseit.Booking.com.request.OrderRequest;
import com.nseit.Booking.com.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;


    public List<BookOrder> showAllOrder() {
        return orderRepository.findAll();
    }

    public SuccessResponse orderHotel(OrderRequest orderRequest) {
        BookOrder bookOrder = new BookOrder();
        BookUser bookUser = userRepository.findById(orderRequest.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id" + orderRequest.getUser_id()));

        Hotel hotel = hotelRepository.findById(orderRequest.getHotel_id()).orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id" + orderRequest.getHotel_id()));

        bookOrder.setBookUser(bookUser);
        bookOrder.setHotel(hotel);

        return new SuccessResponse();

    }

    public List<BookOrder> showOrderHistory(Integer userId) {
        BookUser bookUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with id "
                        + userId));

        return orderRepository.findByBookUser(bookUser)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }

    public List<BookOrder> cancelOrder(Integer userId) {
        BookOrder bookOrder = orderRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Order does not exist with id"
                        + userId));
        bookOrder.setIsCancelled(true);
        orderRepository.save(bookOrder);

        return orderRepository.findByBookUser(bookOrder.getBookUser())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
    }

}
