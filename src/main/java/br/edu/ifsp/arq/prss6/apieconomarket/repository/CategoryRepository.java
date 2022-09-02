package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.model.dao.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
