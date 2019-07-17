package com.hotshots.service.hotshots.service;

import java.util.List;

import com.hotshots.service.hotshots.entity.TransactionEntity;
import com.hotshots.service.hotshots.entity.TransactionIdentity;

public interface HotShotsInterface {

	List<TransactionEntity> findAll(String bookingDate);
	
	TransactionEntity findById(TransactionIdentity transactionIdentity);
	
//	List<TransactionEntity> findAllTheDetails(String bookingDate);
}
