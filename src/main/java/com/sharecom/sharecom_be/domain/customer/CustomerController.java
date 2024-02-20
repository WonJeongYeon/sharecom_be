package com.sharecom.sharecom_be.domain.customer;


import com.sharecom.sharecom_be.domain.customer.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DesktopService;
import com.sharecom.sharecom_be.domain.parts.PatchPartsDto;
import com.sharecom.sharecom_be.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("")
    @Operation(summary = "고객 목록 조회", description = "고객 목록을 조회합니다. 파라미터로 검색 조건을 활성화할 수 있습니다.")
    @ResponseBody
    public BaseResponse<List<GetAllCustomerDto>> getAllCustomers(GetCustomerParam getCustomerParam) {

        return new BaseResponse<>(customerService.getAllCustomer(getCustomerParam));
    }

    @PostMapping("")
    @Operation(summary = "고객 정보 등록", description = "고객 정보를 등록합니다.")
    @ResponseBody
    public BaseResponse<Integer> addCustomer(@RequestBody PostCustomerReq postCustomerReq) {
//        log.info(postCustomerReq.getName() + "씨는 " + postCustomerReq.getBirth() + "생일");
//        log.info(Arrays.toString(postCustomerReq.getPc()));

        int id = customerService.addCustomer(postCustomerReq);
//        int id = 5;
        return new BaseResponse<>(id);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "고객 상세정보 조회", description = "고객의 상세정보를 조회합니다. 대여정보가 포함됩니다.")
    @ResponseBody
    public BaseResponse<GetCustomerDto> getDetailCustomer(@PathVariable int customerId) {

        return new BaseResponse<>(customerService.getCustomerDetail(customerId));
    }

    @PatchMapping("/{customerId}")
    @Operation(summary = "고객 정보 변경", description = "고객 정보를 변경합니다.")
    @ResponseBody
    public BaseResponse<String> patchCustomer(@PathVariable int customerId, @RequestBody PatchCustomerDto patchCustomerDto) {
        try {
            customerService.patchCustomer(customerId, patchCustomerDto);
            return new BaseResponse<>("성공");
        } catch (Exception e) {
            return new BaseResponse<>("실패");
        }
    }
}
