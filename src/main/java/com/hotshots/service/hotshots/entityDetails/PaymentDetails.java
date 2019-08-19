package com.hotshots.service.hotshots.entityDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="paymentdetails")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "paymentId")
public class PaymentDetails implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paymentId")
	private int PaymentId;
	
	@Column(name = "paymentMode")
	private String PaymentMode;
	
	@Column(name = "isPaymentDone")
	private boolean IsPaymentDone;
	
	@OneToOne
    @JoinColumn(name = "timeSlotId", insertable =  true, updatable = false)
    private TimeSlotInfo TimeSlotInfo;
	
	public PaymentDetails() {
		
	}

	public int getPaymentId() {
		return PaymentId;
	}

	public void setPaymentId(int paymentId) {
		PaymentId = paymentId;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}

	public boolean isIsPaymentDone() {
		return IsPaymentDone;
	}

	public void setIsPaymentDone(boolean isPaymentDone) {
		IsPaymentDone = isPaymentDone;
	}

	public TimeSlotInfo getTimeSlotInfo() {
		return TimeSlotInfo;
	}

	public void setTimeSlotInfo(TimeSlotInfo timeSlotInfo) {
		TimeSlotInfo = timeSlotInfo;
	}
	
	

}
