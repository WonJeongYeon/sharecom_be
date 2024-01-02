package com.sharecom.sharecom_be.domain.parts;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RentalInformation {

    private String desktopSerial;
    private String desktopEtc;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String rentalEtc;

    public RentalInformation(String desktopSerial, String desktopEtc, String customerName, LocalDate startDate, LocalDate endDate, String rentalEtc) {
        this.desktopSerial = desktopSerial;
        this.desktopEtc = desktopEtc;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentalEtc = rentalEtc;
    }
}
