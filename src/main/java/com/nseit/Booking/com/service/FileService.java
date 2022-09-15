package com.nseit.Booking.com.service;


import com.nseit.Booking.com.model.File;
import com.nseit.Booking.com.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File uploadFile(File file){
        return fileRepository.save(file);
    }

}
