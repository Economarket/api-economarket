package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.facade.ShoppingFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.ShoppingEP.MAIN)
public class ShoppingController {

	@Autowired
	private ShoppingFacade facade;
	
	@GetMapping
	public List<ShoppingList> getShoppingLists(long userId) {
		return facade.findByUserId(userId);
	}
	
	@GetMapping("/{id}")
	public ShoppingList getShoppingList(long id) {
		return facade.findById(id);
	}
	
	@PostMapping
	public Long saveShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
		return facade.saveShoppingList(shoppingList);
	}
	
	@PutMapping
	public ShoppingList updateShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
		return facade.updateShoppingList(shoppingList);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public void deleteShoppingList(@RequestParam long id) {
		facade.deleteShoppingList(id);
	}
}
