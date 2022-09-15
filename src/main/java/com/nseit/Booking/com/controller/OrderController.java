package com.nseit.Booking.com.controller;

import com.nseit.Booking.com.model.BookOrder;
import com.nseit.Booking.com.request.CancelOrderRequest;
import com.nseit.Booking.com.request.OrderRequest;
import com.nseit.Booking.com.response.APIResponse;
import com.nseit.Booking.com.response.SuccessResponse;
import com.nseit.Booking.com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3000"})
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private APIResponse apiResponse;

    @PostMapping
    public ResponseEntity<APIResponse> orderHotel(@RequestBody OrderRequest orderRequest){
        SuccessResponse successResponse= orderService.orderHotel(orderRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(successResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllOrders() {
        List<BookOrder> bookOrders=orderService.showAllOrder();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookOrders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public  ResponseEntity<APIResponse>viewOrderHistory(@PathVariable Integer userId){
        List<BookOrder>bookOrders=orderService.showOrderHistory(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(bookOrders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PostMapping("/cancelOrder")
    public ResponseEntity<APIResponse> cancelOrder(@RequestBody CancelOrderRequest cancelOrderRequest) {
        List<BookOrder> orders = orderService.cancelOrder(cancelOrderRequest.getOrderId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orders);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
