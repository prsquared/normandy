package org.prsquared.normandy;

import org.prsquared.normandy.service.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NormandyApplication {

	public static void main(String[] args) {
		//SpringApplication.run(NormandyApplication.class, args);
		new Game().play();
	}

}
