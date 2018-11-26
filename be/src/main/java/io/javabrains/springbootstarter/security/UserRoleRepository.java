package io.javabrains.springbootstarter.security;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.javabrains.springbootstarter.domain.Party;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String roleName);
    
    Optional<UserRole> findById(Long id);
    
    
}