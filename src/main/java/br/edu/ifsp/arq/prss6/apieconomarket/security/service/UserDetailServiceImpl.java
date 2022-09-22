package br.edu.ifsp.arq.prss6.apieconomarket.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.security.authorization.UserDetail;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepository repository;
	
	public UserDetailServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("Usuário com email [" + email + "] não encontrado");
		}
		
		return UserDetail.create(user);
	}

}
