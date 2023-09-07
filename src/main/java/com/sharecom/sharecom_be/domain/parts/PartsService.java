package com.sharecom.sharecom_be.domain.parts;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PartsService {

    private final PartsRepository partsRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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

        List<GetPartsDto> parts = partsRepository.findAllByTypeAndNameAndSerialAndBuyAt(type, getPartsParam.getName(), getPartsParam.getSerial(), localDate);

        return parts;
    }

    public String addParts(PostPartsReq postPartsReq) {



        LocalDate localDate;
        try {
            Date date = formatter.parse(postPartsReq.getBuyAt());
            localDate = new java.sql.Date(date.getTime())  // java.util.Date -> java.sql.Date
                    .toLocalDate();
        } catch (Exception e) {
            log.error(e.getMessage());
            localDate = null;
        }

        Parts parts = Parts.builder()
                .name(postPartsReq.getName())
                .serial(postPartsReq.getSerial())
                .buyAt(localDate)
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
}
