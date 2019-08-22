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
@Table(name="utilityinfo")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "utilityId")
public class UtilityInfo implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utilityId")
	private int UtilityId;
	
	@Column(name = "utilityName")
	private String UtilityName;
	
	@Column(name = "utilityPrice")
	private float UtilityPrice;
	
	@Column(name = "utilityQuantity")
	private int UtilityQuantity;
	
	
	@OneToOne
    @JoinColumn(name = "timeSlotId", insertable =  true, updatable = false)
    private TimeSlotInfo TimeSlotInfo;

	public UtilityInfo() {
		
	}

	public int getUtilityId() {
		return UtilityId;
	}

	public int getUtilityQuantity() {
		return UtilityQuantity;
	}

	public void setUtilityQuantity(int utilityQuantity) {
		UtilityQuantity = utilityQuantity;
	}

	public void setUtilityId(int utilityId) {
		UtilityId = utilityId;
	}

	public String getUtilityName() {
		return UtilityName;
	}

	public void setUtilityName(String utilityName) {
		UtilityName = utilityName;
	}

	public float getUtilityPrice() {
		return UtilityPrice;
	}

	public void setUtilityPrice(float utilityPrice) {
		UtilityPrice = utilityPrice;
	}

	public TimeSlotInfo getTimeSlotInfo() {
		return TimeSlotInfo;
	}

	public void setTimeSlotInfo(TimeSlotInfo timeSlotInfo) {
		TimeSlotInfo = timeSlotInfo;
	}
	
	
	
	
	
	
}
