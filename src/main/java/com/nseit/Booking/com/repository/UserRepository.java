package com.nseit.Booking.com.repository;

import com.nseit.Booking.com.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BookUser,Integer> {
    BookUser findByUserName(String userName);
}
