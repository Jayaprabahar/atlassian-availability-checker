/**
 * 
 */
package com.jayaprabahar.atlassian.availabilitychecker.counter;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * <p> Project : atlassianavailabilitychecker </p>
 * <p> Title : ReminderCounter.java </p>
 * <p> Description: ReminderCounter</p>
 * <p> Created: Jul 12, 2020</p>
 * 
 * @version 1.0.0
 * @author <a href="mailto:jpofficial@gmail.com">Jayaprabahar</a>
 * 
 */
@Component
@Getter
public class ReminderCounter {

	private AtomicInteger counter = new AtomicInteger();

}
