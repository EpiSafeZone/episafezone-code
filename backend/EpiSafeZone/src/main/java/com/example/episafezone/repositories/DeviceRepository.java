package com.example.episafezone.repositories;

import com.example.episafezone.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    List<Device> findDevicesByUser(Integer userId);
}
