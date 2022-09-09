package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.ProductDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Product;
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
	
	public List<ProductDTO> productModelToDTO(List<Product> products) {
		return products
				.stream()
				.map(p -> modelMapper.map(p, ProductDTO.class))
				.collect(Collectors.toList());
	}
	
	public ProductDTO productModelToDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
	}
	
	//TODO: Adicionar outros métodos para mapeamento Entidade -> Interface
	/*Obs: Todos os Facade devem ter um objeto do tipo ModelMapperUtil para fazerem a conversão.
	 * Obs²: Verificar possibilidade e necessidade de usar o mapeamento para inserts no banco (testar POSTs
	 * para ver se permitem apenas os dados básicos de cada objeto)
	 */
}
