package com.jayaprabahar.atlassian.availabilitychecker.mail.service;

import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.jayaprabahar.atlassian.availabilitychecker.mail.config.MailProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : MailSenderService.java </p>
 * <p> Description: Send mail service</p>
 * <p> Created: Jul 1, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Slf4j
public class MailSenderService {

	private JavaMailSender javaMailSender;
	private MailProperties mailProperties;

	@Autowired
	public MailSenderService(JavaMailSender javaMailSender, MailProperties mailProperties) {
		this.javaMailSender = javaMailSender;
		this.mailProperties = mailProperties;
	}

	/**
	 * This method sends email with the passed content
	 * 
	 * @param isApplicationRunning 
	 * 
	 */
	public void sendEmail(boolean isApplicationRunning, String applicationUrl) {
		log.info("Preparing mail for the details");

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

			String subject = isApplicationRunning ? mailProperties.getSubjectServerUp() : mailProperties.getSubjectServerDown();

			if (mailProperties.isAppendApplnUrlInSubject())
				subject = String.join(StringUtils.SPACE, subject, applicationUrl);

			messageHelper.setSubject(subject);
			messageHelper.setText(isApplicationRunning ? mailProperties.getContentServerUp() : mailProperties.getContentServerDown(), true);
			messageHelper.setFrom(mailProperties.getFromAddress());
			messageHelper.setCc(mailProperties.getCcAddress());
			messageHelper.setReplyTo(mailProperties.getReplyToAddress());
			messageHelper.setTo(InternetAddress.parse(mailProperties.getToAddresses()));
		};

		try {
			javaMailSender.send(messagePreparator);
			log.info("Mail successfully sent to {}", mailProperties.getToAddresses());
		} catch (MailException e) {
			log.error("Exception", e);
		}
	}
}
