package com.sharecom.sharecom_be.entity;


import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
@Getter
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "insertAt", nullable = false, updatable = false, columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updateAt", nullable = false, columnDefinition = "DATETIME default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

}
