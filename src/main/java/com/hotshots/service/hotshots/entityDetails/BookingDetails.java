package com.hotshots.service.hotshots.entityDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="BookingDetails")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "bookingDetailsId")
public class BookingDetails implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingDetailsId")
	private int bookingDetailsId;
	


	public BookingDetails(int bookingDetailsId, String bookingName, String mobilenumber, int timeSlotId,
			TimeSlotInfo TimeSlotInfo) {
		this.bookingDetailsId = bookingDetailsId;
		this.bookingName = bookingName;
		this.mobilenumber = mobilenumber;
//		this.timeSlotId = timeSlotId;
		this.TimeSlotInfo = TimeSlotInfo;
	}

	public TimeSlotInfo getTimeSlotInfo() {
		return TimeSlotInfo;
	}

	public void setTimeSlotInfo(TimeSlotInfo timeSlotInfo) {
		TimeSlotInfo = timeSlotInfo;
	}

	@Column(name = "bookingName")
	private String bookingName;
	
	@Column(name = "mobilenumber")
	private String mobilenumber;
	
//	@Column(name = "timeSlotId")
//	private int timeSlotId;
	
	@OneToOne
    @JoinColumn(name = "timeSlotId", insertable =  true, updatable = false)
	
    private TimeSlotInfo TimeSlotInfo;
	

	public BookingDetails() {
		
	}

	public int getBookingDetailsId() {
		return bookingDetailsId;
	}

	public void setBookingDetailsId(int bookingDetailsId) {
		this.bookingDetailsId = bookingDetailsId;
	}

	public String getBookingName() {
		return bookingName;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

//	public int getTimeSlotId() {
//		return timeSlotId;
//	}
//
//	public void setTimeSlotId(int timeSlotId) {
//		this.timeSlotId = timeSlotId;
//	}
}
