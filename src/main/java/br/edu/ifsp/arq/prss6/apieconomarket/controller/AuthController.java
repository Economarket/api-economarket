package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.dto.LoginDTO;
import br.edu.ifsp.arq.prss6.apieconomarket.facade.AuthFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthFacade authFacade;
	
	//Método falso para mapeamento do SpringFox
	@ApiOperation("Login.")
	@PostMapping("/login")
	public void fakeLogin(@ApiParam("Credentials") @RequestBody LoginDTO loginDTO) {
	    throw new IllegalStateException("Esse método não deveria ser chamado. Ele é chamado pelos filtros de segurança do Spring.");
	}
	
	@GetMapping(EndpointsConstMapping.AuthEP.REFRESH_TOKEN)
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authFacade.refreshToken(request, response);
	}
	
	@PostMapping(EndpointsConstMapping.AuthEP.LOGOUT)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authFacade.logout(request, response);
	}
}
