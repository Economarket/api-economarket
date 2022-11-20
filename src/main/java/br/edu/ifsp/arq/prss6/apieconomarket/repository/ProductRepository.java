package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT p FROM product p WHERE p.searchName LIKE %:name%")
	public Page<Product> findBySearchNameLike(@Param("name") String name, Pageable pagination);
	
	@Query(value = "SELECT p FROM product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
	public Page<Product> findByPriceRange(@Param("minPrice")Double minPrice, @Param("maxPrice")Double maxPrice, Pageable pagination);
	
	public Page<Product> findByMarketsId(Long id, Pageable pagination);

	public Page<Product> findByMarketsIdIn(List<Long> id, Pageable pagination);
	
	public Page<Product> findByMarketsIdAndSearchNameLike(Long id, String name, Pageable pagination);
	
	public Page<Product> findByCategoryId(Long id, Pageable pagination);
	
	public Page<Product> findByCategoryIdAndSearchNameLike(Long id, String name, Pageable pagination);
}
