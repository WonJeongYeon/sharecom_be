package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.all.GetAllDto;
import com.sharecom.sharecom_be.domain.desktop.DTO.GetDesktopDto;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Transactional
public interface DesktopRepository extends JpaRepository<Desktop, Integer> {


//    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.DTO.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:serial is null or c.serial LIKE CONCAT('%', :serial, '%')) AND (:usedYn is null or c.usedYn = :usedYn)")
//    List<GetDesktopDto> findAllBySerial(String serial

    @Query("SELECT new com.sharecom.sharecom_be.domain.desktop.DTO.GetDesktopDto(c.id, c.serial, c.usedYn, c.usedAt, c.etc) FROM Desktop c WHERE (:usedYn is null or c.usedYn = :usedYn) and (c.state = :state)")
    List<GetDesktopDto> findAllByUsedYn(Boolean usedYn, BaseEntity.State state);

    Desktop findBySerial(String serial);
    List<Desktop> findByState(BaseEntity.State state);

    @Modifying
    @Query(value = "insert into desktop (serial, etc, used_yn, board_id, cooler_id, cpu_id, gpu_id, power_id, ram_id, ssd_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
        nativeQuery = true)
    int saveDesktop(String serial, String etc, boolean used_yn, int board_id, int cooler_id, int cpu_id, int gpu_id, int power_id, int ram_id, int ssd_id);

    boolean existsBySerial(String serial);

    @Query("update Desktop c set c.usedYn = :usedYn where c.id = :id")
    @Modifying
    @Transactional
    void updateDesktop(boolean usedYn, int id);

    @Query(value = "select d.used_yn, d.serial, d.id AS desktopId, " +
            "c.name, c.id AS customerId, " +
            "r.start_date, r.end_date, r.etc, r.id AS rentalId, " +
            "rl.type " +
            "from desktop d " +
            "left outer join rental r on r.desktop_id=d.id " +
            "left outer join customer c on r.customer_id=c.id " +
            "left outer join " +
            "(select id, type, rental_id, ROW_NUMBER() OVER(PARTITION BY rental_id ORDER BY id desc) AS rn from rental_logs) rl " +
            "on r.id=rl.rental_id where rn=1 or rn IS NULL order by used_yn desc",
    nativeQuery = true)
    List<Query> getAllInfo();

//    @Query("SELECT new com.sharecom.sharecom_be.domain.all.GetAllDto(d.usedYn, d.serial, d.id, c.name, c.id, r.startDate, r.endDate, r.etc, r.id, rl.type) " +
//            "FROM Desktop d LEFT JOIN Rental r ON r.desktopId.id=d.id " +
//            "LEFT JOIN Customer c ON r.customerId.id = c.id " +
//            "LEFT JOIN (SELECT id, type, rental_id, )")
}

