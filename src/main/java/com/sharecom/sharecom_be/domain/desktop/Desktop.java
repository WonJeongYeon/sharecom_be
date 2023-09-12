package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.parts.Parts;
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
@Table(name = "desktop")
public class Desktop extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial", nullable = false, length = 50)
    private String serial;

    @Column(name = "used_yn", nullable = false, length = 50)
    private boolean usedYn;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Column(name = "etc", nullable = false, length = 50)
    private String etc;

    @JoinColumn(name = "cpu_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts cpuId;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts boardId;

    @JoinColumn(name = "gpu_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts gpuId;

    @JoinColumn(name = "ram_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts ramId;

    @JoinColumn(name = "power_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts powerId;

    @JoinColumn(name = "cooler_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts coolerId;

    @JoinColumn(name = "ssd_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Parts ssdId;

    @Builder
    public Desktop(String serial, boolean usedYn, LocalDateTime usedAt, String etc, Parts cpuId, Parts boardId, Parts gpuId, Parts ramId,
                   Parts powerId, Parts coolerId, Parts ssdId) {
        this.serial = serial;
        this.usedYn = usedYn;
        this.usedAt = usedAt;
        this.etc = etc;
        this.cpuId = cpuId;
        this.boardId = boardId;
        this.gpuId = gpuId;
        this.ramId = ramId;
        this.powerId = powerId;
        this.coolerId = coolerId;
        this.ssdId = ssdId;
    }
}
