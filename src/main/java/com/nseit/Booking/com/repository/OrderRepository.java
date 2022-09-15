package com.nseit.Booking.com.repository;

import com.nseit.Booking.com.model.BookOrder;
import com.nseit.Booking.com.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<BookOrder,Integer> {
    Optional<List<BookOrder>>findByBookUser(BookUser bookUser);
}
