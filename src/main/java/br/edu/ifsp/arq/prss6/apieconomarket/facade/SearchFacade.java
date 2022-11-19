package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.ArrayList;
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
	
	public List<BrandDTO> findBrandByName(String name) {
		return modelMapperUtil.brandModelToDTO(brandRepository.findBySearchNameLike(
				"%" + UtilsFunc.treatSearchName(name) + "%"));		
	}

	public Page<MarketDTO> findMarkets(Pageable pagination) {
		return modelMapperUtil.marketModelToDTO(marketRepository.findAll(pagination));
	}
	
	public List<Market> findMarketsByDistance(double distance, double locateX, double locateY) {
		List<Market> markets = marketRepository.findAll();
		List<Market> nearbyMarkets = new ArrayList<>();
		
		markets.stream().forEach(m -> {
			Double distanceWithMarket = UtilsFunc.calculateDistance(locateX, 
																	locateY, 
																	Double.parseDouble(m.getAddress().getLocateX()), 
																	Double.parseDouble(m.getAddress().getLocateY()));
			if(distance >= distanceWithMarket) {
				nearbyMarkets.add(m);
			}
		});
		
		return nearbyMarkets;
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
		return UtilsFunc.isBlankOrEmpty(name) ?
				modelMapperUtil.productModelToDTO(productRepository.findAll(pagination)) :
				modelMapperUtil.productModelToDTO(UtilsFunc.productsBySearch(UtilsFunc.treatSearchName(name),
						productRepository.findAll(pagination)));
	}
	
	public Page<ProductDTO> findProductsByPriceRange(Double minPrice, Double maxPrice, Pageable pagination) {
		return modelMapperUtil.productModelToDTO(productRepository.findByPriceRange(minPrice, maxPrice, pagination));
	}

	public Page<ProductDTO> findProductsByMarket(Long id, String name, Pageable pagination) {	
		return UtilsFunc.isBlankOrEmpty(name) ? 
				modelMapperUtil.productModelToDTO(productRepository.findByMarketsId(id, pagination)) :
				modelMapperUtil.productModelToDTO(UtilsFunc.productsBySearch(UtilsFunc.treatSearchName(name), 
						productRepository.findByMarketsId(id, pagination)));
	}
	
	public Page<ProductDTO> findProductsByCategory(Long id, String name, Pageable pagination){
		return UtilsFunc.isBlankOrEmpty(name) ? 
				modelMapperUtil.productModelToDTO(productRepository.findByCategoryId(id, pagination)) :
				modelMapperUtil.productModelToDTO(UtilsFunc.productsBySearch(UtilsFunc.treatSearchName(name), 
						productRepository.findByCategoryId(id, pagination)));
	}
	
	public Page<ProductDTO> findProductsByCategoryAndName(Long id, String name, Pageable pagination){
		return modelMapperUtil.productModelToDTO(productRepository.findByCategoryId(id, pagination));
	}
}
