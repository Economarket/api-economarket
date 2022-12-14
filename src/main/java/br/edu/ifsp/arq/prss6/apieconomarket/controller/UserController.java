package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.UserFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.UserEP.MAIN)
public class UserController {
	
	@Autowired
	private UserFacade facade;
	
	
	@GetMapping
	public Page<UserDTO> findUsers(@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findUsers(pagination);
	}
	
	@GetMapping(EndpointsConstMapping.UserEP.BY_ID)
	public UserDTO findUserById(@PathVariable Long id) {
		return facade.findUserById(id);
	}

	@PostMapping
	public Long saveUser(@Valid @RequestBody User user) {
		return facade.saveUser(user);
	}

	@PutMapping
	public User updateUser(@Valid @RequestBody User user) {
		return facade.updateUser(user);
	}

	@DeleteMapping(EndpointsConstMapping.UserEP.BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable Long id) {
		facade.deleteUser(id);
	}

}
