package com.sharecom.sharecom_be.domain.rental;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRentalLogsReq {
    private String type;
    private int rentalId;

}
