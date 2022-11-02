package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ProductList;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList, Long> {

	@Query(value = "delete from product_list WHERE shopping_list_id = :id", nativeQuery = true)
	public void deleteByShoppingListId(Long id);

}
