package com.sharecom.sharecom_be.domain.all;


import com.sharecom.sharecom_be.domain.customer.CustomerService;
import com.sharecom.sharecom_be.domain.customer.DTO.GetAllCustomerDto;
import com.sharecom.sharecom_be.domain.customer.DTO.GetCustomerDto;
import com.sharecom.sharecom_be.domain.customer.DTO.GetCustomerParam;
import com.sharecom.sharecom_be.domain.customer.DTO.PostCustomerReq;
import com.sharecom.sharecom_be.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/all")
public class AllController {

    private final AllService allService;

    @GetMapping("")
    @Operation(summary = "종합관리 조회", description = "기술의 집합체")
    @ResponseBody
    public BaseResponse<List<GetAllDto>> getAll() {

        return new BaseResponse<>(allService.getAll());
    }


}
