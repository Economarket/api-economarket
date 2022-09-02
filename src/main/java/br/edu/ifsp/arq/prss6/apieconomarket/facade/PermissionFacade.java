package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.PermissionRepository;

@Service
public class PermissionFacade {

	@Autowired
	private PermissionRepository repository;
	
	public Long savePermission(Permission permission) {
		return null;
	}
	
	public Permission updatePermission(Permission permission) {
		return null;
	}
	
	public void deletePermission(long id) {
		
	}
}
