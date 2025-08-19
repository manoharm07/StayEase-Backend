package com.example.hostelManagement.service.hostel;


import com.example.hostelManagement.models.hostel.Hostel;
import com.example.hostelManagement.repository.hostel.HostelRepo;
import com.example.hostelManagement.repository.user.StudentRepo;
import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {

    @Autowired
    private HostelRepo hostelRepo;

    public Hostel createHostel(Hostel hostel) {
        return hostelRepo.save(hostel);
    }

    public Hostel updateHostel(Hostel hostel) {
        return hostelRepo.save(hostel);
    }

    public void deleteHostel(Integer hostelId) {
        hostelRepo.deleteById(hostelId);
    }

    public Hostel getHostelById(Integer hostelId) {
        return hostelRepo.findById(hostelId).orElse(null);
    }

    public List<Hostel> getAllHostels() {
        return hostelRepo.findAll();
    }

    public List<Hostel> getHostelsByFilter(String filterBy,String filterName) {
        if (filterBy.equalsIgnoreCase("location")) {
            return hostelRepo.findByLocationContainingIgnoreCase(filterName);
        } else if (filterBy.equalsIgnoreCase("fees")) {
            return hostelRepo.findByFeesLessThan(Integer.valueOf(filterName));
        } else if (filterBy.equalsIgnoreCase("name")){
            return hostelRepo.findByNameContainingIgnoreCase(filterName);
        }else{
            return hostelRepo.findAll();
        }
    }


}