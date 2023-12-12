package com.sharecom.sharecom_be.domain.desktop.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
@Entity
@Getter
@ToString
@Table(name = "desktop_logs")
public class DesktopLogs extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "desktop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Desktop desktopId;

    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 25)
    protected Type type;

    public enum Type {
        NEW_DESKTOP, UPDATE_DESKTOP, DELETE_DESKTOP, PARTS_CHANGED, RESTORE_DESKTOP
    }

    @Column(name = "updated_etc", columnDefinition = "TEXT")
    private String etc;

    @JoinColumn(name = "old_parts")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Parts oldParts;

    @JoinColumn(name = "new_parts")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Parts newParts;

    @Enumerated(EnumType.STRING)
    @Column(name = "parts_type", length = 25)
    protected Parts.Type partsType;

    @Builder
    public DesktopLogs(String reason, Desktop desktopId, Type type, String etc, Parts oldParts, Parts newParts, Parts.Type partsType) {
        this.reason = reason;
        this.desktopId = desktopId;
        this.type = type;
        this.etc = etc;
        this.oldParts = oldParts;
        this.newParts = newParts;
        this.partsType = partsType;
    }
}
