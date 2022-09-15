package com.nseit.Booking.com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    public static final String USER = "CUSTOMER";
    public static final String ROLE_USER = "ROLE_CUSTOMER";
    public static final String ADMIN = "ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String HOTEL = "HOTEL";
    public static final String ROLE_HOTEL = "ROLE_HOTEL";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<BookUser>bookUsers;

}
