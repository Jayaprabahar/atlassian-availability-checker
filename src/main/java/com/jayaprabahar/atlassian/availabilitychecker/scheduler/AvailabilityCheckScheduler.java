package com.jayaprabahar.atlassian.availabilitychecker.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jayaprabahar.atlassian.availabilitychecker.checker.BambooAvailabilityChecker;
import com.jayaprabahar.atlassian.availabilitychecker.checker.BitBucketAvailabilityChecker;
import com.jayaprabahar.atlassian.availabilitychecker.counter.ReminderCounter;
import com.jayaprabahar.atlassian.availabilitychecker.exit.ShutdownApplication;
import com.jayaprabahar.atlassian.availabilitychecker.mail.config.MailProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : AvailabilityCheckScheduler.java </p>
 * <p> Description: AvailabilityCheckScheduler </p>
 * <p> Created: Jul 1, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Slf4j
public class AvailabilityCheckScheduler {

	private BambooAvailabilityChecker bambooAvailabilityCheckerJob;
	private BitBucketAvailabilityChecker bitBucketAvailabilityCheckerJob;
	private ReminderCounter reminderCounter;
	private MailProperties mailProperties;
	private ShutdownApplication shutdownApplication;

	/**
	 * 
	 */
	@Autowired
	public AvailabilityCheckScheduler(BambooAvailabilityChecker bambooAvailabilityCheckerJob, BitBucketAvailabilityChecker bitBucketAvailabilityCheckerJob,
	        ReminderCounter reminderCounter, MailProperties mailProperties, ShutdownApplication shutdownApplication) {
		this.bambooAvailabilityCheckerJob = bambooAvailabilityCheckerJob;
		this.bitBucketAvailabilityCheckerJob = bitBucketAvailabilityCheckerJob;
		this.reminderCounter = reminderCounter;
		this.mailProperties = mailProperties;
		this.shutdownApplication = shutdownApplication;

	}

	@Scheduled(fixedRateString = "${atlassian.availabilitychecker.schedulerTimeInSeconds:300}000")
	public void execute() {
		if (reminderCounter.getCounter().get() < mailProperties.getNumberOfMailRemainders()) {
			log.info("-----------------Scheduler Started --------------");
			bambooAvailabilityCheckerJob.triggerProcess();
			bitBucketAvailabilityCheckerJob.triggerProcess();
			log.info("-----------------Scheduler Ended --------------");
		} else {
			shutdownApplication.destroy();
			log.info("-----------------Scheduler stopped as the maximum number of remainders are sent --------------");
		}
	}

}
