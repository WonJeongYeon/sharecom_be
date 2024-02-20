package com.sharecom.sharecom_be.domain.customer;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sharecom.sharecom_be.domain.customer.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DesktopLogsRepository;
import com.sharecom.sharecom_be.domain.desktop.DesktopRepository;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.desktop.Entity.DesktopLogs;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.domain.parts.PartsRepository;
import com.sharecom.sharecom_be.domain.parts.PatchPartsDto;
import com.sharecom.sharecom_be.entity.BaseEntity;
import com.sharecom.sharecom_be.exception.BaseException;
import com.sharecom.sharecom_be.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public List<GetAllCustomerDto> getAllCustomer(GetCustomerParam getCustomerParam) {

        return customerRepository.findAllByAny(getCustomerParam.getName(), getCustomerParam.getPhone(), getCustomerParam.getAddress(), getCustomerParam.getEtc(), BaseEntity.State.ACTIVE);
    }

    public int addCustomer(PostCustomerReq postCustomerReq) {
        Customer customer = new Customer(postCustomerReq.getName(), postCustomerReq.getEtc(), postCustomerReq.getAddress(), postCustomerReq.getPhone(), postCustomerReq.getBirth(), false);
        customerRepository.save(customer);
        return customer.getId();
    }

    public GetCustomerDto getCustomerDetail(int customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<RentalInfo> list = customerRepository.getCustomerByCustomerId(customerId);
        return GetCustomerDto.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .etc(customer.getEtc())
                .desktop(list)
                .build();

    }

    @Transactional
    public void patchCustomer(int customerId, PatchCustomerDto patchCustomerDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        if (patchCustomerDto.getName() != null) {
            customer.updateName(patchCustomerDto.getName());
        }
        if (patchCustomerDto.getAddress() != null) {
            customer.updateAddress(patchCustomerDto.getAddress());
        }
        if (patchCustomerDto.getPhone() != null) {
            customer.updatePhone(patchCustomerDto.getPhone());
        }
        if (patchCustomerDto.getEtc() != null) {
            customer.updateEtc(patchCustomerDto.getEtc());
        }
        if (!customer.getBirth().equals(stringToLocalDate(patchCustomerDto.getBirth()))) {
            customer.updateBirth(stringToLocalDate(patchCustomerDto.getBirth()));
        }
        log.info(customer.toString());
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
