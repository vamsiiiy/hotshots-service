package com.hotshots.service.hotshots.entityDetails;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="BookingInfo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "bookingId")
public class BookingInfo implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingId")
	private Integer bookingId;
	
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
	@OrderBy("courtName ASC")
	private Set<CourtInfo> courtDetailsList;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
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
