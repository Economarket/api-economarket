package br.edu.ifsp.arq.prss6.apieconomarket.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.UserDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;

@Component
public class ModelMapperUtil {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserDTO userModelToDTO(User user) {
		return modelMapper.map(user, UserDTO.class);
	}
	
	//TODO: Adicionar outros métodos para mapeamento Entidade -> Interface
	/*Obs: Todos os Facade devem ter um objeto do tipo ModelMapperUtil para fazerem a conversão.
	 * Obs²: Verificar possibilidade e necessidade de usar o mapeamento para inserts no banco (testar POSTs
	 * para ver se permitem apenas os dados básicos de cada objeto)
	 */
}
