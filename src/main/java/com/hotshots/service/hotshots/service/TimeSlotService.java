package com.hotshots.service.hotshots.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotshots.service.hotshots.entity.TimeSlotEntity;

import com.hotshots.service.hotshots.dao.*;

@Service
public class TimeSlotService implements TimeSlotInterface {

	
	private TimeSlotRepository TimeSlotRepository;
	
	public TimeSlotService(TimeSlotRepository timeSlotRepository) {
		TimeSlotRepository = timeSlotRepository;
	}

	@Override
	public TimeSlotEntity findById(int id) {
		
		TimeSlotEntity timeSlotEntity = null;
		Optional<TimeSlotEntity> result = this.TimeSlotRepository.findById(id);
		
		if(result.isPresent()) {
			timeSlotEntity = result.get();
		}

		return timeSlotEntity;
	}

}
