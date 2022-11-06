package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductListDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ProductList;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductListRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;

@Service
public class ProductListFacade {
	
	@Autowired
	private ProductListRepository repository;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	
	public ProductListDTO findById(Long id) {
		Optional<ProductList> optProductList = repository.findById(id);
		return modelMapperUtil.productListModelToDTO(optProductList.get());
	}
	
	public Long insertProductList(ProductList productList) {
		return repository.save(productList).getId();
	}
	
	public ProductListDTO updateProductList(ProductList productList) {
		if(productList.getId() == null) {
			
		}
		try {
			repository.findById(productList.getId());
		} catch (Exception e) {
			throw new RuntimeException(
					"Código de productList inválido!");
		}
		return modelMapperUtil.productListModelToDTO(productList);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
