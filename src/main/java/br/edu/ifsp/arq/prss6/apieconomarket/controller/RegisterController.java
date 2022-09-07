package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.facade.RegisterFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.User;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.RegisterEP.MAIN)
public class RegisterController {
	
	@Autowired
	private RegisterFacade facade;

	@PostMapping(EndpointsConstMapping.ItemsEP.CATEGORY)
	public Long saveCategory(@Valid @RequestBody Category category) {
		return facade.saveCategory(category);
	}

	@PutMapping(EndpointsConstMapping.ItemsEP.CATEGORY)
	public Category updateCategory(@Valid @RequestBody Category category) {
		return facade.updateCategory(category);
	}

	@DeleteMapping(EndpointsConstMapping.ItemsEP.CATEGORY)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCategory(@RequestParam Long id) {
		facade.deleteCategory(id);
	}

	@PostMapping(EndpointsConstMapping.ItemsEP.BRAND)
	public Long saveBrand(@Valid @RequestBody Brand brand) {
		return facade.saveBrand(brand);
	}

	@PutMapping(EndpointsConstMapping.ItemsEP.BRAND)
	public Brand updateBrand(@Valid @RequestBody Brand brand) {
		return facade.updateBrand(brand);
	}

	@DeleteMapping(EndpointsConstMapping.ItemsEP.BRAND)
	@ResponseStatus(HttpStatus.OK)
	public void deleteBrand(@RequestParam Long id) {
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

	@DeleteMapping(EndpointsConstMapping.MarketEP.MAIN)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMarket(@RequestParam Long id) {
		facade.deleteMarket(id);
	}

	@PostMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Long saveProduct(@Valid @RequestBody Product product) {
		return facade.saveProduct(product);
	}

	@PutMapping(EndpointsConstMapping.ProductEP.MAIN)
	public Product updateProduct(@Valid @RequestBody Product product) {
		return facade.updateProduct(product);
	}

	@DeleteMapping(EndpointsConstMapping.ProductEP.MAIN)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@RequestParam Long id) {
		facade.deleteProduct(id);
	}

	@PostMapping(EndpointsConstMapping.UserEP.MAIN)
	public Long saveUser(@Valid @RequestBody User user) {
		return facade.saveUser(user);
	}

	@PutMapping(EndpointsConstMapping.UserEP.MAIN)
	public User updateUser(@Valid @RequestBody User user) {
		return facade.updateUser(user);
	}

	@DeleteMapping(EndpointsConstMapping.UserEP.MAIN)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@RequestParam Long id) {
		facade.deleteUser(id);
	}
}
