//package io.nzbee.entity.customer;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import io.nzbee.entity.role.Role;
//
//@Service
//public class CustomerService {
//
//	@Autowired
//	private CustomerCustomRepository customerRepository; 
//
//	
//	@PreAuthorize("hasAuthority('CUSTOMER_READER')")
//	@Transactional(readOnly = true)
//	public List<Customer> getAllCustomers() {
//		return customerRepository.findAll();
//	}
//
//	@PreAuthorize("hasAuthority('CUSTOMER_READ')")
//	@Transactional(readOnly = true)
//	public Optional<Role> getCustomer(String userName) {
//		return customerRepository.findByRolePartyPartyUserUsername(userName);
//	}
//	
//	@PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
//	@Transactional
//	public void addCustomer(Customer customer) {
//		customerRepository.save(customer);
//	}
//	
//	@PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
//	@Transactional
//	public void updateCustomer(Long id, Customer customer) {
//		customerRepository.save(customer);
//	}
//	
//	@PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
//	@Transactional
//	public void deleteCustomer(Long id) {
//		customerRepository.deleteById(id);
//	}
//	
//}
