package br.edu.ifsp.arq.prss6.apieconomarket.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Unity;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UnityEnum;

@Service
public class FieldUtilsFacade {

	public List<Unity> getUnityValues() {
		List<Unity> unityList = new ArrayList<>();
		
		for(UnityEnum u : UnityEnum.values()) {
			unityList.add(new Unity(u.name(), u.getValue()));
		}
		
		return unityList;
	}

}
