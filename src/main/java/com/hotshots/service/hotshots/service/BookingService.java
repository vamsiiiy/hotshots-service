package com.hotshots.service.hotshots.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotshots.service.hotshots.dao.BookingRepository;
import com.hotshots.service.hotshots.dao.TimeSlotRepository;
import com.hotshots.service.hotshots.dao.UtilityRepository;
import com.hotshots.service.hotshots.email.EmailService;
import com.hotshots.service.hotshots.entityDetails.BookingInfo;
import com.hotshots.service.hotshots.entityDetails.CourtInfo;
import com.hotshots.service.hotshots.entityDetails.PaymentDetails;
import com.hotshots.service.hotshots.entityDetails.TimeSlotInfo;
import com.hotshots.service.hotshots.entityDetails.UtilityInfo;

@Service
public class BookingService {

	private BookingRepository bookingRepository;
	private TimeSlotRepository timeSlotRepository;
	private UtilityRepository utilityRepository;
	
	@Autowired
	public EmailService EmailService;

	public TimeSlotRepository getTimeSlotRepository() {
		return timeSlotRepository;
	}

	public void setTimeSlotRepository(TimeSlotRepository timeSlotRepository) {
		this.timeSlotRepository = timeSlotRepository;
	}

	@Autowired
	public BookingService(BookingRepository bookingRepository, TimeSlotRepository timeSlotRepository, UtilityRepository utilityRepository) {
		this.bookingRepository = bookingRepository;
		this.timeSlotRepository = timeSlotRepository;
		this.utilityRepository = utilityRepository;
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
			if(!areCourtsNotAvailable && courtInfo.getTimeSlotDetails() != null) {
				courtInfo.setBookingInfo(bookingItem);
				for(TimeSlotInfo timeSlot: courtInfo.getTimeSlotDetails()) {
					timeSlot.setCourtInfo(courtInfo);
					timeSlot.getBookingDetails().setTimeSlotInfo(timeSlot);
					for(UtilityInfo utilityDetails: timeSlot.getUtilityInfoDetails()) {
						utilityDetails.setTimeSlotInfo(timeSlot);
					}
					timeSlot.getPaymentDetails().setTimeSlotInfo(timeSlot);
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
				
				
				// If there is no Utility information in DB and a new value is inserted
				if(timeSlot.getUtilityInfoDetails() == null && timeSlotInfoRequest.getUtilityInfoDetails() != null) {
					
					Set<UtilityInfo> newutilityInfoDetailsCollection = new 	HashSet<UtilityInfo>();
					for(UtilityInfo utilityDetailsRequest: timeSlotInfoRequest.getUtilityInfoDetails()) {
						UtilityInfo newUtiltiy = new UtilityInfo();
						newUtiltiy.setTimeSlotInfo(timeSlot);
						newUtiltiy.setUtilityName(utilityDetailsRequest.getUtilityName());
						newUtiltiy.setUtilityPrice(utilityDetailsRequest.getUtilityPrice());
						newUtiltiy.setUtilityQuantity(utilityDetailsRequest.getUtilityQuantity());
						newutilityInfoDetailsCollection.add(newUtiltiy);
					}
					
					timeSlot.setUtilityInfoDetails(newutilityInfoDetailsCollection);
				}
				else if(timeSlot.getUtilityInfoDetails() != null && timeSlotInfoRequest.getUtilityInfoDetails() != null){
//
//					for(Iterator<UtilityInfo> featureIterator = timeSlot.getUtilityInfoDetails().iterator(); 
//						    featureIterator.hasNext(); ) {
//							UtilityInfo feature = featureIterator.next();
//						    feature.setTimeSlotInfo(null);
//						    featureIterator.remove();
//						}
					this.utilityRepository.deleteTimeSlotId(timeSlot.getTimeSlotId());
//					this.utilityRepository.save(entity)
//					timeSlot.getUtilityInfoDetails().clear();
					for(UtilityInfo utilityDetailsRequest: timeSlotInfoRequest.getUtilityInfoDetails()) {
						UtilityInfo newUtiltiy = new UtilityInfo();
						newUtiltiy.setTimeSlotInfo(timeSlot);
						newUtiltiy.setUtilityName(utilityDetailsRequest.getUtilityName());
						newUtiltiy.setUtilityPrice(utilityDetailsRequest.getUtilityPrice());
						newUtiltiy.setUtilityQuantity(utilityDetailsRequest.getUtilityQuantity());
						timeSlot.getUtilityInfoDetails().add(newUtiltiy);
					}										
				}

				if(timeSlot.getPaymentDetails() == null) {
					timeSlot.setPaymentDetails(new PaymentDetails());
				}
				timeSlot.getPaymentDetails().setTimeSlotInfo(timeSlot);
				
				timeSlot.getPaymentDetails().setPaymentMode(timeSlotInfoRequest.getPaymentDetails().getPaymentMode());				
				timeSlot.getPaymentDetails().setIsPaymentDone(timeSlotInfoRequest.getPaymentDetails().isIsPaymentDone());
				timeSlot.getPaymentDetails().setReceivedBy(timeSlotInfoRequest.getPaymentDetails().getReceivedBy());
				timeSlot.getPaymentDetails().setPaidTo(timeSlotInfoRequest.getPaymentDetails().getPaidTo());
				areTimeSlotsBooked = true;
			} 
		}
		// If no TimeSlot exists than add the timeSlot details
		if(!areTimeSlotsBooked) {
			timeSlotInfoRequest.setCourtInfo(courtItem);
			timeSlotInfoRequest.getBookingDetails().setTimeSlotInfo(timeSlotInfoRequest);
			courtItem.getTimeSlotDetails().add(timeSlotInfoRequest);
			for(UtilityInfo utilty: timeSlotInfoRequest.getUtilityInfoDetails()) {
				utilty.setTimeSlotInfo(timeSlotInfoRequest);
			}
			timeSlotInfoRequest.getPaymentDetails().setTimeSlotInfo(timeSlotInfoRequest);
		}
	}
	
	
	public List<BookingInfo> updateBasedOnTimeSlotId(String dateDetails, int timeSlotId) {
		
		Optional<TimeSlotInfo> timeSlotInfo = this.timeSlotRepository.findById(timeSlotId);
		if(timeSlotInfo != null) {
			timeSlotInfo.get().setSlotBooked(false);
			timeSlotInfo.get().getBookingDetails().setBookingName("");
			timeSlotInfo.get().getBookingDetails().setMobilenumber("");
			timeSlotInfo.get().setSlotBooked(false);
			for (UtilityInfo utility: timeSlotInfo.get().getUtilityInfoDetails()
				 ) {
				utility.setUtilityName("");
				utility.setUtilityPrice(0);
				utility.setUtilityQuantity(0);
			}
			timeSlotInfo.get().getPaymentDetails().setIsPaymentDone(false);
			timeSlotInfo.get().getPaymentDetails().setPaidTo("");
			timeSlotInfo.get().getPaymentDetails().setPaymentMode("");
			timeSlotInfo.get().getPaymentDetails().setReceivedBy("");
			this.timeSlotRepository.save(timeSlotInfo.get());
			return this.bookingRepository.findByBookingDate(dateDetails);
		}
		return null;
	}
	
	public String TestBatchProcess() {
		this.EmailService.BuildContextData(this.bookingRepository.findByBookingDate("11-06-2018"));
		this.EmailService.sendEmail();
		return "";
	}
	
}
