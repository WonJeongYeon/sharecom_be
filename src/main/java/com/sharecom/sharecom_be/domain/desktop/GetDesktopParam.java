package com.sharecom.sharecom_be.domain.desktop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDesktopParam {

    private String serial;
    private Boolean usedYn;
}
