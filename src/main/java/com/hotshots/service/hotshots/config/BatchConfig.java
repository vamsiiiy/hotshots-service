package com.hotshots.service.hotshots.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.hotshots.service.hotshots.config.tasks.SampleTasklet;
import com.hotshots.service.hotshots.dao.BookingRepository;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	

    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
    
    @Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;
    


    
    @Bean
    public Job TestJob() {
    	return this.jobs.get("TestJob").start(Step1()).build();
    }
    
    @Bean
    public Step Step1() {
    	return this.steps.get("Step1").tasklet(sampleTasklet()).build();
    }
    
    @Bean
    public Tasklet FirstTasklet() {
        return (contribution, chunkContext) -> {
        	// Fetch todays details
        	
//        	this.bookingRepository.findByBookingDate(bookingDate)
        	
            return RepeatStatus.FINISHED;
        };
    }
    
    @Bean
    public SampleTasklet sampleTasklet(){
    	return new SampleTasklet();
    }
    
//	@Scheduled(fixedRate = 5000)
//	public void run() throws Exception {
//	    jobLauncher.run(
//	    		TestJob(),
//	        new JobParametersBuilder().addLong("uniqueness", System.nanoTime()).toJobParameters()
//	    );
//	}

}
