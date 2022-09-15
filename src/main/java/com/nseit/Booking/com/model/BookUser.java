package com.nseit.Booking.com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class BookUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;
    private String emailId;
    private Long phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookUser")
    private Set<BookOrder> bookOrder;

    @OneToMany(mappedBy = "bookUser",cascade = CascadeType.ALL)
    private Set<Hotel> hotels;

    @ManyToMany
    @JsonIgnore
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    public BookUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
