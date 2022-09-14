package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.RegisterFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.RegisterEP.MAIN)
public class RegisterController {
	
	@Autowired
	private RegisterFacade facade;

	@PostMapping(EndpointsConstMapping.CategoryEP.MAIN)
	public Long saveCategory(@Valid @RequestBody Category category) {
		return facade.saveCategory(category);
	}

	@PutMapping(EndpointsConstMapping.CategoryEP.MAIN)
	public Category updateCategory(@Valid @RequestBody Category category) {
		return facade.updateCategory(category);
	}

	@DeleteMapping(EndpointsConstMapping.CategoryEP.MAIN + "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCategory(@PathVariable Long id) {
		facade.deleteCategory(id);
	}

	@PostMapping(EndpointsConstMapping.BrandEP.MAIN)
	public Long saveBrand(@Valid @RequestBody Brand brand) {
		return facade.saveBrand(brand);
	}

	@PutMapping(EndpointsConstMapping.BrandEP.MAIN)
	public Brand updateBrand(@Valid @RequestBody Brand brand) {
		return facade.updateBrand(brand);
	}

	@DeleteMapping(EndpointsConstMapping.BrandEP.MAIN + "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteBrand(@PathVariable Long id) {
		facade.deleteBrand(id);
	}

	@PostMapping(EndpointsConstMapping.MarketEP.MAIN)
	public Long saveMarket(@Valid @RequestBody Market market) {
		return facade.saveMarket(market);
	}

	@PutMapping(EndpointsConstMapping.MarketEP.MAIN)
	public Market updateMarket(@Valid @RequestBody Market market) {
		return facade.updateMarket(market);
	}

	@DeleteMapping(EndpointsConstMapping.MarketEP.MAIN + "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMarket(@PathVariable Long id) {
		facade.deleteMarket(id);
	}

	@PostMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Long saveProduct(@Valid @RequestBody Product product) {
		//TODO: Linkar o mercado com produto na 'market_with_product'
		
		return facade.saveProduct(product);
	}

	@PutMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Product updateProduct(@Valid @RequestBody Product product) {
		return facade.updateProduct(product);
	}

	@DeleteMapping(EndpointsConstMapping.ProductEP.MAIN + "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long id) {
		facade.deleteProduct(id);
	}
}
