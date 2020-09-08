/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.checker;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jayaprabahar.atlassian.availabilitychecker.counter.ReminderCounter;
import com.jayaprabahar.atlassian.availabilitychecker.mail.config.MailProperties;
import com.jayaprabahar.atlassian.availabilitychecker.mail.service.MailSenderService;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : AvailabilityChecker.java </p>
 * <p> Description: AvailabilityChecker</p>
 * <p> Created: Jul 12, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Slf4j
public final class AvailabilityChecker {

	private MailSenderService mailSenderService;
	private MailProperties mailProperties;
	private ReminderCounter reminderCounter;

	@Value("#{new Integer('${atlassian.availabilitychecker.loadTimeoutInSeconds}')}")
	public Integer loadTimeout;

	@Value("${atlassian.availabilitychecker.headlessRun:true}")
	private boolean headlessRun;

	private WebDriver webDriver;
	private String applicationUrl;
	private String xpathLocation;
	private String applicationError;

	/**
	 * Initializes the bean
	 */
	@Autowired
	public AvailabilityChecker(MailSenderService mailSenderService, MailProperties mailProperties,
			ReminderCounter reminderCounter) {
		this.mailSenderService = mailSenderService;
		this.mailProperties = mailProperties;
		this.reminderCounter = reminderCounter;
	}

	/**
	 * 
	 */
	public void updateConfigAndTriggerProcess(String applicationUrl, String applicationError, String xpathLocation) {
		this.applicationUrl = applicationUrl;
		this.applicationError = applicationError;
		this.xpathLocation = xpathLocation;
		executeProcess();
	}

	/**
	 * Initializes the driver. Suitable for Springboot where chomedriver exe can't
	 * be placed inside the SpringBoot jar
	 */
	@PostConstruct
	private void initDriverPath() {
		WebDriverManager.chromedriver().setup();
	}

	/**
	 * Steps to be executed
	 */
	public void executeProcess() {
		initDriverPath();
		invokeWebDriver();
		openLoginPage();
		triggerMail(isApplicationRunning());
		tearDown();
	}

	/**
	 * Invokes the bonigarcia webdriver
	 */
	private void invokeWebDriver() {
		ChromeOptions options = new ChromeOptions();

		if (headlessRun)
			options.addArguments("headless");

		webDriver = new ChromeDriver(options);
		webDriver.manage().deleteAllCookies();
		webDriver.manage().timeouts().pageLoadTimeout(loadTimeout, TimeUnit.SECONDS);
	}

	/**
	 * Webdriver opens the application
	 */
	private void openLoginPage() {
		webDriver.get(applicationUrl);
	}

	/**
	 * Checks whether the application is running or not. It varies according to your
	 * application
	 * 
	 * @return boolean - TRUE if the application is running, FALSE if not
	 */
	public boolean isApplicationRunning() {
		try {
			WebElement webElement = webDriver.findElement(By.xpath(xpathLocation));
			return !StringUtils.contains(webElement.getText(), applicationError);
		} catch (NoSuchElementException e) {
			// Means error element is not found
			return true;
		}
	}

	/**
	 * Triggers email service based on the server status and flag for sending email
	 * 
	 * @param isApplicationRunning
	 */
	private void triggerMail(boolean isApplicationRunning) {
		log.info("Server is {} for {}", BooleanUtils.toStringOnOff(isApplicationRunning).toUpperCase(), applicationUrl);
		if ((isApplicationRunning && mailProperties.isSendMailWhenServerIsUp())
				|| (!isApplicationRunning && mailProperties.isSendMailWhenServerIsDown())) {
			mailSenderService.sendEmail(isApplicationRunning, applicationUrl);
			reminderCounter.getCounter().incrementAndGet();
		}
	}

	/**
	 * Kill the webdriver
	 */
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

}
