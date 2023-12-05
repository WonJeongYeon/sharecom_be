package com.sharecom.sharecom_be.domain.rental;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sharecom.sharecom_be.domain.customer.Customer;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.entity.BaseEntity;
import com.sharecom.sharecom_be.exception.BaseException;
import com.sharecom.sharecom_be.response.BaseResponseStatus;
import lombok.*;
import net.bytebuddy.utility.nullability.UnknownNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@ToString
@Table(name = "rental")
public class Rental extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "etc", nullable = false, length = 50)
    private String etc;

    @JoinColumn(name = "desktop_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Desktop desktopId;

    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customerId;

    @Builder
    public Rental(LocalDate startDate, LocalDate endDate, String etc, Desktop desktop, Customer customer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.etc = etc;
        this.desktopId = desktop;
        this.customerId = customer;
    }
}
