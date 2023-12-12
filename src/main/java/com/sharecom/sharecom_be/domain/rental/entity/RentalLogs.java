package com.sharecom.sharecom_be.domain.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@ToString
@Table(name = "rental_logs")
public class RentalLogs extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "rental_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Rental rentalId;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 25)
    protected Type type;

    public enum Type {
       RESERVATION, RENTAL, RETURN_NORMAL, RETURN_DELAYED, RETURN_ALREADY
    }



    @Builder
    public RentalLogs(Rental rentalId, Type type) {
        this.rentalId = rentalId;
        this.type = type;

    }
}
