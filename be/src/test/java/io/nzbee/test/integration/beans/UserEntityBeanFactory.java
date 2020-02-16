//package io.nzbee.test.integration.beans;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import io.nzbee.entity.party.person.Person;
//import io.nzbee.entity.role.customer.Customer;
//import io.nzbee.security.user.User;
//import io.nzbee.security.user.role.IUserRoleService;
//import io.nzbee.security.user.role.UserRole;
//
//@Service(value = "userEntityBeanFactory")
//@Profile(value = "dev")
//public class UserEntityBeanFactory {
//	
//	@Autowired
//	@Qualifier("userRoleService")
//	private IUserRoleService userRoleService; 
//
//	@Bean
//	@Transactional
//	public final User getUserEntityBean() {
//		
//		final Person person = new Person();
//		
//		person.setFamilyName("Test Family Name");
//		person.setGivenName("Test Given Name");
//
//		final Customer partyRole = new Customer();
//		partyRole.setRoleStart(new Date());
//		
//		person.addRole(partyRole);
//		partyRole.setRoleParty(person);
//		
//		User usr = new User();
//		usr.setUsername("testusername@testdomain.com");
//		usr.setPassword("test1234");
//		
//		person.setPartyUser(usr);
//		usr.setUserParty(person);
//		
//		UserRole userRole = userRoleService.findByName("Customer");
//		usr.addUserRole(userRole);
//		
//		return usr;
//	}
//	
//	
//	
//}
