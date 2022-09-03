package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.ShoppingList;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingList, Long> {

	public List<ShoppingList> findByUserId(long userId);
}
