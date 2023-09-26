package com.sharecom.sharecom_be.domain.parts;

import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@ToString
@Table(name = "item_dummy")
public class Parts extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial", nullable = false, length = 50)
    private String serial;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "etc", columnDefinition = "TEXT")
    private String etc;

    @Column(name = "buy_at")
    private LocalDate buyAt;

    @Column(name = "used_yn", nullable = false, columnDefinition = "tinyint(1)")
    private boolean usedYn;

    @Column(name = "used_at")
    private LocalDate usedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 25)
    protected Type type;

    public enum Type {
        CPU, GPU, MAIN_BOARD, RAM, SSD, POWER, COOLER
    }

    @Builder
    public Parts(String serial, String name, String etc, LocalDate buyAt, boolean usedYn, LocalDate usedAt, Type type) {
        this.serial = serial;
        this.name = name;
        this.etc = etc;
        this.buyAt = buyAt;
        this.usedYn = usedYn;
        this.usedAt = usedAt;
        this.type = type;
    }

    public void updateUsedYn(boolean usedYn) {
        this.usedYn = usedYn;
        this.usedAt = LocalDate.now();
    }


    public void updateSerial(String serial) {
        this.serial = serial;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEtc(String etc) {
        this.etc = etc;
    }

    public void updateState(State state) {
        this.state = state;
    }

    public void updateType(String type) {
        this.type = Type.valueOf(type);
    }
}
