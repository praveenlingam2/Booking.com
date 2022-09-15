package com.nseit.Booking.com.service;

import com.nseit.Booking.com.exception.ResourceAlreadyExistException;
import com.nseit.Booking.com.exception.ResourceNotFoundException;

import com.nseit.Booking.com.model.BookUser;
import com.nseit.Booking.com.model.File;
import com.nseit.Booking.com.model.Hotel;
import com.nseit.Booking.com.repository.FileRepository;
import com.nseit.Booking.com.repository.HotelRepository;

import com.nseit.Booking.com.repository.UserRepository;
import com.nseit.Booking.com.request.HotelRequest;
import com.nseit.Booking.com.response.HotelResponse;
import com.nseit.Booking.com.utils.ImageUtility;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    public Hotel addRoom(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelRequest, hotel);
        BookUser bookUser = userRepository.findById(hotelRequest.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("U n F"));
        File file=fileRepository.findById(hotelRequest.getFile_id()).orElseThrow(()->new ResourceNotFoundException("file does not exist"));
        hotel.setBookUser(bookUser);
        hotel.setFile(file);
        hotel.setHotelName(hotelRequest.getHotelName());
        hotel.setRoomName(hotelRequest.getRoomName());
        hotel.setLocation(hotelRequest.getLocation());

        boolean isBookExists = hotelRepository.findByRoomName(hotelRequest.getRoomName()).isPresent();
        if (isBookExists)
            throw new ResourceAlreadyExistException("Room already exists.");
        return hotelRepository.save(hotel);
    }

    public void deleteRoom(Integer id) {
        hotelRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("No room with id "
                        + id));
        hotelRepository.deleteById(id);

    }

    public List<HotelResponse> viewAllRoom() {
        List<Hotel>hotels =hotelRepository.findAll();
        List<HotelResponse> hotelResponses=new ArrayList<>();
        for(Hotel hotel : hotels){
            HotelResponse hotelResponse=new HotelResponse();
            hotelResponse.setId((hotel.getId()));
            if(hotel.getFile() !=null && hotel.getFile().getImage()!=null){
                hotelResponse.setImage(ImageUtility.decompressImage(hotel.getFile().getImage()));
                System.out.println(hotel.getFile().getImage());
            }else {
                hotelResponses.add(hotelResponse);
            }
        }
        return hotelResponses;
    }

    public Hotel updateRoom(HotelRequest hotelRequest) {

        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelRequest, hotel);
        if (hotelRequest.getId() == null)
            throw new ResourceNotFoundException("No room with id "
                    + hotelRequest.getId());
        Hotel hotel1 = hotelRepository.findById(hotelRequest.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("No room with userid id "
                        + hotelRequest.getUser_id()));
        hotel.setId(hotel.getId());
        return hotelRepository.save(hotel);
    }

    public Hotel findUserById(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find book with id " + id));
    }
}
