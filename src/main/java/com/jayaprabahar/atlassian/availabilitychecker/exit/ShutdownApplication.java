/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.exit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : ShutdownApplication.java </p>
 * <p> Description: Shuts down the whole Application </p>
 * <p> Created: Jul 12, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Configuration
public class ShutdownApplication {
	@Autowired
	private ApplicationContext appContext;

	/**
	 * 
	 */
	public void destroy() {
		SpringApplication.exit(appContext, () -> 0);
		System.exit(0);
	}
}
