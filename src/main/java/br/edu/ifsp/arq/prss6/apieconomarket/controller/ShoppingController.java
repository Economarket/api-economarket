package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ShoppingListDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.ShoppingFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.ShoppingEP.MAIN)
public class ShoppingController {

	@Autowired
	private ShoppingFacade facade;
	
	@GetMapping("/{id}")
	public ShoppingListDTO getShoppingList(@PathVariable Long id) {
		return facade.findById(id);
	}
	
	@GetMapping(EndpointsConstMapping.UserEP.MAIN + "/{userId}")
	public List<ShoppingList> getShoppingLists(@PathVariable Long userId) {
		return facade.findByUserId(userId);
	}
	
	@PostMapping
	public Long saveShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
		return facade.saveShoppingList(shoppingList);
	}
	
//	@PutMapping
//	public ShoppingListDTO updateShoppingList(@Valid @RequestBody ShoppingList shoppingList) {
//		return facade.updateShoppingList(shoppingList);
//	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteShoppingList(@PathVariable long id) {
		facade.deleteShoppingList(id);
	}
}
