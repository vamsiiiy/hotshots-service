package com.hotshots.service.hotshots.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TimeSlot")
public class TimeSlotEntity {

	@Id
	private int timeSlotId;
	
	@Column(name = "timeSlotName")
	private String timeSlotName;
	
	public TimeSlotEntity(int timeSlotId, String timeSlotName, int transactionId) {
		this.timeSlotId = timeSlotId;
		this.timeSlotName = timeSlotName;
		this.transactionId = transactionId;
	}

	@Column(name = "transactionId")
	private int transactionId;

	public int getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(int timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getTimeSlotName() {
		return timeSlotName;
	}

	public void setTimeSlotName(String timeSlotName) {
		this.timeSlotName = timeSlotName;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
}
