/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.checker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : BitBucketAvailabilityChecker.java </p>
 * <p> Description: Checks the availability of bitbucket and notifies the person via configured emails</p>
 * <p> Created: Jul 1, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Slf4j
public class BitBucketAvailabilityChecker {

	@Value("${atlassian.availabilitychecker.bitbucket.url}")
	private String bambooUrl;

	@Value("${atlassian.availabilitychecker.bitbucket.error}")
	private String bambooError;

	private String errorXpathLocation = "/html/body/div/section/div/div/section/p[1]";

	private AvailabilityChecker availabilityChecker;

	public BitBucketAvailabilityChecker(AvailabilityChecker availabilityChecker) {
		this.availabilityChecker = availabilityChecker;
		log.info("BitBucketAvailabilityChecker is loaded");
	}

	public void triggerProcess() {
		availabilityChecker.updateConfigAndTriggerProcess(bambooUrl, bambooError, errorXpathLocation);
		log.info("BitBucketAvailabilityChecker process is triggered");
	}

}
