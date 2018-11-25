package io.javabrains.springbootstarter.security;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE", schema="security")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserRole implements Serializable {
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(						//the table that manages the many to many relationship
    								name = "ROLE_PERMISSION", schema="security", 
    								joinColumns 		= @JoinColumn(name = "role_id"), 
    								inverseJoinColumns 	= @JoinColumn(name = "permission_id"))
    
	@OrderBy
    @JsonIgnore
    private Collection<Authority> authorities;

	@ManyToMany(mappedBy = "roles")
    private Collection<User> Users;
	
	
    public Collection<Authority> getAuthorities() {
		return authorities;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

}