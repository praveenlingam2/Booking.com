package com.nseit.Booking.com.repository;

import com.nseit.Booking.com.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
