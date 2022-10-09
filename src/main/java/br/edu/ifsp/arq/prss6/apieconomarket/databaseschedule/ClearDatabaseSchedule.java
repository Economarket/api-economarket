package br.edu.ifsp.arq.prss6.apieconomarket.databaseschedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.edu.ifsp.arq.prss6.apieconomarket.domain.model.RefreshToken;
import br.edu.ifsp.arq.prss6.apieconomarket.service.RefreshTokenService;
import br.edu.ifsp.arq.prss6.apieconomarket.utils.UtilsCons;

@Component
@EnableScheduling
public class ClearDatabaseSchedule {
	
	@Autowired
	private RefreshTokenService refreshTokenService;

	@Scheduled(cron = "* */15 * * * *")
	public void schedule() {
		this.clearDatabase();
	}
	
	private void clearDatabase() {
		List<RefreshToken> rf = refreshTokenService.findAll();
		
		if(!rf.isEmpty()) {
			for(RefreshToken r : rf) {
				LocalDateTime dateTimeToken = r.getDateTime();
				LocalDateTime now = LocalDateTime.now();

				if(now.minusSeconds(UtilsCons.REFRESH_TO_SECONDS).isAfter(dateTimeToken)) {
					refreshTokenService.invalidateRefreshToken(r.getUser(), r.getUserAgent());
				}
			}
		}
	}
	
}
