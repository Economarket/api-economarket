package br.edu.ifsp.arq.prss6.apieconomarket.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.RefreshToken;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.RefreshTokenRepository;
import br.edu.ifsp.arq.prss6.apieconomarket.repository.UserRepository;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void registerRefreshTokenOnDatabase(User user, String userAgent, String refreshToken) {
		refreshTokenRepository.save(new RefreshToken(user, userAgent, refreshToken));
	}
	
	@Transactional
	public void invalidateRefreshToken(User user, String userAgent) {
		List<RefreshToken> refreshTokens = findRefreshTokenByUserAndUserAgent(user, userAgent);
		
		refreshTokens.forEach(rt -> refreshTokenRepository.deleteById(rt.getId()));
	}
	
	public List<RefreshToken> findRefreshTokenByUserAndUserAgent(String user, String userAgent) {
		Optional<User> optUser = userRepository.findByEmail(user);
		
		return findRefreshTokenByUserAndUserAgent(optUser.get(), userAgent);
	}
	
	public List<RefreshToken> findRefreshTokenByUserAndUserAgent(User user, String userAgent) {
		List<RefreshToken> optRefreshToken = 
				refreshTokenRepository.findByUserAndUserAgent(user, userAgent);
		
		if(optRefreshToken.isEmpty()) {
			throw new EntityNotFoundException("Refresh token invalid or expired.");
		}
		
		return optRefreshToken;
	}
}
