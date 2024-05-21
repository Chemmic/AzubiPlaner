package de.dhbw.softwareengineering.azubiplaner.application.rules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleConfig {

	  @Bean
	  public NonConsecutiveDaysRule nonConsecutiveDaysRule() {
	      return new NonConsecutiveDaysRule();
	  }

	  @Bean
	  public FridayRule fridayRule() {
	      return new FridayRule();
	  }
	
}
