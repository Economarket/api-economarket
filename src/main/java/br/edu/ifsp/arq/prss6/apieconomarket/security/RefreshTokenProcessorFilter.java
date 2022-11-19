package br.edu.ifsp.arq.prss6.apieconomarket.security;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.catalina.util.ParameterMap;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.edu.ifsp.arq.prss6.apieconomarket.config.JWTBuilder;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenProcessorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(("/auth/token/refresh".equalsIgnoreCase(req.getRequestURI()) || 
				"/auth/logout".equalsIgnoreCase(req.getRequestURI())) 
				&& req.getCookies() != null) {
			
			String refreshToken =
					Stream.of(req.getCookies())
						.filter(cookie -> "refreshToken".equals(cookie.getName()))
						.findFirst()
						.map(cookie -> cookie.getValue())
						.orElse(null);
//			req = new MyServletRequestWrapper(req, refreshToken);
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					JWTBuilder.getAuthenticationToken(refreshToken);
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
		}
		
		chain.doFilter(request, response);
	}

	static class MyServletRequestWrapper extends HttpServletRequestWrapper{

		private String refreshToken;
		
		public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
			super(request);
			this.refreshToken = refreshToken;
		}
		
		@Override
		public Map<String, String[]> getParameterMap() {
			ParameterMap<String, String[]> map = 
					new ParameterMap<>(getRequest().getParameterMap());
			map.put("refresh_token", new String[] { refreshToken });
			map.setLocked(true);
			return map;
		}
	}
}
