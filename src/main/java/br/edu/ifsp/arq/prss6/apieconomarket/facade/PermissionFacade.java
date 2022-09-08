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
	
	//TODO: Implementar Salvar Permissão
	public Long savePermission(Permission permission) {
		return null;
	}
	
	//TODO: Implementar Atualizar Permissão
	public Permission updatePermission(Permission permission) {
		return null;
	}
	
	//TODO: Implementar Excluir Permissão
	public void deletePermission(long id) {
		
	}


}
