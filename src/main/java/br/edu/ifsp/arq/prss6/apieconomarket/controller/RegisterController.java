package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import javax.transaction.Transactional;
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

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
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

	@DeleteMapping(EndpointsConstMapping.CategoryEP.BY_ID)
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

	@DeleteMapping(EndpointsConstMapping.BrandEP.BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deleteBrand(@PathVariable Long id) {
		facade.deleteBrand(id);
	}

	@PostMapping(EndpointsConstMapping.MarketEP.MAIN)
	@Transactional
	public Long saveMarket(@Valid @RequestBody Market market) {
		return facade.saveMarket(market);
	}

	@PutMapping(EndpointsConstMapping.MarketEP.MAIN)
	@Transactional
	public Market updateMarket(@Valid @RequestBody Market market) {
		return facade.updateMarket(market);
	}

	@DeleteMapping(EndpointsConstMapping.MarketEP.BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMarket(@PathVariable Long id) {
		facade.deleteMarket(id);
	}

	@PostMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Long saveProduct(@Valid @RequestBody Product product) {
		return facade.saveProduct(product);
	}

	@PutMapping(EndpointsConstMapping.ProductEP.MAIN)
	public ProductDTO updateProduct(@Valid @RequestBody Product product) {
		return facade.updateProduct(product);
	}

	@DeleteMapping(EndpointsConstMapping.ProductEP.BY_ID)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long id) {
		facade.deleteProduct(id);
	}
}
