package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.RefreshToken;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	List<RefreshToken> findByUserAndUserAgent(User user, String userAgent);
	
	void deleteByUserAndUserAgent(User user, String userAgent);

}
