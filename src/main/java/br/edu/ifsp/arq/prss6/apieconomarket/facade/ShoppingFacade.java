package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ShoppingRepository;

@Service
public class ShoppingFacade {

	@Autowired
	private ShoppingRepository repository;
	
	public Long saveShoppingList(ShoppingList shoppingList) {
		return null;
	}
	
	public ShoppingList updateShoppingList(ShoppingList shoppingList) {
		return null;
	}
	
	public void deleteShoppingList(long id) {
		
	}
}
