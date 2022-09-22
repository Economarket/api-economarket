package br.edu.ifsp.arq.prss6.apieconomarket.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
	
	/* APENAS PARA DEBUG
	 * E pela zoeira :)
	 * */

	@GetMapping
	@ResponseBody
	public void downloadUserAvatarImage(HttpServletResponse response) throws IOException {

		ClassPathResource imgFile = new ClassPathResource("/img/image.png");
		 
	    response.setContentType(MediaType.IMAGE_PNG_VALUE);
	    StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
	}
}
