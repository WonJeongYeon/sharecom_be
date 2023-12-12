package com.sharecom.sharecom_be.domain.all;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllDto {
    private Boolean used_yn;
    private String serial;
    private Integer desktopId;
    private String name;
    private Integer customerId;
    private Date start_date;
    private Date end_date;
    private String etc;
    private Integer rentalId;
    private String type;
}
