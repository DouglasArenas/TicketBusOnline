package com.um.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.um.main.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}