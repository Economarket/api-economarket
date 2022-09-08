package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
