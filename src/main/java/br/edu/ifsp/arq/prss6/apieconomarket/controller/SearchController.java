package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.CategoryDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.MarketDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.SearchFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.SearchEP.MAIN)
public class SearchController {

	@Autowired
	private SearchFacade facade;
	
	@GetMapping(EndpointsConstMapping.CategoryEP.MAIN)
	public List<CategoryDTO> findCategories() {
		return facade.findCategories();
	}
	
	@GetMapping(EndpointsConstMapping.CategoryEP.MAIN + "/{id}")
	public CategoryDTO findCategoryById(@PathVariable Long id) {
		return facade.findCategoryById(id);
	}
	
	@GetMapping(EndpointsConstMapping.BrandEP.MAIN)
	public Page<BrandDTO> findBrands(Pageable pagination) {
		return facade.findBrands(pagination);
	}
		
	@GetMapping(EndpointsConstMapping.BrandEP.MAIN + "/{id}")
	public BrandDTO findBrandById(@PathVariable Long id) {
		return facade.findBrandById(id);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN)
	public Page<MarketDTO> findMarkets(Pageable pagination) {
		return facade.findMarkets(pagination);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN)
	public List<Market> findMarketsByDistance(double distance, double locateX, double locateY) {
		return facade.findMarketsByDistance(distance, locateX, locateY);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN + "/{id}")
	public MarketDTO findMarketById(@PathVariable Long id) {
		return facade.findMarketById(id);
	}
	
//	@GetMapping(EndpointsConstMapping.MarketEP.BY_NAME)
//	public List<Market> findMarketsByName(@PathVariable String name) {
//		return facade.findMarketsByName(name);
//	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Page<ProductDTO> findProducts(Pageable pagination) {
		return facade.findProducts(pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/{id}")
	public ProductDTO findProductById(@PathVariable Long id) {
		return facade.findProductById(id);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/name")
	public Page<ProductDTO> findProductsByName(@RequestParam String name, Pageable pagination) {
		return facade.findProductsByName(name, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/market" + "/{id}")
	public Page<ProductDTO> findProductsByMarket(@PathVariable Long id, Pageable pagination) {
		return facade.findProductsByMarket(id, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/category" + "/{id}")
	public Page<ProductDTO> findProductsByCategory(@PathVariable Long id, Pageable pagination) {
		return facade.findProductsByCategory(id, pagination);
	}
	
//	@GetMapping(EndpointsConstMapping.ItemsEP.PRODUCT)
//	public List<Product> findProductsByMarketAndName(@PathVariable Long marketId, String productName) {
//		return facade.findProductsByMarketAndName(marketId, productName);
//	}
}
