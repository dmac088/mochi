package io.nzbee.security.user.role;

import java.io.Serializable;
import java.util.HashSet;
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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.entity.product.Product;
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
@Transactional
public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(						//the table that manages the many to many relationship
    								name = "ROLE_PERMISSION", schema="security", 
    								joinColumns 		= @JoinColumn(name = "role_id"), 
    								inverseJoinColumns 	= @JoinColumn(name = "permission_id"))
	@OrderBy
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<Authority>();

	@ManyToMany(mappedBy = "roles")
    private Set<User> Users = new HashSet<User>();

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
    
	public Set<User> getUsers() {
		return Users;
	}
	
	public void addUser(User user) {
		this.getUsers().add(user);
		user.addUserRole(this);
	}
	
	public void removeUser(User user) {
		this.getUsers().remove(user);
		user.removeUserRole(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return this.name != null && name.equals(((UserRole) o).getName());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }
}