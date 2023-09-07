package com.sharecom.sharecom_be.domain.parts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class GetPartsParam {
    private String type;
    private String name;
    private String serial;
    private String buy_at;
}
