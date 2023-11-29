package com.sharecom.sharecom_be.domain.customer;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sharecom.sharecom_be.domain.customer.DTO.GetAllCustomerDto;
import com.sharecom.sharecom_be.domain.customer.DTO.GetCustomerParam;
import com.sharecom.sharecom_be.domain.customer.DTO.PostCustomerReq;
import com.sharecom.sharecom_be.domain.desktop.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DesktopLogsRepository;
import com.sharecom.sharecom_be.domain.desktop.DesktopRepository;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.desktop.Entity.DesktopLogs;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.domain.parts.PartsRepository;
import com.sharecom.sharecom_be.domain.rental.QRental;
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
import java.util.ArrayList;
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
}
