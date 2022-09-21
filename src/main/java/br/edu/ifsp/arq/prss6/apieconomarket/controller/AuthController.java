package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.arq.prss6.apieconomarket.facade.AuthFacade;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.EndpointsConstMapping;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthFacade authFacade;
	
	@GetMapping(EndpointsConstMapping.AuthEP.REFRESH_TOKEN)
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authFacade.refreshToken(request, response);
	}
	
	@PostMapping(EndpointsConstMapping.AuthEP.LOGOUT)
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		authFacade.logout(request, response);
	}
}
