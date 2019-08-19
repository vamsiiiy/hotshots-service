package com.hotshots.service.hotshots.entityDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="TimeSlotInfo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "timeSlotId")
public class TimeSlotInfo implements java.io.Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "timeSlotId")
	private int timeSlotId;
	
	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public BookingDetails getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(BookingDetails bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public TimeSlotInfo() {
		
	}

	public String getTimeSlotCode() {
		return timeSlotCode;
	}

	public void setTimeSlotCode(String timeSlotCode) {
		this.timeSlotCode = timeSlotCode;
	}

	public String getTimeSlotDescription() {
		return timeSlotDescription;
	}

	public void setTimeSlotDescription(String timeSlotDescription) {
		this.timeSlotDescription = timeSlotDescription;
	}

	public boolean isSlotBooked() {
		return isSlotBooked;
	}

	public void setSlotBooked(boolean isSlotBooked) {
		this.isSlotBooked = isSlotBooked;
	}

//	public int getCourtId() {
//		return courtId;
//	}
//
//	public void setCourtId(int courtId) {
//		this.courtId = courtId;
//	}

	@Column(name = "timeSlotCode")
	private String timeSlotCode;
	
	@Column(name = "timeSlotDescription")
	private String timeSlotDescription;
	
	@Column(name = "isSlotBooked")
	private boolean isSlotBooked;
	
//	@Column(name = "courtId")
//	private int courtId;
	
	
	@ManyToOne
	@JoinColumn(name="courtId", insertable =  true, updatable = false)
	private CourtInfo courtInfo;



	public TimeSlotInfo(int timeSlotId, String timeSlotCode, String timeSlotDescription, boolean isSlotBooked,
			int courtId, CourtInfo courtInfo) {
		this.timeSlotId = timeSlotId;
		this.timeSlotCode = timeSlotCode;
		this.timeSlotDescription = timeSlotDescription;
		this.isSlotBooked = isSlotBooked;
//		this.courtId = courtId;
		this.courtInfo = courtInfo;
//		this.bookingDetails = bookingDetails;
	}

	public CourtInfo getCourtInfo() {
		return courtInfo;
	}

	public void setCourtInfo(CourtInfo courtInfo) {
		this.courtInfo = courtInfo;
	}
	

	@OneToOne(mappedBy = "TimeSlotInfo",
            fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
    private BookingDetails bookingDetails;
	
	public UtilityInfo getUtilityInfoDetails() {
		return utilityInfoDetails;
	}

	public void setUtilityInfoDetails(UtilityInfo utilityInfoDetails) {
		this.utilityInfoDetails = utilityInfoDetails;
	}

	@OneToOne(mappedBy = "TimeSlotInfo",
            fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
    private UtilityInfo utilityInfoDetails;
	
	@OneToOne(mappedBy = "TimeSlotInfo",
            fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
    private PaymentDetails paymentDetails;

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}
    

}
