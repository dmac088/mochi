package io.nzbee.security.authority;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import io.nzbee.security.user.role.UserRole;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERMISSION", schema="security")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY,
			mappedBy = "authorities")
	private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

	@NaturalId
    @Column(name = "NAME")
    private String name;

    @Override
    public String getAuthority() { 
        return this.name;
    }
}