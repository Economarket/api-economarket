package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Permission;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.PermissionRepository;

@Service
public class PermissionFacade {

	@Autowired
	private PermissionRepository repository;
	
	public List<Permission> getPermissions() {
		return repository.findAll();
	}
	
	public Permission getPermissionById(Long id) {
		return repository.findById(id).get();
	}
	
	public Long savePermission(Permission permission) {
		return repository.save(permission).getId();
	}
	
	public Permission updatePermission(Permission permission) {
		return repository.save(permission);
	}
	
	public void deletePermission(long id) {
		repository.deleteById(id);
	}


}
