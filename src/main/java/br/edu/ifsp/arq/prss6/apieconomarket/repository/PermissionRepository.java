package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
