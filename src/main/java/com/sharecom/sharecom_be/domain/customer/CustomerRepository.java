package com.sharecom.sharecom_be.domain.customer;

import com.sharecom.sharecom_be.domain.customer.DTO.GetAllCustomerDto;
import com.sharecom.sharecom_be.domain.desktop.Entity.Desktop;
import com.sharecom.sharecom_be.domain.parts.GetPartsDto;
import com.sharecom.sharecom_be.domain.parts.Parts;
import com.sharecom.sharecom_be.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT new com.sharecom.sharecom_be.domain.customer.DTO.GetAllCustomerDto(c.id, c.name, c.phone, c.address, c.etc, c.birth, c.rentalState) FROM Customer c WHERE (:name is null"
            + " or c.name LIKE CONCAT('%', :name, '%')) and (:phone is null or c.phone LIKE CONCAT('%', :phone, '%')) and (:address is null or c.address LIKE CONCAT('%', :address, '%')) and (:etc is null or c.etc LIKE CONCAT('%', :etc, '%'))" +
            " and c.state = :state ORDER BY c.name")
    List<GetAllCustomerDto> findAllByAny(String name, String phone, String address, String etc, BaseEntity.State state);

}

