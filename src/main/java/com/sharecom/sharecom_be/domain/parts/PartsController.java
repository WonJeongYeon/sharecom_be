package com.sharecom.sharecom_be.domain.parts;

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
@RequestMapping("/parts")
public class PartsController {

    private final PartsService partsService;

    @GetMapping("")
    @Operation(summary = "부품 목록 조회", description = "부품 목록을 조회합니다. 파라미터로 검색 조건을 활성화할 수 있습니다.")
    @ResponseBody
    public BaseResponse<List<GetPartsDto>> getParts(GetPartsParam getPartsParam) throws ParseException {
        log.warn(getPartsParam.toString());

        List<GetPartsDto> list = partsService.getParts(getPartsParam);

        return new BaseResponse<>(list);
    }

    @PostMapping("")
    @Operation(summary = "부품 추가", description = "부품을 추가합니다.")
    @ResponseBody
    public BaseResponse<String> addParts(@RequestBody PostPartsReq postPartsReq) {
        String result = partsService.addParts(postPartsReq);
        return new BaseResponse<>(result);
    }
}
