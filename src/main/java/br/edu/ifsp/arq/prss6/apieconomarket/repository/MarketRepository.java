package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Market;

public interface MarketRepository extends JpaRepository<Market, Long> {

	public List<Market> findByNameLike(String nome);
}
