package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.CategoryRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;

@Service
public class RegisterFacade {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private MarketRepository marketRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	

	public Long saveCategory(Category category) {
		return categoryRepository.save(category).getId();
	}

	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	public void deleteCategory(long id) {
		categoryRepository.deleteById(id);
	}

	public Long saveBrand(Brand brand) {
		return brandRepository.save(brand).getId();
	}

	public Brand updateBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	public void deleteBrand(long id) {
		brandRepository.deleteById(id);
	}

	public Long saveMarket(Market market) {
		return marketRepository.save(market).getId();
	}

	public Market updateMarket(Market market) {
		return marketRepository.save(market);
	}

	public void deleteMarket(long id) {
		marketRepository.deleteById(id);
	}

	public Long saveProduct(Product product) {
		String prodSearchName = product.getName()
				.trim()
				.toLowerCase();
		product.setSearchName(prodSearchName);
		return productRepository.save(product).getId();
	}

	public ProductDTO updateProduct(Product product) {
		return modelMapperUtil.productModelToDTO(productRepository.save(product));
	}

	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
}