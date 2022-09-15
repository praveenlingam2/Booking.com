package com.nseit.Booking.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class BookOrder {
    @Id
    @GeneratedValue
    private Integer id;
    private Boolean isCancelled = false;
    @Temporal(TemporalType.DATE)
    private Date Date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private BookUser bookUser;

    @ManyToOne
    @JoinColumn(name = "Hotel_id", referencedColumnName = "id")
    private Hotel hotel;


}
