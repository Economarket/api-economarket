package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.BrandDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Unity;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.FieldUtilsFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping(EndpointsConstMapping.FieldUtilsEP.MAIN)
public class FieldUtilsController {

	@Autowired
	private FieldUtilsFacade facade;
	
	@GetMapping(EndpointsConstMapping.FieldUtilsEP.UNITY)
	public List<Unity> getUnityValues() {
		return facade.getUnityValues();
	}
	
	@GetMapping(EndpointsConstMapping.FieldUtilsEP.BRAND)
	public Page<BrandDTO> getBrands(Pageable pagination) {
		return facade.getBrands(pagination);
	}
}
