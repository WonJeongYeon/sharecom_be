package com.sharecom.sharecom_be.domain.all;

import com.sharecom.sharecom_be.domain.customer.Customer;
import com.sharecom.sharecom_be.domain.customer.CustomerRepository;
import com.sharecom.sharecom_be.domain.customer.DTO.*;
import com.sharecom.sharecom_be.domain.desktop.DesktopRepository;
import com.sharecom.sharecom_be.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AllService {

    private final DesktopRepository desktopRepository;
    private final EntityManager entityManager;
    public List<GetAllDto> getAll() {

        javax.persistence.Query query = entityManager.createNativeQuery("select d.used_yn, d.serial, d.id AS desktopId, " +
                        "c.name, c.id AS customerId, " +
                        "r.start_date, r.end_date, r.etc, r.id AS rentalId, " +
                        "rl.type " +
                        "from desktop d " +
                        "left outer join rental r on r.desktop_id=d.id " +
                        "left outer join customer c on r.customer_id=c.id " +
                        "left outer join " +
                        "(select id, type, rental_id, ROW_NUMBER() OVER(PARTITION BY rental_id ORDER BY id desc) AS rn from rental_logs) rl " +
                        "on r.id=rl.rental_id where rn=1 or rn IS NULL order by used_yn desc");


//        List<Query> query = desktopRepository.getAllInfo();
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<GetAllDto> list = jpaResultMapper.list(query, GetAllDto.class);

        return list;
    }


}
