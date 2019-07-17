package com.hotshots.service.hotshots.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.hotshots.service.hotshots.dao.HotShotRepository;
import com.hotshots.service.hotshots.entity.TimeSlotEntity;
import com.hotshots.service.hotshots.entity.TransactionEntity;
import com.hotshots.service.hotshots.entity.TransactionIdentity;

@Service
public class HotShotsService implements HotShotsInterface, TimeSlotInterface{

	private HotShotRepository hotShotRepository;
	private TimeSlotInterface timeSlotInterface;

	@Autowired
	public HotShotsService(HotShotRepository hotShotRepository, TimeSlotInterface timeSlotInterface) {
		this.hotShotRepository = hotShotRepository;
		this.timeSlotInterface = timeSlotInterface;
	}

	public HotShotRepository getHotShotRepository() {
		return hotShotRepository;
	}

	public void setHotShotRepository(HotShotRepository hotShotRepository) {
		this.hotShotRepository = hotShotRepository;
	}



	@Override
	public TransactionEntity findById(TransactionIdentity transactionIdentity) {
		// TODO Auto-generated method stub
		Optional<TransactionEntity> result =  this.hotShotRepository.findById(transactionIdentity);
		
		TransactionEntity transactionEntity = null;
		if(result.isPresent()) {
			transactionEntity = result.get();
		}
		
		// this.timeSlotInterface.findById(transactionEntity.getTransactionId());
		
		System.out.println(transactionEntity);
		return transactionEntity;
	}
	
	 

	@Override
	public TimeSlotEntity findById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionEntity> findAll(String bookingDate) {
		return this.hotShotRepository.findAllTheDetails(bookingDate);
	}



	
}
