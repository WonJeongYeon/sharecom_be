package com.sharecom.sharecom_be.domain.desktop.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDeletedDesktopDto {
    private int id;
    private String serial;
    private LocalDateTime deletedAt;
}
