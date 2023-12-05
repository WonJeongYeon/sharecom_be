package com.sharecom.sharecom_be.domain.customer.DTO;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCustomerReq {
    private String address;
    private String etc;
    private String name;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private int[] pc;

}
