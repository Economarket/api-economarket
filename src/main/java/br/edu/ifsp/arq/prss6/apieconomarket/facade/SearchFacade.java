package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.CategoryRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketWithProductRepository;
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
	private MarketWithProductRepository marketWithProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;
	

	public List<Product> findProducts() {
		return productRepository.findAll();
	}

	public Product findProductById(long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		return optProduct.orElseThrow();
	}

	public List<Market> findMarkets() {
		return marketRepository.findAll();
	}

	public Market findMarketById(long id) {
		Optional<Market> optMarket = marketRepository.findById(id);
		return optMarket.orElseThrow();
	}

	public List<Market> findMarketsByName(String name) {
		return marketRepository.findByNameLike(name);
	}

	public List<Product> findProductsByName(String name) {
		return productRepository.findByNameLike(name);
	}

	public List<Product> findProductsByMarket(long marketId) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketId(marketId);
		return null;
	}

	public List<Product> findProductsByMarketAndName(long marketId, String productName) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketIdAndProductName(marketId, productName);
		return null;
	}
}
