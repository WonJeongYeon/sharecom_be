package com.sharecom.sharecom_be.domain.desktop;

import com.sharecom.sharecom_be.domain.desktop.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DTO.GetDetailDesktopDto;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.desktop.Entity.DesktopLogs;
import com.sharecom.sharecom_be.domain.parts.*;
import com.sharecom.sharecom_be.entity.BaseEntity;
import com.sharecom.sharecom_be.exception.BaseException;
import com.sharecom.sharecom_be.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DesktopService {

    private final DesktopRepository desktopRepository;
    private final PartsRepository partsRepository;
    private final DesktopLogsRepository desktopLogsRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public List<GetDesktopDto> getDesktop(GetDesktopParam getDesktopParam) throws ParseException {

        List<GetDesktopDto> desktopList = desktopRepository.findAllByUsedYn(getDesktopParam.getUsedYn(), BaseEntity.State.ACTIVE);

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
        int id;
        if (desktopRepository.existsBySerial(postDesktopReq.getSerial())) {
            return "이미 존재하는 본체 고유번호입니다.";
        }
        try {
            id = desktopRepository.saveDesktop(postDesktopReq.getSerial(),
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
            return result;
        }

        DesktopLogs desktopLogs = DesktopLogs.builder()
                .reason("새 PC 생성")
                .type(DesktopLogs.Type.NEW_DESKTOP)
                .desktopId(desktopRepository.findById(id).orElseThrow())
                .etc(null)
                .newParts(null)
                .oldParts(null)
                .partsType(null)
                .build();
        desktopLogsRepository.save(desktopLogs);

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

    public void modifyDesktop(int desktopId, PatchDesktopDto patchDesktopDto) {
        Desktop desktop = desktopRepository.findById(desktopId).orElseThrow();
        if (patchDesktopDto.getEtc() != null) {
            DesktopLogs desktopLogs = DesktopLogs.builder()
                    .reason(patchDesktopDto.getReason() == null ? "(내용 없음)" : patchDesktopDto.getReason())
                    .type(DesktopLogs.Type.UPDATE_DESKTOP)
                    .etc(patchDesktopDto.getEtc())
                    .newParts(null)
                    .oldParts(null)
                    .partsType(null)
                    .build();
            desktopLogsRepository.save(desktopLogs);
            desktop.updateEtc(patchDesktopDto.getEtc());
        }
        if (patchDesktopDto.getPartsType().length != 0) {
            String[] partsType = patchDesktopDto.getPartsType();
            int[] parts = patchDesktopDto.getPartsArr();
            int[] oldParts = patchDesktopDto.getOldPartsArr();
            for (int i = 0; i<partsType.length; i++) {
                desktopLogsRepository.saveDesktopLogs(patchDesktopDto.getReason() == null ? "(내용 없음)" : patchDesktopDto.getReason(),
                        DesktopLogs.Type.PARTS_CHANGED.name(),
                        desktopId,
                        oldParts[i],
                        parts[i],
                        Parts.Type.valueOf(partsType[i]).name());
                desktop.updateParts(Parts.Type.valueOf(partsType[i]), partsRepository.findById(parts[i]).get());
            }
            partsRepository.updateParts(true, toList(parts));
            partsRepository.updateParts(false, toList(oldParts));
        }
    }

    private List<Integer> toList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i: arr) {
            list.add(i);
        }
        return list;
    }

    @Transactional
    public void deleteDesktop(int desktopId) {

        Desktop desktop = desktopRepository.findById(desktopId).orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_EXISTS_DESKTOP));
        if (desktop.isUsedYn()) {
            throw new BaseException(BaseResponseStatus.USED_DESKTOP);
        }
        desktop.deleteDesktop(BaseEntity.State.INACTIVE);

        List<Integer> list = new ArrayList<>();
        list.add(desktop.getBoardId().getId());
        list.add(desktop.getCoolerId().getId());
        list.add(desktop.getCpuId().getId());
        list.add(desktop.getGpuId().getId());
        list.add(desktop.getPowerId().getId());
        list.add(desktop.getRamId().getId());
        list.add(desktop.getSsdId().getId());
        partsRepository.updateParts(false, list);
    }
}
