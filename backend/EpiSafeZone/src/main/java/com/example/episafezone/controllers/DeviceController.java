package com.example.episafezone.controllers;

import com.example.episafezone.models.Device;
import com.example.episafezone.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @PostMapping(path = "/create")
    Device createDevice(@RequestBody Device device) {
        return deviceService.create(device);
    }

}
