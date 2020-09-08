/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : MailProperties.java </p>
 * <p> Description: Bean class holds email properties</p>
 * <p> Created: Jul 2, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@ConfigurationProperties(prefix = "atlassian.availabilitychecker.email")
@Getter
@Setter
public class MailProperties {

	private String fromAddress;
	private String ccAddress;
	private String replyToAddress;
	private String toAddresses;
	private String subjectServerDown;
	private String subjectServerUp;
	private String contentServerDown;
	private String contentServerUp;
	private boolean sendMailWhenServerIsDown = true;
	private boolean sendMailWhenServerIsUp = true;
	private boolean appendApplnUrlInSubject = true;
	private int numberOfMailRemainders = 10000;
}
