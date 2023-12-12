package com.sharecom.sharecom_be.domain.desktop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDesktopLogsDto {
    private LocalDateTime insertAt;
    private String reason;
    private String type;
    private String content;
}
