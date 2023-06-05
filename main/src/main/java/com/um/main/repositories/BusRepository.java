package com.um.main.repositories;

import com.um.main.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusRepository extends JpaRepository<Bus, Long> {
}

