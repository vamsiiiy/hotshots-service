package com.hotshots.service.hotshots.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotshots.service.hotshots.dao.BookingRepository;
import com.hotshots.service.hotshots.dao.TimeSlotRepository;
import com.hotshots.service.hotshots.entityDetails.BookingInfo;
import com.hotshots.service.hotshots.entityDetails.CourtInfo;
import com.hotshots.service.hotshots.entityDetails.TimeSlotInfo;

@Service
public class BookingService {

	private BookingRepository bookingRepository;
	private TimeSlotRepository timeSlotRepository;

	public TimeSlotRepository getTimeSlotRepository() {
		return timeSlotRepository;
	}

	public void setTimeSlotRepository(TimeSlotRepository timeSlotRepository) {
		this.timeSlotRepository = timeSlotRepository;
	}

	@Autowired
	public BookingService(BookingRepository bookingRepository, TimeSlotRepository timeSlotRepository) {
		this.bookingRepository = bookingRepository;
		this.timeSlotRepository = timeSlotRepository;
	}

	public BookingRepository getBookingRepository() {
		return bookingRepository;
	}

	public void setBookingRepository(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	public BookingInfo saveCourtDetails(CourtInfo courtInfo, String bookingDate) {
		
		List<BookingInfo> bookingInfo = this.bookingRepository.findByBookingDate(bookingDate);
		boolean areCourtsNotAvailable = false;
		if(bookingInfo != null && bookingInfo.size() > 0) {
			BookingInfo bookingItem = bookingInfo.get(0);

			for(CourtInfo courtItem: bookingItem.getCourtDetailsList()) {
				
				// If Court is available, than add the timeslot details
				if(courtItem.getCourtName().equals(courtInfo.getCourtName())) {

					areCourtsNotAvailable = true;
					this.UpdateTimeSlots(courtItem, courtInfo.getTimeSlotDetails().iterator().next());
					return this.bookingRepository.save(bookingItem);
			
				} 
			}
			if(!areCourtsNotAvailable) {
				courtInfo.setBookingInfo(bookingItem);
				for(TimeSlotInfo timeSlot: courtInfo.getTimeSlotDetails()) {
					timeSlot.setCourtInfo(courtInfo);
					timeSlot.getBookingDetails().setTimeSlotInfo(timeSlot);
					courtInfo.getTimeSlotDetails().add(timeSlot);
				}
				
				// add the Court Details
				bookingItem.getCourtDetailsList().add(courtInfo);
				return this.bookingRepository.save(bookingItem);
			}
			
		}
		return null;
	}
	
	private void UpdateTimeSlots(CourtInfo courtItem, TimeSlotInfo timeSlotInfoRequest) {
		boolean areTimeSlotsBooked = false;
		for(TimeSlotInfo timeSlot: courtItem.getTimeSlotDetails()) {
			
			// If already a TimeSlot exists than update
			if(timeSlot.getTimeSlotCode().equals(timeSlotInfoRequest.getTimeSlotCode())) {
				timeSlot.getBookingDetails().setBookingName(timeSlotInfoRequest.getBookingDetails().getBookingName());
				timeSlot.getBookingDetails().setMobilenumber(timeSlotInfoRequest.getBookingDetails().getMobilenumber());
				timeSlot.setSlotBooked(true);
				areTimeSlotsBooked = true;
			} 
		}
		// If no TimeSlot exists than add the timeSlot details
		if(!areTimeSlotsBooked) {
			timeSlotInfoRequest.setCourtInfo(courtItem);
			timeSlotInfoRequest.getBookingDetails().setTimeSlotInfo(timeSlotInfoRequest);
			courtItem.getTimeSlotDetails().add(timeSlotInfoRequest);
		}
	}
	
	
	public String updateBasedOnTimeSlotId(int timeSlotId) {
		
		Optional<TimeSlotInfo> timeSlotInfo = this.timeSlotRepository.findById(timeSlotId);
		if(timeSlotInfo != null) {
			timeSlotInfo.get().setSlotBooked(false);
			timeSlotInfo.get().getBookingDetails().setBookingName("");
			timeSlotInfo.get().getBookingDetails().setMobilenumber("");
			this.timeSlotRepository.save(timeSlotInfo.get());
			return "Cancelled";
		}
		return "Couldn't find the time Slot";
	}
	
}
