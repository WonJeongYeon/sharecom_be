package com.sharecom.sharecom_be.domain.parts;

import com.sharecom.sharecom_be.domain.all.GetAllDto;
import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.Part;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PartsService {

    private final PartsRepository partsRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final EntityManager entityManager;

    public List<GetPartsDto> getParts(GetPartsParam getPartsParam) throws ParseException {


        // 문자열 -> Date

        LocalDate localDate;
        try {
            Date date = formatter.parse(getPartsParam.getBuy_at());
            localDate = new java.sql.Date(date.getTime())  // java.util.Date -> java.sql.Date
                    .toLocalDate();
        } catch (Exception e) {
            log.error(e.getMessage());
            localDate = null;
        }
        Parts.Type type;
        try {
            type = Parts.Type.valueOf(getPartsParam.getType());
        } catch (Exception e) {
            log.error(e.getMessage());
            type = null;
        }

        List<GetPartsDto> parts = partsRepository.findAllByTypeAndNameAndSerialAndBuyAt(type, getPartsParam.getName(), getPartsParam.getSerial(), localDate, getPartsParam.getEtc(), getPartsParam.getUsedYn(), BaseEntity.State.ACTIVE);

        return parts;
    }

    public List<GetPartsDto> getDeletedParts() {
        List<Parts> parts = partsRepository.findAllByState(BaseEntity.State.INACTIVE);
        List<GetPartsDto> list = new ArrayList<>();
        for (Parts p : parts) {
            list.add(new GetPartsDto(p.getId(), p.getType(), p.getName(), p.getSerial(), p.getBuyAt(), p.isUsedYn(), p.getEtc()));
        }
        return list;
    }

    public String restoreParts(int partsId) {
        try {
            Parts p = partsRepository.findById(partsId).orElseThrow();
            p.updateState(BaseEntity.State.ACTIVE);
        } catch (Exception e) {
            return "실패";
        }
        return "성공";
    }

    public GetDetailPartsDto getDetailParts(int partsId) {
        Parts parts = partsRepository.findById(partsId).orElseThrow();
        String typeQuery = switch (parts.getType().name()) {
            case "CPU" -> "cpu";
            case "GPU" -> "gpu";
            case "COOLER" -> "cooler";
            case "POWER" -> "power";
            case "MAIN_BOARD" -> "board";
            case "RAM" -> "ram";
            case "SSD" -> "ssd";
            default -> "";
        };
        javax.persistence.Query query = entityManager.createNativeQuery("select d.used_yn, d.serial, d.id AS desktopId, " +
                "c.name, c.id AS customerId, " +
                "r.start_date, r.end_date, r.etc, r.id AS rentalId, " +
                "rl.type " +
                "from desktop d " +
                "left outer join rental r on r.desktop_id=d.id " +
                "left outer join customer c on r.customer_id=c.id " +
                "left outer join " +
                "(select id, type, rental_id, ROW_NUMBER() OVER(PARTITION BY rental_id ORDER BY id desc) AS rn from rental_logs) rl " +
                "on r.id=rl.rental_id where (rn=1 or rn IS NULL) " +
                "AND d." + typeQuery + "_id = " + partsId
//                + "order by used_yn desc"
        );
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<GetAllDto> list = jpaResultMapper.list(query, GetAllDto.class);
        GetDetailPartsDto getDetailPartsDto = new GetDetailPartsDto(parts.getId(), parts.getType(), parts.getName(),
                parts.getSerial(), parts.getBuyAt(), parts.isUsedYn(), parts.getEtc(), list);
        return getDetailPartsDto;

    }

    public String addParts(PostPartsReq postPartsReq) {

        Parts parts = Parts.builder()
                .name(postPartsReq.getName())
                .serial(postPartsReq.getSerial())
                .buyAt(stringToLocalDate(postPartsReq.getBuyAt()))
                .etc(postPartsReq.getEtc())
                .type(Parts.Type.valueOf(postPartsReq.getType()))
                .usedAt(null)
                .usedYn(false)
                .build();
        String result;
        try {
            partsRepository.save(parts);
            result = "성공";
        } catch (Exception e) {
            result = "실패";
        }
        return result;
    }

    @Transactional
    public void patchParts(int partsId, PatchPartsDto patchPartsDto) {
        Parts parts = partsRepository.findById(partsId).orElseThrow();
        if (patchPartsDto.getSerial() != null) {
            parts.updateSerial(patchPartsDto.getSerial());
        }
        if (patchPartsDto.getName() != null) {
            parts.updateName(patchPartsDto.getName());
        }
        if (patchPartsDto.getEtc() != null) {
            parts.updateEtc(patchPartsDto.getEtc());
        }
        if (patchPartsDto.getType() != null) {
            parts.updateType(patchPartsDto.getType());
        }
        if (!parts.getBuyAt().equals(stringToLocalDate(patchPartsDto.getBuyAt()))) {
            parts.updateBuyAt(stringToLocalDate(patchPartsDto.getBuyAt()));
        }
        log.info(parts.toString());
    }

    @Transactional
    public void deleteParts(int partsId) {
        Parts parts = partsRepository.findById(partsId).orElseThrow();
        parts.updateState(BaseEntity.State.INACTIVE);
    }

    private LocalDate stringToLocalDate(String dateStr) {
        LocalDate localDate;
        try {
            Date date = formatter.parse(dateStr);
            localDate = new java.sql.Date(date.getTime())  // java.util.Date -> java.sql.Date
                    .toLocalDate();
        } catch (Exception e) {
            log.error(e.getMessage());
            localDate = null;
        }
        return localDate;
    }
}
