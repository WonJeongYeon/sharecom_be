package com.sharecom.sharecom_be.domain.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostPartsReq {
    private String type;
    private String name;
    private String serial;
    private String etc;
    private String buyAt;


}
