package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.SearchFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.SearchEP.MAIN)
public class SearchController {

	@Autowired
	private SearchFacade facade;
	
	@GetMapping(EndpointsConstMapping.CategoryEP.MAIN)
	public List<Category> findCategories() {
		return facade.findCategories();
	}
	
	@GetMapping(EndpointsConstMapping.CategoryEP.MAIN + "/{id}")
	public Category findCategoryById(@PathVariable Long id) {
		return facade.findCategoryById(id);
	}
	
	@GetMapping(EndpointsConstMapping.BrandEP.MAIN)
	public List<Brand> findBrands() {
		return facade.findBrands();
	}
		
	@GetMapping(EndpointsConstMapping.BrandEP.MAIN + "/{id}")
	public Brand findBrandById(@PathVariable Long id) {
		return facade.findBrandById(id);
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN)
	public List<Market> findMarkets() {
		return facade.findMarkets();
	}
	
	@GetMapping(EndpointsConstMapping.MarketEP.MAIN + "/{id}")
	public Market findMarketById(@PathVariable Long id) {
		return facade.findMarketById(id);
	}
	
//	@GetMapping(EndpointsConstMapping.MarketEP.BY_NAME)
//	public List<Market> findMarketsByName(@PathVariable String name) {
//		return facade.findMarketsByName(name);
//	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN)
	public List<ProductDTO> findProducts() {
		return facade.findProducts();
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/{id}")
	public ProductDTO findProductById(@PathVariable Long id) {
		return facade.findProductById(id);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/name")
	public List<ProductDTO> findProductsByName(@RequestParam String name) {
		return facade.findProductsByName(name);
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/market")
	public List<ProductDTO> findProductsByMarket(@RequestParam Long id) {
		return facade.findProductsByMarket(id);
	}
//	
//	@GetMapping(EndpointsConstMapping.ItemsEP.PRODUCT)
//	public List<Product> findProductsByMarketAndName(@PathVariable Long marketId, String productName) {
//		return facade.findProductsByMarketAndName(marketId, productName);
//	}
}
