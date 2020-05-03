package io.nzbee.security.user.role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.User;

@Entity
@Table(name = "ROLE", schema="security")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(						//the table that manages the many to many relationship
    								name = "ROLE_PERMISSION", schema="security", 
    								joinColumns 		= @JoinColumn(name = "role_id"), 
    								inverseJoinColumns 	= @JoinColumn(name = "permission_id"))
    @JsonIgnore
    private List<Authority> authorities = new ArrayList<Authority>();

	@ManyToMany(fetch = FetchType.LAZY,
				mappedBy = "roles")
    private List<User> Users = new ArrayList<User>();

	@Id
    @Column(name = "id")
    private Long Id;

	@NaturalId
    @Column(name = "NAME")
    private String name;
    
	public Long getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
    public List<Authority> getAuthorities() {
		return authorities;
	}
    
	public List<User> getUsers() {
		return Users;
	}
	
	public void addUser(User user) {
		this.getUsers().add(user);
		user.getUserRoles().add(this);
	}
	
	public void removeUser(User user) {
		this.getUsers().remove(user);
		user.getUserRoles().remove(this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole)) return false;
        return name != null && name.equals(((UserRole) o).getName());
    }
 
    @Override
    public int hashCode() {
        return 31;
    }	
}