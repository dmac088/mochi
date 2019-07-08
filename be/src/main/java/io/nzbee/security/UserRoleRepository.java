package io.nzbee.security;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String roleName);
    
    Optional<UserRole> findById(Long id);
    
}