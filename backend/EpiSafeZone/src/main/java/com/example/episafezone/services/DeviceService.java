package com.example.episafezone.services;

import com.example.episafezone.models.Device;
import com.example.episafezone.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device create(Device device){
        return deviceRepository.save(device);
    }

    public List<Device> getAllFromUser(Integer userId){
        return deviceRepository.findDevicesByUser(userId);
    }
}
