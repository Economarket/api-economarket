package br.edu.ifsp.arq.prss6.apieconomarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	List<Schedule> findByMarketId(Long id);

}
