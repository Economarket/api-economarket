package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
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
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsFunc;

@Service
public class SearchFacade {
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	

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
	
	public List<ProductDTO> findProducts() {
		return modelMapperUtil.productModelToDTO(productRepository.findAll());
	}
	
	public ProductDTO findProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		return modelMapperUtil.productModelToDTO(optProduct.get());
	}
	
	public List<ProductDTO> findProductsByName(String name) {
		return modelMapperUtil.productModelToDTO(
				productRepository.findBySearchNameLike("%" + UtilsFunc.removeWhiteSpacesIfExists(name) + "%"));
	}

	public List<ProductDTO> findProductsByMarket(Long id) {
		return modelMapperUtil.productModelToDTO(productRepository.findByMarketsId(id));
	}

	public List<Product> findProductsByMarketAndName(Long marketId, String productName) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketIdAndProductName(marketId, productName);
		return null;
	}
}
