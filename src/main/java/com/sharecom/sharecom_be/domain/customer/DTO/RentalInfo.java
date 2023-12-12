package com.sharecom.sharecom_be.domain.customer.DTO;

import com.sharecom.sharecom_be.domain.rental.entity.RentalLogs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RentalInfo {

    private int id;
    private String serial;
    private String etc;
    private RentalLogs.Type state;
    private LocalDate startDate;
    private LocalDate endDate;
    private int rentalId;
    private LocalDateTime insertAt;
}
