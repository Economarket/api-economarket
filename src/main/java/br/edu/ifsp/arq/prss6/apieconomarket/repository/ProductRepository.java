package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByNameLike(String name);
}
