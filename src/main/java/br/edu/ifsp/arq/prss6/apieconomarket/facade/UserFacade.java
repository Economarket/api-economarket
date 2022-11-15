package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.PermissionRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsCons;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsFunc;


@Service
public class UserFacade {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	public Page<UserDTO> findUsers(Pageable pagination) {
		return modelMapperUtil.userModelToDTO(userRepository.findAll(pagination));
	}
	
	public UserDTO findUserById(Long id) {
		return modelMapperUtil.userModelToDTO(userRepository.findById(id).get());
	}
	
	public Long saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Permission permission = permissionRepository.findByName(UtilsCons.USER_DEFAULT_PERMISSION).get();
		if(permission == null) {
			throw new RuntimeException("Permissão não encontrada!");
		}
		
		user.setPermissions(Arrays.asList(permission));
		return userRepository.save(user).getId();
	}

	public User updateUser(User user) {
		if(UtilsFunc.stringEquals(user.getEmail(), "admin@admin.com") ||
				UtilsFunc.stringEquals(user.getEmail(), "manager@manager.com")) {
			
			throw new RuntimeException(
					"Operação inválida! O usuário é privado e não permite modificações. (PARA DE FAZER MERDA NA APLICAÇÃO!!!)");
		}
		
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		User user = userRepository.findById(id).get();
		
		if(user != null && (UtilsFunc.stringEquals(user.getEmail(), "admin@admin.com") ||
				UtilsFunc.stringEquals(user.getEmail(), "manager@manager.com"))) {
			
			throw new RuntimeException(
					"Operação inválida! O usuário é privado e não permite modificações. (PARA DE FAZER MERDA NA APLICAÇÃO!!!)");
		}
		
		userRepository.deleteById(id);
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public Boolean validatePassword(String password, String encodedPassword) {
		return passwordEncoder.matches(password, encodedPassword);

	}
}
