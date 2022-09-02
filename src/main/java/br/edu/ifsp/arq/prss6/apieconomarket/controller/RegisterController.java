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

	@PostMapping(EndpointsConstMapping.RegisterEP.CATEGORY)
	public Long saveCategory(@Valid @RequestBody Category category) {
		return facade.saveCategory(category);
	}

	@PutMapping(EndpointsConstMapping.RegisterEP.CATEGORY)
	public Category updateCategory(@Valid @RequestBody Category category) {
		return facade.updateCategory(category);
	}

	@DeleteMapping(EndpointsConstMapping.RegisterEP.CATEGORY)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCategory(@RequestParam long id) {
		facade.deleteCategory(id);
	}

	@PostMapping(EndpointsConstMapping.RegisterEP.BRAND)
	public Long saveBrand(@Valid @RequestBody Brand brand) {
		return facade.saveBrand(brand);
	}

	@PutMapping(EndpointsConstMapping.RegisterEP.BRAND)
	public Brand updateBrand(@Valid @RequestBody Brand brand) {
		return facade.updateBrand(brand);
	}

	@DeleteMapping(EndpointsConstMapping.RegisterEP.BRAND)
	@ResponseStatus(HttpStatus.OK)
	public void deleteBrand(@RequestParam long id) {
		facade.deleteBrand(id);
	}

	@PostMapping(EndpointsConstMapping.RegisterEP.MARKET)
	public Long saveMarket(@Valid @RequestBody Market market) {
		return facade.saveMarket(market);
	}

	@PutMapping(EndpointsConstMapping.RegisterEP.MARKET)
	public Market updateMarket(@Valid @RequestBody Market market) {
		return facade.updateMarket(market);
	}

	@DeleteMapping(EndpointsConstMapping.RegisterEP.MARKET)
	@ResponseStatus(HttpStatus.OK)
	public void deleteMarket(@RequestParam long id) {
		facade.deleteMarket(id);
	}

	@PostMapping(EndpointsConstMapping.RegisterEP.PRODUCT)
	public Long saveProduct(@Valid @RequestBody Product product) {
		return facade.saveProduct(product);
	}

	@PutMapping(EndpointsConstMapping.RegisterEP.PRODUCT)
	public Product updateProduct(@Valid @RequestBody Product product) {
		return facade.updateProduct(product);
	}

	@DeleteMapping(EndpointsConstMapping.RegisterEP.PRODUCT)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@RequestParam long id) {
		facade.deleteProduct(id);
	}

	@PostMapping(EndpointsConstMapping.RegisterEP.USER)
	public Long saveUser(@Valid @RequestBody User user) {
		return facade.saveUser(user);
	}

	@PutMapping(EndpointsConstMapping.RegisterEP.USER)
	public User updateUser(@Valid @RequestBody User user) {
		return facade.updateUser(user);
	}

	@DeleteMapping(EndpointsConstMapping.RegisterEP.USER)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@RequestParam long id) {
		facade.deleteUser(id);
	}
}
