package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.parts.GetPartsDto;
import com.sharecom.sharecom_be.domain.parts.Parts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface DesktopRepository extends JpaRepository<Desktop, Integer> {

//    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) AND (:usedYn is null or c.usedYn = :usedYn)")
//    List<GetDesktopDto> findAllBySerial(String serial

    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:usedYn is null or c.usedYn = :usedYn)")
    List<GetDesktopDto> findAllByUsedYn(Boolean usedYn);

    Desktop findBySerial(String serial);

    @Modifying
    @Query(value = "insert into desktop (serial, etc, used_yn, board_id, cooler_id, cpu_id, gpu_id, power_id, ram_id, ssd_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        nativeQuery = true)
    void saveDesktop(String serial, String etc, boolean used_yn, int board_id, int cooler_id, int cpu_id, int gpu_id, int power_id, int ram_id, int ssd_id);
}

