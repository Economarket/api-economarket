package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.CategoryDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.MarketDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.CategoryRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.MarketRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ProductRepository;
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
	
	
	public List<CategoryDTO> findCategories() {
		return modelMapperUtil.categoryModelToDTO(categoryRepository.findAll());
	}
	
	public CategoryDTO findCategoryById(Long id) {
		return modelMapperUtil.categoryModelToDTO(categoryRepository.findById(id).get());
	}
	
	public List<BrandDTO> findBrands() {
		return modelMapperUtil.brandModelToDTO(brandRepository.findAll());
	}
	
	public BrandDTO findBrandById(Long id) {
		return modelMapperUtil.brandModelToDTO(brandRepository.findById(id).get());
	}

	public List<MarketDTO> findMarkets() {
		return modelMapperUtil.marketModelToDTO(marketRepository.findAll());
	}
	
	public MarketDTO findMarketById(Long id) {
		Optional<Market> optMarket = marketRepository.findById(id);
		return modelMapperUtil.marketModelToDTO(optMarket.orElseThrow());
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
				productRepository.findBySearchNameLike("%" + UtilsFunc.treatSearchName(name) + "%"));
	}

	public List<ProductDTO> findProductsByMarket(Long id) {
		return modelMapperUtil.productModelToDTO(productRepository.findByMarketsId(id));
	}

	public List<Product> findProductsByMarketAndName(Long marketId, String productName) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketIdAndProductName(marketId, productName);
		return null;
	}
	
	public List<ProductDTO> findProductsByCategory (Long id){
		return modelMapperUtil.productModelToDTO(productRepository.findByCategoryId(id));
	}
}
