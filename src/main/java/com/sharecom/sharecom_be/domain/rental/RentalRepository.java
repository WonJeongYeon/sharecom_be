package com.sharecom.sharecom_be.domain.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Modifying
    @Query(value = "insert into rental (customer_id, desktop_id, start_date, end_date, etc) VALUES (?, ?, ?, ?, ?)",
            nativeQuery = true)
    int saveRental(int customerId, int desktopId, LocalDate startDate, LocalDate endDate, String etc);

}

