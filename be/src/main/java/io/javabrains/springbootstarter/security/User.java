package io.javabrains.springbootstarter.security;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.google.common.collect.Iterables;

import io.javabrains.springbootstarter.domain.Party;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_", schema="security", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
@Getter 
@Setter
public class User implements UserDetails, Serializable {

	
    @Id
    @Column(name = "pty_id")
    private Long Id;
    
	@Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED") 
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;  

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToOne
    @JsonBackReference
    @MapsId
    @JoinColumn(name="pty_id")
    private Party userParty;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", schema="security", 
    		   joinColumns 			= @JoinColumn(name = "pty_id"/*, referencedColumnName = "pty_id"*/), 
    		   inverseJoinColumns 	= @JoinColumn(name = "role_id"/*, referencedColumnName = "role_id"*/))
    @OrderBy
    //@JsonIgnore
    private Collection<UserRole> roles;
    
    public Collection<UserRole> getRoles() {
		return roles;
	}

	@Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    } 

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//create a new array and return it
		Collection<GrantedAuthority> colNewAuth = new ArrayList<GrantedAuthority>();
		
		 for(UserRole ur : roles) {
	            colNewAuth.addAll(ur.getAuthorities());
	     }
		return colNewAuth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public Party getUserParty() {
		return userParty;
	}

	public void setUserParty(Party userParty) {
		this.userParty = userParty;
	}

	public void setPassword(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(Encoders.userRounds);
		this.password  = pe.encode(password);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}