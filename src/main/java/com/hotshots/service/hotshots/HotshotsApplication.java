package com.hotshots.service.hotshots;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class HotshotsApplication {
	
//	@Autowired
//    JobLauncher jobLauncher;
//     
//    @Autowired
//    Job job;

	public static void main(String[] args) {
		SpringApplication.run(HotshotsApplication.class, args);
	}
	


}
