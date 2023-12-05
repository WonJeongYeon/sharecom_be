package com.sharecom.sharecom_be.domain.customer.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCustomerDto {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String etc;
    private LocalDate birth;
    private boolean rentalState;
}
