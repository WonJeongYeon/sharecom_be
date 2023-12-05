package com.sharecom.sharecom_be.domain.customer.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class GetCustomerParam {
    private String name;
    private String phone;
    private String address;
    private String etc;
}
