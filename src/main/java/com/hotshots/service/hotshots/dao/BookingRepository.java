package com.hotshots.service.hotshots.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotshots.service.hotshots.entityDetails.BookingInfo;

@Repository
public interface BookingRepository extends CrudRepository<BookingInfo, Integer>{
 	List<BookingInfo> findByBookingDate(String bookingDate);
}
