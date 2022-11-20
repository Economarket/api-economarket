package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.CategoryDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.MarketDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ShoppingListProductDTO;
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
	
	@GetMapping(EndpointsConstMapping.CategoryEP.BY_ID)
	public CategoryDTO findCategoryById(@PathVariable Long id) {
		return facade.findCategoryById(id);
	}
	
	@GetMapping(EndpointsConstMapping.BrandEP.MAIN)
	public Page<BrandDTO> findBrands(@SortDefault(sort = "brandName", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findBrands(pagination);
	}
		
	@GetMapping(EndpointsConstMapping.BrandEP.BY_ID)
	public BrandDTO findBrandById(@PathVariable Long id) {
		return facade.findBrandById(id);
	}
	
	@GetMapping(EndpointsConstMapping.BrandEP.BY_NAME)
	public List<BrandDTO> findBrandById(@RequestParam String name) {
		return facade.findBrandByName(name);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN)
	public Page<MarketDTO> findMarkets(@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findMarkets(pagination);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.DISTANCE)
	public List<Market> findMarketsByDistance(@RequestParam Double distance, 
			@RequestParam Double locateX, @RequestParam Double locateY) {
		return facade.findMarketsByDistance(distance, locateX, locateY);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.BY_ID)
	public MarketDTO findMarketById(@PathVariable Long id) {
		return facade.findMarketById(id);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Page<ProductDTO> findProducts(@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findProducts(pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_ID)
	public ProductDTO findProductById(@PathVariable Long id) {
		return facade.findProductById(id);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_NAME)
	public Page<ProductDTO> findProductsByName(@RequestParam String name, 
			@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findProductsByName(name, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_PRICE_RANGE)
	public Page<ProductDTO> findProductsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice, 
			@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findProductsByPriceRange(minPrice, maxPrice, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_MARKET)
	public Page<ProductDTO> findProductsByMarket(@PathVariable Long id, String name, 
			@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findProductsByMarket(id, name, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_CATEGORY)
	public Page<ProductDTO> findProductsByCategory(@PathVariable Long id, String name, 
			@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findProductsByCategory(id, name, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.BY_NEARBY_MARKETS)
	public Page<ProductDTO> findProductsByNearbyMarketsAndName(@RequestParam Double distance, 
			@RequestParam Double locateX, @RequestParam Double locateY, String name, Pageable pagination) {
		return facade.findProductsByNearbyMarketsAndName(distance, locateX, locateY, name, pagination);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.SHOPPING_BY_MARKET)
	public Page<ShoppingListProductDTO> findShoppingListProductsByMarket(@PathVariable Long shoppingListId, 
			@SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pagination) {
		return facade.findShoppingListProductsByMarket(shoppingListId, pagination);
	}
}
