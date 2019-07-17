package com.hotshots.service.hotshots.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class TransactionIdentity implements Serializable  {

	@NotNull
	private int courtId;
	
	@NotNull
	private String timeSlot;
	
	@NotNull
	private String bookingDate;

	public TransactionIdentity(int courtId, String timeSlot, String bookingDate) {
		this.courtId = courtId;
		this.timeSlot = timeSlot;
		this.bookingDate = bookingDate;
	}
	
	public TransactionIdentity() {
		
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
}
