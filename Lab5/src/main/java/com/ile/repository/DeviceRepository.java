package com.ile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ile.persistence.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
}