package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.CategoryDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.MarketDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductListDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ShoppingListDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Brand;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Category;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Market;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ProductList;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.ShoppingList;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;

@Component
public class ModelMapperUtil {

	@Autowired
	private ModelMapper modelMapper;
	
	public List<UserDTO> userModelToDTO(List<User> users) {
		return users
				.stream()
				.map(u -> modelMapper.map(u, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<UserDTO> userModelToDTO(Page<User> pagedUsers) {
		return pagedUsers.map(u -> userModelToDTO(u));
	}
	
	public UserDTO userModelToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	public List<ProductDTO> productModelToDTO(List<Product> products) {
		return products
				.stream()
				.map(p -> modelMapper.map(p, ProductDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<ProductDTO> productModelToDTO(Page<Product> pagedProducts) {
		return pagedProducts.map(p -> productModelToDTO(p));
	}
	
	public ProductDTO productModelToDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	public MarketDTO marketModelToDTO(Market market) {
		return modelMapper.map(market, MarketDTO.class);
	}
	
	public List<MarketDTO> marketModelToDTO(List<Market> markets) {
		return markets
				.stream()
				.map(p -> modelMapper.map(p, MarketDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<MarketDTO> marketModelToDTO(Page<Market> pagedMarkets) {
		return pagedMarkets.map(m -> marketModelToDTO(m));
	}
	
	public List<CategoryDTO> categoryModelToDTO(List<Category> categorys) {
		return categorys
				.stream()
				.map(p -> modelMapper.map(p, CategoryDTO.class))
				.collect(Collectors.toList());
	}
	
	public CategoryDTO categoryModelToDTO(Category category) {
		return modelMapper.map(category, CategoryDTO.class);
	}
	
	public List<BrandDTO> brandModelToDTO(List<Brand> brands) {
		return brands
				.stream()
				.map(p -> modelMapper.map(p, BrandDTO.class))
				.collect(Collectors.toList());
	}
	
	public Page<BrandDTO> brandModelToDTO(Page<Brand> pagedBrands) {
		return pagedBrands.map(b -> brandModelToDTO(b));
	}
	
	public BrandDTO brandModelToDTO(Brand brand) {
		return modelMapper.map(brand, BrandDTO.class);
	}

	public ShoppingListDTO shoppingListModelToDTO(ShoppingList shoppingList) {
		return modelMapper.map(shoppingList, ShoppingListDTO.class);
	}
	
	public ProductListDTO productListModelToDTO(ProductList productList) {
		return modelMapper.map(productList, ProductListDTO.class);
	}
	
	//TODO: Adicionar outros métodos para mapeamento Entidade -> Interface
	/*Obs: Todos os Facade devem ter um objeto do tipo ModelMapperUtil para fazerem a conversão.
	 * Obs²: Verificar possibilidade e necessidade de usar o mapeamento para inserts no banco (testar POSTs
	 * para ver se permitem apenas os dados básicos de cada objeto)
	 */
}
