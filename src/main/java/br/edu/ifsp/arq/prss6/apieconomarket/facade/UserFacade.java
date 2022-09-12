package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;

@Service
public class UserFacade {
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findUsers() {
		return modelMapperUtil.userModelToDTO(userRepository.findAll());
	}
	
	public UserDTO findUserById(Long id) {
		return modelMapperUtil.userModelToDTO(userRepository.findById(id).get());
	}
	
	public Long saveUser(User user) {
		return userRepository.save(user).getId();
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}
