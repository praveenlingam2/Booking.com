package com.nseit.Booking.com.repository;

import com.nseit.Booking.com.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Integer> {


    Optional<Object> findByRoomName(String roomName);
}
