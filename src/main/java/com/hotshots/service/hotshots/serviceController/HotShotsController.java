package com.hotshots.service.hotshots.serviceController;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotshots.service.hotshots.entity.TransactionEntity;
import com.hotshots.service.hotshots.entity.TransactionIdentity;
import com.hotshots.service.hotshots.entityDetails.BookingInfo;
import com.hotshots.service.hotshots.service.BookingService;
import com.hotshots.service.hotshots.service.HotShotsInterface;

@RestController
@RequestMapping("/hot-shots/api")
public class HotShotsController {

	private  HotShotsInterface HotShotsInterface;
	
	private BookingService  bookingService;
	
	public HotShotsController(com.hotshots.service.hotshots.service.HotShotsInterface hotShotsInterface,
			BookingService bookingService) {
		HotShotsInterface = hotShotsInterface;
		this.bookingService = bookingService;
	}
	
	@GetMapping("/allCourts")
	public List<TransactionEntity> getAllCourtDetails(@RequestParam("bookingDate") String bookingDate){
		System.out.println(bookingDate);
		return this.HotShotsInterface.findAll(bookingDate);
	}
	
	@PostMapping("/getSpecificDetails")
	public TransactionEntity getSpecificDetails(@RequestBody TransactionIdentity transactionIdentity) {
		return this.HotShotsInterface.findById(transactionIdentity);
	}
	
	@GetMapping("/getBookingDetails")
	public List<BookingInfo> getAllBookingDetails(@RequestParam("bookingDate") String bookingDate){
		System.out.println(bookingDate);
		return this.bookingService.getBookingRepository().findByBookingDate(bookingDate);
	}
}
