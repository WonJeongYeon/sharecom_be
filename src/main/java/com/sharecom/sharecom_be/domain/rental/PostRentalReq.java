package com.sharecom.sharecom_be.domain.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRentalReq {
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String etc;
    private int[] pcArr;
}
