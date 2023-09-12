package com.sharecom.sharecom_be.domain.desktop;


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
    public BaseResponse<List<GetDesktopDto>> getParts(GetDesktopParam getPartsParam) throws ParseException {
        log.warn(getPartsParam.toString());

        List<GetDesktopDto> getDesktopDto = desktopService.getDesktop(getPartsParam);


        return new BaseResponse<>(getDesktopDto);
    }
//
//    @PostMapping("")
//    @Operation(summary = "부품 추가", description = "부품을 추가합니다.")
//    @ResponseBody
//    public BaseResponse<String> addParts(@RequestBody PostPartsReq postPartsReq) {
//        String result = partsService.addParts(postPartsReq);
//        return new BaseResponse<>(result);
//    }
}
