package com.sharecom.sharecom_be.domain.rental;

import com.sharecom.sharecom_be.domain.customer.CustomerRepository;
import com.sharecom.sharecom_be.domain.desktop.DesktopRepository;
import com.sharecom.sharecom_be.exception.BaseException;
import com.sharecom.sharecom_be.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentalService {

    private final RentalRepository rentalRepository;
    private final DesktopRepository desktopRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public int saveRental(PostRentalReq postRentalReq) {

        List<Integer> failures = new ArrayList<>();
        for (int i: postRentalReq.getPcArr()) {
            try {
                rentalRepository.saveRental(postRentalReq.getCustomerId(), i, postRentalReq.getStartDate(), postRentalReq.getEndDate(), postRentalReq.getEtc());
                desktopRepository.updateDesktop(true, i);
                customerRepository.updateCustomer(true, postRentalReq.getCustomerId());
            } catch (Exception e) {
                log.error(e.getMessage());
                failures.add(i);
            }
        }
        if (failures.size() == 0) {
            return 1;
        } else {
            throw new BaseException(BaseResponseStatus.FAILED_TO_SAVE_RENTAL);
        }
    }
}
