package com.sharecom.sharecom_be.domain.desktop;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDesktopReq {
    private String serial;
    private String etc;
    private int cpuId;
    private int boardId;
    private int gpuId;
    private int ssdId;
    private int ramId;
    private int powerId;
    private int coolerId;
}
