package io.nzbee.security.user.role;

import java.io.Serializable;
import java.util.Set;
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

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLE", schema="security")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(						//the table that manages the many to many relationship
    								name = "ROLE_PERMISSION", schema="security", 
    								joinColumns 		= @JoinColumn(name = "role_id"), 
    								inverseJoinColumns 	= @JoinColumn(name = "permission_id"))
	@OrderBy
    @JsonIgnore
    private Set<Authority> authorities;

	@ManyToMany(mappedBy = "roles")
    private Set<User> Users;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

	@NaturalId
    @Column(name = "NAME")
    private String name;
    
    public UserRole() {
    	
    }
    
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public Set<Authority> getAuthorities() {
		return authorities;
	}
	
	public void addUser(User user) {
		this.Users.add(user);
		user.addUserRole(this);
	}
	
	public void removeUser(User user) {
		this.Users.remove(user);
		user.addUserRole(this);
	}
}