package com.sharecom.sharecom_be.domain.parts;

import com.sharecom.sharecom_be.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PartsRepository extends JpaRepository<Parts, Integer> {

//    @Query("SELECT c FROM Parts c WHERE (:type is null or c.type = :type) and (:name is null"
//            + " or c.name LIKE CONCAT('%', :name, '%')) and (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) and (:buyAt is null or c.buyAt = :buyAt)")
//    List<GetPartsDto> findAllByTypeAndNameAndSerialAndBuyAt(Parts.Type type, String name, String serial, LocalDate buyAt);

    @Query("SELECT new com.sharecom.sharecom_be.domain.parts.GetPartsDto(c.id, c.type, c.name, c.serial, c.buyAt, c.usedYn, c.etc) FROM Parts c WHERE (:type is null or c.type = :type) and (:name is null"
            + " or c.name LIKE CONCAT('%', :name, '%')) and (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) and (:buyAt is null or c.buyAt = :buyAt) and (:etc is null or c.etc LIKE CONCAT('%', :etc, '%'))" +
            "and (:usedYn is null or c.usedYn = :usedYn) and c.state = :state")
    List<GetPartsDto> findAllByTypeAndNameAndSerialAndBuyAt(Parts.Type type, String name, String serial, LocalDate buyAt, String etc, Boolean usedYn, BaseEntity.State state);

    @Query("update Parts p set p.usedYn = :usedYn where p.id in (:list)")
    @Modifying
    @Transactional
    void updateParts(boolean usedYn, List<Integer> list);
}

