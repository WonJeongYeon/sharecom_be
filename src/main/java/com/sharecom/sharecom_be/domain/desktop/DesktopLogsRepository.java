package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.desktop.DTO.GetDesktopDto;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.desktop.Entity.DesktopLogs;
import com.sharecom.sharecom_be.domain.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DesktopLogsRepository extends JpaRepository<DesktopLogs, Integer> {

//    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.DTO.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) AND (:usedYn is null or c.usedYn = :usedYn)")
//    List<GetDesktopDto> findAllBySerial(String serial


    @Modifying
    @Query(value = "insert into desktop_logs (reason, type, desktop_id, old_parts, new_parts, parts_type) VALUES(?,?,?,?,?,?)",
        nativeQuery = true)
    void saveDesktopLogs(String reason, String type, int desktopId, int oldParts, int newParts, String partsType);

}

