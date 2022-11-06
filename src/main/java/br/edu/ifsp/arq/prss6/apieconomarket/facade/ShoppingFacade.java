package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ShoppingListDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.ShoppingRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;

@Service
public class ShoppingFacade {

	@Autowired
	private ShoppingRepository repository;
	
	@Autowired
	private SearchFacade searchFacade;
	
	@Autowired
	private ProductListFacade productListFacade;
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	public List<ShoppingList> findByUserId(Long userId) {
		return repository.findByUserId(userId);
	}
	
	public ShoppingListDTO findById(Long id) {
		Optional<ShoppingList> optShoppingList = repository.findById(id);
		return modelMapperUtil.shoppingListModelToDTO(optShoppingList.get());
	}
	
	public Long saveShoppingList(ShoppingList shoppingList) {
		Long idList;
		
		List<Product> products = shoppingList.getProductList().stream().map(t -> t.getProduct()).collect(Collectors.toList());
		ShoppingList sl = new ShoppingList();
		if(!products.isEmpty()) {
			try {
				products.forEach(p -> searchFacade.findProductById(p.getId()));
			} catch (Exception e) {
				throw new RuntimeException(
						"Código de produto inválido!");
			}
			
			sl.setUser(shoppingList.getUser());
			idList = repository.save(sl).getId();
			sl.setId(idList);
			
			shoppingList.getProductList().stream().forEach(pl -> {
				pl.setShoppingList(sl);
				pl.setId(productListFacade.insertProductList(pl));
			});

			return idList;
		}
		
		return repository.save(shoppingList).getId();
	}
	
//	public ShoppingListDTO updateShoppingList(ShoppingList shoppingList) {
////		ShoppingListDTO sldto = findById(shoppingList.getId());
////		
////		//sldto.getProductList().stream().forEach(pl -> productListFacade.deleteById(pl.getId()));
////		
////		List<ProductListDTO> productList2 = sldto.getProductList();
////		
////		for(ProductListDTO pl : productList2) {
////			productListFacade.deleteById(pl.getId());
////		}
////		
////		List<ProductList> productList = shoppingList.getProductList();
////		
////		ShoppingList nsl = new ShoppingList();
////		nsl.setId(shoppingList.getId());
////		nsl.setUser(shoppingList.getUser());
////		nsl.setUuid(shoppingList.getUuid());
////		
////		
////		for(ProductList pl : productList) {
////			pl.setShoppingList(nsl);
////			productListFacade.saveProductList(pl);
////		}
//		
//		repository.save(shoppingList);
//		
//		return findById(shoppingList.getId());
//	}
	
	public void deleteShoppingList(long id) {
		repository.deleteById(id);
	}
}
