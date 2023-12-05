package com.sharecom.sharecom_be.domain.rental;

import com.sharecom.sharecom_be.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    @PostMapping("")
    @Operation(summary = "대여정보 등록", description = "대여정보를 등록합니다. 대여할 PC는 다수일 수 있습니다.")
    @ResponseBody
    public BaseResponse<String> saveRental(@RequestBody PostRentalReq postRentalReq) {

        int code = rentalService.saveRental(postRentalReq);

        return new BaseResponse<>(code + "");
    }
}
