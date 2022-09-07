package org.prsquared.normandy;

import org.prsquared.normandy.service.Game;
import org.prsquared.normandy.service.SimulationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NormandyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NormandyApplication.class, args);
		/*try {
			SimulationService service = new SimulationService();
			service.fastMode = false;
			service.isTest = false;
			service.simulate();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}*/
	}

}
