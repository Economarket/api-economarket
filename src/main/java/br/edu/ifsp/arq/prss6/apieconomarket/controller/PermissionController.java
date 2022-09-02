package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.facade.PermissionFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.PermissionEP.MAIN)
public class PermissionController {

	@Autowired
	private PermissionFacade facade;
	
	@PostMapping
	public Long savePermission(@Valid @RequestBody Permission permission) {
		return facade.savePermission(permission);
	}
	
	@PutMapping
	public Permission updatePermission(@Valid @RequestBody Permission permission) {
		return facade.updatePermission(permission);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deletePermission(@RequestParam long id) {
		facade.deletePermission(id);
	}
}
