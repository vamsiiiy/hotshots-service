package com.hotshots.service.hotshots.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotshots.service.hotshots.entity.TimeSlotEntity;

public interface TimeSlotRepository extends JpaRepository<TimeSlotEntity, Integer>{

}
