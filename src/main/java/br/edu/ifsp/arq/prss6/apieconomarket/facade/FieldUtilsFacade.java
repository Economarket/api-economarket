package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Unity;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.BrandRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.ModelMapperUtil;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UnityEnum;

@Service
public class FieldUtilsFacade {
	
	@Autowired
	private ModelMapperUtil modelMapperUtil;
	
	@Autowired
	private BrandRepository brandRepository;

	public List<Unity> getUnityValues() {
		List<Unity> unityList = new ArrayList<>();
		
		for(UnityEnum u : UnityEnum.values()) {
			unityList.add(new Unity(u.name(), u.getValue()));
		}
		
		return unityList;
	}
	
	public Page<BrandDTO> getBrands(Pageable pagination) {
		return modelMapperUtil.brandModelToDTO(brandRepository.findAll(pagination));
	}

}
