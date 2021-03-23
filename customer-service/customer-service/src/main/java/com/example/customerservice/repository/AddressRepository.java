package com.example.customerservice.repository;

import com.example.customerservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,String> {
}
