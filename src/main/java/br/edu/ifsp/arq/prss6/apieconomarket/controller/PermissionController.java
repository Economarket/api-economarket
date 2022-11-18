package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.PermissionFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.PermissionEP.MAIN)
public class PermissionController {

	@Autowired
	private PermissionFacade facade;
	
	@GetMapping
	public List<Permission> getPermissions() {
		return facade.getPermissions();
	}
	
	@GetMapping(EndpointsConstMapping.PermissionEP.BY_ID)
	public Permission getPermissionById(@PathVariable Long id) {
		return facade.getPermissionById(id);
	}
	
	@PostMapping
	public Long savePermission(@Valid @RequestBody Permission permission) {
		return facade.savePermission(permission);
	}
	
	@PutMapping
	public Permission updatePermission(@Valid @RequestBody Permission permission) {
		return facade.updatePermission(permission);
	}
		
	@DeleteMapping(EndpointsConstMapping.PermissionEP.BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deletePermission(@PathVariable Long id) {
		facade.deletePermission(id);
	}
}
