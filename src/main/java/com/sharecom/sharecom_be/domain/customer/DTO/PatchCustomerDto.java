package com.sharecom.sharecom_be.domain.customer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchCustomerDto {
//    private int id;

    private String name;
    private String address;
    private String phone;
    private String birth;
    private String etc;


//    private LocalDate buyAt;
//    private boolean usedYn;
//    private LocalDate usedAt;
//    private Parts.Type type;

}
