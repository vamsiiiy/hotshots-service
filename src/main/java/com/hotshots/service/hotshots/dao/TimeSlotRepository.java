package com.hotshots.service.hotshots.dao;
import org.springframework.data.repository.CrudRepository;

import com.hotshots.service.hotshots.entityDetails.TimeSlotInfo;

public interface TimeSlotRepository extends CrudRepository<TimeSlotInfo, Integer> {

}
