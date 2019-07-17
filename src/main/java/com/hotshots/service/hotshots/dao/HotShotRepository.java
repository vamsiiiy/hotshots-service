package com.hotshots.service.hotshots.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotshots.service.hotshots.entity.TransactionEntity;
import com.hotshots.service.hotshots.entity.TransactionIdentity;

public interface HotShotRepository extends JpaRepository<TransactionEntity, TransactionIdentity>{

	@Query("select u from TransactionEntity u where u.transactionIdentity.bookingDate= :bookingDate")
	public List<TransactionEntity> findAllTheDetails(@Param("bookingDate") String bookingDate);
}
