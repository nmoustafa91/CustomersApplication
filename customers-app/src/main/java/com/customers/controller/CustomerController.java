package com.customers.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.customers.api.CustomersApi;
import com.customers.db.model.VersionModel;
import com.customers.etag.ItemETagResponseEntity;
import com.customers.model.BulkCreationRequestDTO;
import com.customers.model.BulkUpdateRequestDTO;
import com.customers.model.CreateCustomerRequestDTO;
import com.customers.model.CustomerDTO;
import com.customers.model.ListCustomersResponseDTO;
import com.customers.model.UpdateCustomerRequestDTO;
import com.customers.service.CustomerService;

import lombok.RequiredArgsConstructor;

/**
 * This is the controller layer to handle the client request for the customers api.
 */
@RestController
@RequiredArgsConstructor
public class CustomerController implements CustomersApi {

	private final CustomerService customerservice;

	@Override
	public ResponseEntity<CustomerDTO> createCustomer(CreateCustomerRequestDTO createCustomerRequestDTO) {
		CustomerDTO customerDTO = customerservice.createCustomer(createCustomerRequestDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
	}


	@Override
	public ResponseEntity<ListCustomersResponseDTO> getCustomers(String name, String firstName, List<UUID> customerIds, String city,
			String email, String search, Integer pageNumber, Integer pageSize, String sort) {
		ListCustomersResponseDTO listCustomersResponseDTO = customerservice.getCustomers(name, firstName, customerIds,
				city, search, email, PageRequest.of(pageNumber, pageSize, Sort.by(sort == null ? "created" : sort)));
		return ResponseEntity.ok(listCustomersResponseDTO);
	}

	@Override
	public ResponseEntity<List<CustomerDTO>> createCustomers(BulkCreationRequestDTO bulkCreationRequestDTO) {
		return null;
	}

	@Override
	public ResponseEntity<Void> deleteCustomer(UUID customerId) {
		customerservice.deleteCustomer(customerId);

		return ResponseEntity.noContent().build();
	}


	@Override
	public ResponseEntity<Void> deleteCustomers(List<UUID> customersIds) {
		customerservice.deleteCustomers(customersIds);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<ListCustomersResponseDTO> getAllCustomers(Integer pageNumber, Integer pageSize, String sort) {
		ListCustomersResponseDTO listCustomersResponseDTO = customerservice.getAllCustomers(PageRequest.of(pageNumber, pageSize, Sort.by(sort == null ? "created" : sort)));
		return ResponseEntity.ok(listCustomersResponseDTO);
	}

	@Override
	public ResponseEntity<CustomerDTO> getCustomer(UUID customerId) {
		VersionModel<CustomerDTO> itemDTOVersionModel = customerservice.getCustomer(customerId);

		return new ItemETagResponseEntity<>(itemDTOVersionModel).ok();

	}

	@Override
	public ResponseEntity<CustomerDTO> updateCustomer(UUID customerId, String ifMatch, UpdateCustomerRequestDTO updateCustomerRequestDTO) {
		CustomerDTO customerDTO = customerservice.updateCustomer(updateCustomerRequestDTO, customerId, ifMatch);
		return ResponseEntity.ok(customerDTO);

	}


	@Override
	public ResponseEntity<List<CustomerDTO>> updateCustomers(BulkUpdateRequestDTO bulkUpdateRequestDTO) {
		List<CustomerDTO> customerDTOList = customerservice.updateCustomers(bulkUpdateRequestDTO);
		return ResponseEntity.ok(customerDTOList);
	}

}