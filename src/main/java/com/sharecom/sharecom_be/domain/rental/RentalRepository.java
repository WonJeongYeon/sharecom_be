package com.sharecom.sharecom_be.domain.rental;

import com.sharecom.sharecom_be.domain.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Modifying
    @Query(value = "insert into rental (customer_id, desktop_id, start_date, end_date, etc) VALUES (?, ?, ?, ?, ?)",
            nativeQuery = true)
    Integer saveRental(int customerId, int desktopId, LocalDate startDate, LocalDate endDate, String etc);

}

