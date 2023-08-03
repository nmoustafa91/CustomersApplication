package com.customers.db.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customers.db.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {


}
