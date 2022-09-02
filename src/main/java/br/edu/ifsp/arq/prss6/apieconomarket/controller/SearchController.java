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
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.SearchEP.MAIN)
public class SearchController {

	@Autowired
	private SearchFacade facade;
	
	@GetMapping
	public List<Product> findProducts() {
		return facade.findProducts();
	}
	
	@GetMapping
	public Product findProductById(@PathVariable long id) {
		return facade.findProductById(id);
	}
	
	@GetMapping
	public List<Market> findMarkets() {
		return facade.findMarkets();
	}
	
	@GetMapping
	public Market findMarketById(@PathVariable long id) {
		return facade.findMarketById(id);
	}
	
	@GetMapping
	public List<Market> findMarketsByName(@PathVariable String name) {
		return facade.findMarketsByName(name);
	}
	
	@GetMapping
	public List<Product> findProductsByName(@PathVariable String name) {
		return facade.findProductsByName(name);
	}
	
	@GetMapping
	public List<Product> findProductsByMarket(@PathVariable long marketId) {
		return facade.findProductsByMarket(marketId);
	}
	
	@GetMapping
	public List<Product> findProductsByMarketAndName(@PathVariable long marketId, String productName) {
		return facade.findProductsByMarketAndName(marketId, productName);
	}
}
