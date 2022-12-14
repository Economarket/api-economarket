package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	List<Brand> findBySearchNameLike(String name);
}
