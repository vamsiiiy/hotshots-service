package com.hotshots.service.hotshots.entityDetails;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="BookingInfo")
public class BookingInfo implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingId")
	private int bookingId;
	
	@Column(name = "bookingDate")
	private String bookingDate;
	
	public BookingInfo(int bookingId, String bookingDate, Set<CourtInfo> courtDetailsList) {
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.courtDetailsList = courtDetailsList;
	}
	
	public BookingInfo() {
		
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy="bookingInfo")
	@Cascade({CascadeType.ALL})
	@JsonManagedReference
	private Set<CourtInfo> courtDetailsList;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Set<CourtInfo> getCourtDetailsList() {
		return courtDetailsList;
	}

	public void setCourtDetailsList(Set<CourtInfo> courtDetailsList) {
		this.courtDetailsList = courtDetailsList;
	}

	
}
