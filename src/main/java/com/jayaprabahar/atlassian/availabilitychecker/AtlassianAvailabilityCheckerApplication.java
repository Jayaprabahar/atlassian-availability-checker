package com.jayaprabahar.atlassian.availabilitychecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : AtlassianAvailabilityCheckerApplication.java </p>
 * <p> Description: Main class</p>
 * <p> Created: Jul 1, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@SpringBootApplication
@EnableScheduling
public class AtlassianAvailabilityCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlassianAvailabilityCheckerApplication.class, args);
	}

}
