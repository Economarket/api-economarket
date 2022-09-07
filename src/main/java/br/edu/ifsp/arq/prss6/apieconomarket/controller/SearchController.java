package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.facade.SearchFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.User;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.SearchEP.MAIN)
public class SearchController {

	@Autowired
	private SearchFacade facade;
	
	@GetMapping(EndpointsConstMapping.UserEP.MAIN)
	public List<User> findUsers() {
		return facade.findUsers();
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN)
	public List<Product> findProducts() {
		return facade.findProducts();
	}
	
	@GetMapping(EndpointsConstMapping.ProductEP.MAIN + "/{id}")
	public Product findProductById(@PathVariable Long id) {
		return facade.findProductById(id);
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
//	
//	@GetMapping(EndpointsConstMapping.ItemsEP.PRODUCT)
//	public List<Product> findProductsByName(@PathVariable String name) {
//		return facade.findProductsByName(name);
//	}
//	
//	@GetMapping(EndpointsConstMapping.ItemsEP.PRODUCT)
//	public List<Product> findProductsByMarket(@PathVariable Long marketId) {
//		return facade.findProductsByMarket(marketId);
//	}
//	
//	@GetMapping(EndpointsConstMapping.ItemsEP.PRODUCT)
//	public List<Product> findProductsByMarketAndName(@PathVariable Long marketId, String productName) {
//		return facade.findProductsByMarketAndName(marketId, productName);
//	}
}
