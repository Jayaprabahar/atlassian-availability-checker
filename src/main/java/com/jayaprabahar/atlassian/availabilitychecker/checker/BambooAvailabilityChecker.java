/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.checker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : BambooAvailabilityChecker.java </p>
 * <p> Description: Checks the availability of bamboo and notifies the person via configured emails</p>
 * <p> Created: Jul 1, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Slf4j
public class BambooAvailabilityChecker {

	@Value("${atlassian.availabilitychecker.bamboo.url}")
	private String bambooUrl;

	@Value("${atlassian.availabilitychecker.bamboo.error}")
	private String bambooError;

	private String errorXpathLocation = "/html/body/div[1]/header/section/div/span[1]";

	private AvailabilityChecker availabilityChecker;

	public BambooAvailabilityChecker(AvailabilityChecker availabilityChecker) {
		this.availabilityChecker = availabilityChecker;
		log.info("BambooAvailabilityChecker is loaded");
	}

	public void triggerProcess() {
		availabilityChecker.updateConfigAndTriggerProcess(bambooUrl, bambooError, errorXpathLocation);
		log.info("BambooAvailabilityChecker process is triggered");
	}

}
