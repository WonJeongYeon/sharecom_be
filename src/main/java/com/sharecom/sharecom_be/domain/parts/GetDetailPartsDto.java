package com.sharecom.sharecom_be.domain.parts;

import com.sharecom.sharecom_be.domain.all.GetAllDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class GetDetailPartsDto {

    private int id;
    private String type;
    private String name;
    private String serial;
    private LocalDate buy_at;
    private boolean used_yn;
    private String etc;
    private List<GetAllDto> rentalData;

    public GetDetailPartsDto(int id, Parts.Type type, String name, String serial, LocalDate buy_at, boolean used_yn, String etc, List<GetAllDto> rentalData) {
        this.id = id;
        this.type = type.name();
        this.name = name;
        this.serial = serial;
        this.buy_at = buy_at;
        this.used_yn = used_yn;
        this.etc = etc;
        this.rentalData = rentalData;
    }
}
