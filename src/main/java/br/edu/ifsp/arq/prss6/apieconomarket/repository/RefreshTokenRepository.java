package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.RefreshToken;
import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.User;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByUserAndUserAgent(User user, String userAgent);

}
