package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.CategoryRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;

@Service
public class SearchFacade {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private MarketRepository marketRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}
	
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public List<Brand> findBrands() {
		return brandRepository.findAll();
	}
	
	public Brand findBrandById(Long id) {
		return brandRepository.findById(id).get();
	}

	public List<Market> findMarkets() {
		return marketRepository.findAll();
	}
	
	public Market findMarketById(Long id) {
		Optional<Market> optMarket = marketRepository.findById(id);
		return optMarket.orElseThrow();
	}
	
	public List<Market> findMarketsByName(String name) {
		return marketRepository.findByNameLike(name);
	}
	
	public List<Product> findProducts() {
		return productRepository.findAll();
	}
	
	public Product findProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		return optProduct.orElseThrow();
	}
	
	public List<Product> findProductsByName(String name) {
		return productRepository.findByNameLike(name);
	}
	
	public List<User> findUsers() {
		return userRepository.findAll();
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id).get();
	}

	public List<Product> findProductsByMarket(Long marketId) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketId(marketId);
		return null;
	}

	public List<Product> findProductsByMarketAndName(Long marketId, String productName) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketIdAndProductName(marketId, productName);
		return null;
	}

}
