package com.nseit.Booking.com.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Integer id;
    private String Location;

    @OneToOne(mappedBy = "file")
    private Hotel hotel;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

}
