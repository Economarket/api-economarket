package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ShoppingRepository;

@Service
public class ShoppingFacade {

	@Autowired
	private ShoppingRepository repository;
	
	public List<ShoppingList> findByUserId(Long userId) {
		return repository.findByUserId(userId);
	}
	
	public ShoppingList findById(Long id) {
		Optional<ShoppingList> optShoppingList = repository.findById(id);
		return optShoppingList.get();
	}
	
	public Long saveShoppingList(ShoppingList shoppingList) {
		return null;
	}
	
	public ShoppingList updateShoppingList(ShoppingList shoppingList) {
		return null;
	}
	
	public void deleteShoppingList(long id) {
		
	}
}
