package br.edu.ifsp.arq.prss6.apieconomarket.security.authorization;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsFunc;

public class UserDetail implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private final User user;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetail(Optional<User> user) {
		this.user = user.orElse(new User());
		
		this.authorities = UtilsFunc.permissionsToAuthoritiesList(
				user.orElse(new User()).getPermissions());
	}
	
	public static UserDetail create(Optional<User> user) {
		return new UserDetail(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
