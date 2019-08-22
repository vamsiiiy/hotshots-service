package com.hotshots.service.hotshots.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hotshots.service.hotshots.entityDetails.UtilityInfo;

public interface UtilityRepository extends CrudRepository<UtilityInfo, Integer> {
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from utilityinfo where timeSlotId = :utilityId")
	void deleteTimeSlotId(@Param("utilityId")int timeSlotId);
}
