package com.nseit.Booking.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue
    private Integer id;
    private String hotelName;
    private String location;
    private String roomName;
    private String image;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private Set<BookOrder> bookOrders;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private BookUser bookUser;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

}
