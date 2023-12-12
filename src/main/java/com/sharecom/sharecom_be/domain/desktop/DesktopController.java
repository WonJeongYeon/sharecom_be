package com.sharecom.sharecom_be.domain.desktop;


import com.sharecom.sharecom_be.domain.desktop.DTO.*;
import com.sharecom.sharecom_be.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/desktop")
public class DesktopController {


    private final DesktopService desktopService;

    @GetMapping("")
    @Operation(summary = "본체 목록 조회", description = "본체 목록을 조회합니다. 파라미터로 검색 조건을 활성화할 수 있습니다.")
    @ResponseBody
    public BaseResponse<List<GetDesktopDto>> getParts(GetDesktopParam getDesktopParam) throws ParseException {

        List<GetDesktopDto> getDesktopDto = desktopService.getDesktop(getDesktopParam);


        return new BaseResponse<>(getDesktopDto);
    }

    @GetMapping("/{serial}")
    @Operation(summary = "본체 상세 조회", description = "본체 상세정보를 조회합니다. String serial Required")
    @ResponseBody
    public BaseResponse<GetDetailDesktopDto> getDetailDesktop(@PathVariable String serial) {

        GetDetailDesktopDto desktop = desktopService.getDetailDesktop(serial);
        return new BaseResponse<>(desktop);
    }

    @PostMapping("")
    @Operation(summary = "신규 본체 등록", description = "신규 본체를 등록합니다.")
    @ResponseBody
    public BaseResponse<String> addDesktop(@RequestBody PostDesktopReq postDesktopReq) {
        String result = desktopService.addDesktop(postDesktopReq);
        return new BaseResponse<>(result);
    }

    @PatchMapping("/{desktopId}")
    @Operation(summary = "본체 정보 변경", description = "본체 정보를 변경합니다.")
    @ResponseBody
    public BaseResponse<String> modifyDesktop(@PathVariable int desktopId, @RequestBody PatchDesktopDto patchDesktopDto) {

        String result;
        try {
            desktopService.modifyDesktop(desktopId, patchDesktopDto);
            result = "성공";
        } catch (Exception e) {
            result = "실패";
        }
        return new BaseResponse<>(result);
    }

    @DeleteMapping("/{desktopId}")
    @Operation(summary = "본체 정보 삭제", description = "본체 정보를 삭제합니다.")
    @ResponseBody
    public BaseResponse<String> deleteDesktop(@PathVariable int desktopId) {

        desktopService.deleteDesktop(desktopId);
        return new BaseResponse<>("");
    }

    @GetMapping("/deleted")
    @Operation(summary = "삭제된 본체 조회", description = "삭제된 본체를 조회합니다.")
    @ResponseBody
    public BaseResponse<List<GetDeletedDesktopDto>> getDeletedDesktop() {

        return new BaseResponse<>(desktopService.getDeletedDesktop());
    }

    @PatchMapping("/restore/{desktopId}")
    @Operation(summary = "삭제된 본체 복구", description = "삭제된 본체를 복구합니다.")
    @ResponseBody
    public BaseResponse<String> restoreDesktop(@PathVariable int desktopId) {
        return new BaseResponse<>(desktopService.restoreDesktop(desktopId));

    }

    @GetMapping("/logs/{serial}")
    @Operation(summary = "본체 기록 조회", description = "본체 관리 기록을 조회합니다.")
    @ResponseBody
    public BaseResponse<List<GetDesktopLogsDto>> getDetailDesktopLogs(@PathVariable String serial) {

        return new BaseResponse<>(desktopService.getDetailDesktopLogs(serial));
    }
}
