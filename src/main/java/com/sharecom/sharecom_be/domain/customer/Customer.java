package com.sharecom.sharecom_be.domain.customer;

import com.sharecom.sharecom_be.domain.desktop.Entity.DesktopLogs;
import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@ToString
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "etc", columnDefinition = "TEXT")
    private String etc;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth")
    private LocalDate birth;


    @Column(name = "rental_state")
    private boolean rentalState;

    public Customer(String name, String etc, String address, String phone, LocalDate birth, boolean rentalState) {
        this.name = name;
        this.etc = etc;
        this.address = address;
        this.phone = phone;
        this.birth = birth;
        this.rentalState = rentalState;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEtc(String etc) {
        this.etc = etc;
    }



}
