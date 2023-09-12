package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.parts.GetPartsDto;
import com.sharecom.sharecom_be.domain.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DesktopRepository extends JpaRepository<Desktop, Integer> {

    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) AND (:usedYn is null or c.usedYn = :usedYn)")
    List<GetDesktopDto> findAllBySerial(String serial, boolean usedYn);
}

