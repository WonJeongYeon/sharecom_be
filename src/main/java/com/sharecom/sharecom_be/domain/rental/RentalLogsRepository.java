package com.sharecom.sharecom_be.domain.rental;

import com.sharecom.sharecom_be.domain.rental.entity.Rental;
import com.sharecom.sharecom_be.domain.rental.entity.RentalLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RentalLogsRepository extends JpaRepository<RentalLogs, Integer> {

    @Modifying
    @Query(value = "insert into rental_logs (type, rental_id) VALUES (?, ?)",
            nativeQuery = true)
    int saveRentalLogs(String type, int rentalId);

}

