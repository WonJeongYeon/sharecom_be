package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.parts.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DesktopService {

    private final DesktopRepository desktopRepository;
    private final PartsRepository partsRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public List<GetDesktopDto> getDesktop(GetDesktopParam getDesktopParam) throws ParseException {

        List<GetDesktopDto> desktopList = desktopRepository.findAllByUsedYn(getDesktopParam.getUsedYn());

        return desktopList;
    }

    public GetDetailDesktopDto getDetailDesktop(String serial) {
        Desktop desktop = desktopRepository.findBySerial(serial);
        Parts cpu = desktop.getCpuId();
        Parts mainBoard = desktop.getBoardId();
        Parts gpu = desktop.getGpuId();
        Parts ssd = desktop.getSsdId();
        Parts ram = desktop.getRamId();
        Parts power = desktop.getPowerId();
        Parts cooler = desktop.getCoolerId();
        GetDetailDesktopDto getDetailDesktopDto = GetDetailDesktopDto.builder()
                .id(desktop.getId())
                .serial(desktop.getSerial())
                .usedYn(desktop.isUsedYn())
                .usedAt(desktop.getUsedAt())
                .etc(desktop.getEtc())
                .cpu(mappingPartsDto(cpu))
                .power(mappingPartsDto(power))
                .ram(mappingPartsDto(ram))
                .ssd(mappingPartsDto(ssd))
                .cooler(mappingPartsDto(cooler))
                .gpu(mappingPartsDto(gpu))
                .mainBoard(mappingPartsDto(mainBoard))
                .build();

        return getDetailDesktopDto;
    }

    public String addDesktop(PostDesktopReq postDesktopReq) {
        String result;
        if (desktopRepository.existsBySerial(postDesktopReq.getSerial())) {
            return "이미 존재하는 본체 고유번호입니다.";
        }
        try {
            desktopRepository.saveDesktop(postDesktopReq.getSerial(),
                    postDesktopReq.getEtc(),
                    false,
                    postDesktopReq.getBoardId(),
                    postDesktopReq.getCoolerId(),
                    postDesktopReq.getCpuId(),
                    postDesktopReq.getGpuId(),
                    postDesktopReq.getPowerId(),
                    postDesktopReq.getRamId(),
                    postDesktopReq.getSsdId());
            List<Integer> partsList = new ArrayList<>();
            partsList.add(postDesktopReq.getBoardId());
            partsList.add(postDesktopReq.getCpuId());
            partsList.add(postDesktopReq.getGpuId());
            partsList.add(postDesktopReq.getSsdId());
            partsList.add(postDesktopReq.getRamId());
            partsList.add(postDesktopReq.getPowerId());
            partsList.add(postDesktopReq.getCoolerId());
            partsRepository.updateParts(true, partsList);
            result = "성공";
        } catch (Exception e) {
            log.error(e.getMessage());
            result = "실패";
        }

        return result;
    }

    private GetDetailDesktopDto.PartsDto mappingPartsDto(Parts parts) {
        return GetDetailDesktopDto.PartsDto.builder()
                .id(parts.getId())
                .name(parts.getName())
                .serial(parts.getSerial())
                .etc(parts.getEtc())
                .build();
    }
}
