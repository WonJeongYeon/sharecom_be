package com.sharecom.sharecom_be.domain.customer.DTO;

import com.sharecom.sharecom_be.domain.rental.entity.RentalLogs;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Builder
@Getter
public class GetCustomerDto {
    private String name;
    private String address;
    private String phone;
    private String etc;
    private List<RentalInfo> desktop;


    public GetCustomerDto(String name, String address, String phone, String etc, List<RentalInfo> desktop) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.etc = etc;
        this.desktop = desktop;
    }

}
