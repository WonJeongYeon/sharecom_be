package com.sharecom.sharecom_be.domain.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class GetPartsDto {

    private int id;
    private String type;
    private String name;
    private String serial;
    private LocalDate buy_at;
    private boolean used_yn;
    private String etc;

    public GetPartsDto(int id, Parts.Type type, String name, String serial, LocalDate buy_at, boolean used_yn, String etc) {
        this.id = id;
        this.type = type.name();
        this.name = name;
        this.serial = serial;
        this.buy_at = buy_at;
        this.used_yn = used_yn;
        this.etc = etc;
    }
}
