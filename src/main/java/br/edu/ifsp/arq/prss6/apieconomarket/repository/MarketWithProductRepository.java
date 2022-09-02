package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.MarketWithProduct;

public interface MarketWithProductRepository extends JpaRepository<MarketWithProduct, Long> {

	//TODO: Criar query personalizada para a busca
	//public List<MarketWithProductRepository> findByMarketId(long marketId);
	
	//TODO: Criar query personalizada para a busca
	//public List<Product> findByMarketIdAndProductName(long marketId, String productName);
}
