package com.sharecom.sharecom_be.domain.desktop.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatchDesktopDto {
    private String reason;
    private String etc;
    private String[] partsType;
    private int[] partsArr;
    private int[] oldPartsArr;

}
