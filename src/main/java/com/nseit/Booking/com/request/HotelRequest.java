package com.nseit.Booking.com.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequest {
    private Integer user_id;
    private Integer id;
    private String location;
    private String roomName;
    private String image;
    private Integer file_id;
    private String hotelName;
}
