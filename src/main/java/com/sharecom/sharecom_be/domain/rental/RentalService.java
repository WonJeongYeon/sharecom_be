package com.sharecom.sharecom_be.domain.rental;

import com.sharecom.sharecom_be.domain.customer.Customer;
import com.sharecom.sharecom_be.domain.customer.CustomerRepository;
import com.sharecom.sharecom_be.domain.desktop.DesktopRepository;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.rental.entity.Rental;
import com.sharecom.sharecom_be.domain.rental.entity.RentalLogs;
import com.sharecom.sharecom_be.exception.BaseException;
import com.sharecom.sharecom_be.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;
    private final DesktopRepository desktopRepository;
    private final CustomerRepository customerRepository;
    private final RentalLogsRepository rentalLogsRepository;
    private final EntityManager entityManager;

    @Transactional
    @Modifying
    public int[] saveRental(PostRentalReq postRentalReq) {

        List<Integer> failures = new ArrayList<>();
        List<Integer> rentalId = new ArrayList<>();
        for (int i: postRentalReq.getPcArr()) {
            try {
                Desktop desktop = desktopRepository.findById(i).get();
                Customer customer = customerRepository.findById(postRentalReq.getCustomerId()).get();

                rentalId.add(rentalRepository.save(new Rental(postRentalReq.getStartDate(), postRentalReq.getEndDate(), postRentalReq.getEtc(), desktop, customer)).getId());
//                rentalId.add(rentalRepository.saveRental(postRentalReq.getCustomerId(), i, postRentalReq.getStartDate(), postRentalReq.getEndDate(), postRentalReq.getEtc()));
                desktopRepository.updateDesktop(true, i);
                customerRepository.updateCustomer(true, postRentalReq.getCustomerId());
            } catch (Exception e) {
                log.error(e.getMessage());
                failures.add(i);
            }
        }

        for (int i: rentalId) {
            rentalLogsRepository.saveRentalLogs("RESERVATION", i);
        }

        if (failures.size() == 0) {
            return rentalId.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        } else {
            throw new BaseException(BaseResponseStatus.FAILED_TO_SAVE_RENTAL);
        }
    }

    @Transactional
    public int updateRentalLogs(PostRentalLogsReq postRentalLogsReq) {

        return rentalLogsRepository.saveRentalLogs(postRentalLogsReq.getType(), postRentalLogsReq.getRentalId());
    }
}
