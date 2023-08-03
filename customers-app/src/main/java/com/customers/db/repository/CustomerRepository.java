package com.customers.db.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.customers.db.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>,
    PagingAndSortingRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {


}
