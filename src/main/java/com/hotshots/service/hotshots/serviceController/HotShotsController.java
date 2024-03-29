package com.hotshots.service.hotshots.serviceController;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotshots.service.hotshots.entityDetails.BookingInfo;
import com.hotshots.service.hotshots.entityDetails.CourtInfo;
import com.hotshots.service.hotshots.entityDetails.TimeSlotInfo;
import com.hotshots.service.hotshots.service.BookingService;

@RestController
@RequestMapping("/hot-shots/api")
public class HotShotsController {
	
	private BookingService  bookingService;
	
	public HotShotsController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@GetMapping("/getBookingDetails")
	public List<BookingInfo> getAllBookingDetails(@RequestParam("bookingDate") String bookingDate){
		System.out.println(bookingDate);
		return this.bookingService.getBookingRepository().findByBookingDate(bookingDate);
	}
	
	@PostMapping("/createNew")
	public BookingInfo createNewRecord(@RequestBody BookingInfo bookingInfo){
		System.out.println(bookingInfo);
		for (CourtInfo iterable_element : bookingInfo.getCourtDetailsList()) {
			iterable_element.setBookingInfo(bookingInfo);
			for (TimeSlotInfo iterable_slot : iterable_element.getTimeSlotDetails()) {
				iterable_slot.setCourtInfo(iterable_element);
				iterable_slot.getBookingDetails().setTimeSlotInfo(iterable_slot);
			}
		}
		
		return this.bookingService.getBookingRepository().save(bookingInfo);
	}
	
	@PostMapping("/updateCourtDetails/{bookingId}")   
	public BookingInfo saveUser(@RequestBody CourtInfo courtInfo, @PathVariable String bookingId) throws Exception{
		System.out.println(bookingId);
		return this.bookingService.saveCourtDetails(courtInfo, bookingId);
	   
	}
	
	@GetMapping("/cancelTimeSlot/{timeSlotId}")   
	public String cancelTimeSlot(@PathVariable int timeSlotId) throws Exception{
		System.out.println(timeSlotId);
		 return this.bookingService.updateBasedOnTimeSlotId(timeSlotId);   
	}
	
}
