package io.nzbee.security.user;

import io.nzbee.entity.party.Party;
import io.nzbee.security.Encoders;
import io.nzbee.security.user.role.UserRole;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_", schema="security", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "pty_id")
    private Long Id;
    
	@NaturalId
	@Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "ACCOUNT_LOCKED") 
    private boolean accountLocked;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;  

    @Column(name = "ENABLED")
    private boolean enabled;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name="pty_id")
    private Party userParty;
    
    @Column(name = "is_using2fa")
    private boolean isUsing2FA;
    
    @Column(name = "secret")
    private String secret;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", schema="security", 
    		   joinColumns 			= @JoinColumn(name = "pty_id"), 
    		   inverseJoinColumns 	= @JoinColumn(name = "role_id"))
    private Set<UserRole> roles = new HashSet<UserRole>();
    
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> colNewAuth = new HashSet<GrantedAuthority>();
		
		 for(UserRole ur : this.getUserRoles()) {
	            colNewAuth.addAll(ur.getAuthorities());
	     }
		return colNewAuth;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	public Party getParty() {
		return userParty;
	}

	public void setParty(Party userParty) {
		this.userParty = userParty;
	}

	public void setPassword(String password) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(Encoders.userRounds);
		this.password = pe.encode(password);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }
    
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
	
	public void addUserRole(UserRole ur) {
		this.getUserRoles().add(ur);
	}
	
	public void removeUserRole(UserRole ur) {
		this.getUserRoles().remove(ur);
	}
	
	public Set<UserRole> getUserRoles() {
		return roles;
	}

	public void setUserRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	public Long getId() {
		return Id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return username != null && username.equals(((User) o).getUsername());
    }
 
    @Override
    public int hashCode() {
        return 32;
    }

}