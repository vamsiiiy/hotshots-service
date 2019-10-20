package com.hotshots.service.hotshots.entityDetails;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="CourtInfo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "courtId")
public class CourtInfo implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "courtId")
	private int courtId;
	
	@Column(name = "courtName")
	private String courtName;
	
//	@Column(name = "bookingId")
//	private int bookingId;
	
	@ManyToOne
	@JoinColumn(name="bookingId", insertable =  true, updatable = false)
	private BookingInfo bookingInfo;
	
	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}

	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
	}

	@OneToMany(fetch = FetchType.EAGER,mappedBy="courtInfo")
	@Cascade({CascadeType.ALL})
	@OrderBy("timeSlotCode ASC")
	private Set<TimeSlotInfo> timeSlotDetails;


	


	public CourtInfo(int courtId, String courtName, BookingInfo bookingInfo, Set<TimeSlotInfo> timeSlotDetails) {
		this.courtId = courtId;
		this.courtName = courtName;
//		this.bookingId = bookingId;
		this.bookingInfo = bookingInfo;
		this.timeSlotDetails = timeSlotDetails;
	}

	public Set<TimeSlotInfo> getTimeSlotDetails() {
		return timeSlotDetails;
	}

	public void setTimeSlotDetails(Set<TimeSlotInfo> timeSlotDetails) {
		this.timeSlotDetails = timeSlotDetails;
	}

	public CourtInfo() {
		
	}

	public int getCourtId() {
		return courtId;
	}

	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

//	public int getBookingId() {
//		return bookingId;
//	}
//
//	public void setBookingId(int bookingId) {
//		this.bookingId = bookingId;
//	}


	
	
}
