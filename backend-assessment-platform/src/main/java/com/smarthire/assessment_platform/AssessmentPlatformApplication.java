package com.smarthire.assessment_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AssessmentPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentPlatformApplication.class, args);
	}

}
