package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
