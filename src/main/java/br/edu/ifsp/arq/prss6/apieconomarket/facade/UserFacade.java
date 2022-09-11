package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;

@Service
public class UserFacade {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	//TODO: Passar CRUD do usuário para cá
	
	public List<UserDTO> findUsers() {
		return modelMapperUtil.userModelToDTO(userRepository.findAll());
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public Long saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user).getId();
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Boolean validatePassword(String password, String encodedPassword) {
		return passwordEncoder.matches(password, encodedPassword);
	}
}
