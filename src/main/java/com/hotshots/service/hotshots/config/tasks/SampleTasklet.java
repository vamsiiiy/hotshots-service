package com.hotshots.service.hotshots.config.tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.hotshots.service.hotshots.dao.BookingRepository;
import com.hotshots.service.hotshots.entityDetails.BookingInfo;

public class SampleTasklet implements Tasklet {

    @Autowired
    private BookingRepository bookingRepository;
    
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//    	Date date = new Date();
//    	System.out.println(dateFormat.format(date));
		
		if(this.bookingRepository != null) {
			List<BookingInfo> bookingInfo = this.bookingRepository.findByBookingDate("11-06-2018");
			
			
		}else {
			System.out.println("bookingRepository is null");
		}
		return RepeatStatus.FINISHED;
	}

}
