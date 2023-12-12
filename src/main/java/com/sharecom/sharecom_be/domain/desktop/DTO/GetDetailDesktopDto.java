package com.sharecom.sharecom_be.domain.desktop.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sharecom.sharecom_be.domain.parts.Parts;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDetailDesktopDto {
    private int id;
    private String serial;
    private boolean usedYn;
    private LocalDateTime usedAt;
    private String etc;
    private PartsDto cpu;
    private PartsDto mainBoard;
    private PartsDto gpu;
    private PartsDto ssd;
    private PartsDto ram;
    private PartsDto cooler;
    private PartsDto power;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class PartsDto {
        private int id;
        private String name;
        private String serial;
        private String etc;
        private boolean usedYn;
    }
}
