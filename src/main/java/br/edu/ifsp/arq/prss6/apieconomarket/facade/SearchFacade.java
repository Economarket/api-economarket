package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<BrandDTO> findBrands(Pageable pagination) {
		return modelMapperUtil.brandModelToDTO(brandRepository.findAll(pagination));
	}
	
	public BrandDTO findBrandById(Long id) {
		return modelMapperUtil.brandModelToDTO(brandRepository.findById(id).get());
	}

	public Page<MarketDTO> findMarkets(Pageable pagination) {
		return modelMapperUtil.marketModelToDTO(marketRepository.findAll(pagination));
	}
	
	public MarketDTO findMarketById(Long id) {
		Optional<Market> optMarket = marketRepository.findById(id);
		return modelMapperUtil.marketModelToDTO(optMarket.orElseThrow());
	}
	
	public List<Market> findMarketsByName(String name) {
		return marketRepository.findByNameLike(name);
	}
	
	public Page<ProductDTO> findProducts(Pageable pagination) {
		return modelMapperUtil.productModelToDTO(productRepository.findAll(pagination));
	}
	
	public ProductDTO findProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		return modelMapperUtil.productModelToDTO(optProduct.get());
	}
	
	public Page<ProductDTO> findProductsByName(String name, Pageable pagination) {
		return modelMapperUtil.productModelToDTO(
				productRepository.findBySearchNameLike(UtilsFunc.treatSearchName(name), pagination));
	}

	public Page<ProductDTO> findProductsByMarket(Long id, Pageable pagination) {
		return modelMapperUtil.productModelToDTO(productRepository.findByMarketsId(id, pagination));
	}

	public List<Product> findProductsByMarketAndName(Long marketId, String productName) {
		//TODO: Implementar
		//marketWithProductRepository.findByMarketIdAndProductName(marketId, productName);
		return null;
	}
	
	public Page<ProductDTO> findProductsByCategory (Long id, Pageable pagination){
		return modelMapperUtil.productModelToDTO(productRepository.findByCategoryId(id, pagination));
	}
}
