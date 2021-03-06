package io.javabrains.springbootstarter.domain;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleCustomerService {

	@Autowired
	private RoleCustomerCustomRepository customerRepository; 

	
	@PreAuthorize("hasAuthority('CUSTOMER_READER')")
	@Transactional(readOnly = true)
	public List<RoleCustomer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
	@Transactional(readOnly = true)
	public Optional<Role> getCustomer(String userName) {
		return customerRepository.findByRolePartyPartyUserUsername(userName);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
	@Transactional
	public void addCustomer(RoleCustomer customer) {
		customerRepository.save(customer);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
	@Transactional
	public void updateCustomer(Long id, RoleCustomer customer) {
		customerRepository.save(customer);
	}
	
	@PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
	@Transactional
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
}
