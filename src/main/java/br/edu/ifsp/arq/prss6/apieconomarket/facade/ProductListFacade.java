package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ProductList;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductListRepository;

@Service
public class ProductListFacade {
	
	@Autowired
	private ProductListRepository repository;
	
	public Long saveProductList(ProductList productList) {
		return repository.save(productList).getId();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void deleteByShoppingListId(Long id) {
		repository.deleteByShoppingListId(id);
	}

}
