package com.hotshots.service.hotshots.email;

public class EmailContext {

	public String TimeSlotCode;
	
	public float CourtPrice;
	
	public String Shoes;
	
	public String Shuttels;
	
	public String Drinks;
	
	public String Racquets;
	
	public float Total;
	
	

	public EmailContext(String timeSlotCode, float courtPrice, String shoes, String shuttels, String drinks,
			String racquets, float total) {
		TimeSlotCode = timeSlotCode;
		CourtPrice = courtPrice;
		Shoes = shoes;
		Shuttels = shuttels;
		Drinks = drinks;
		Racquets = racquets;
		Total = total;
		
	}

	public String getTimeSlotCode() {
		return TimeSlotCode;
	}

	public void setTimeSlotCode(String timeSlotCode) {
		TimeSlotCode = timeSlotCode;
	}

	public float getCourtPrice() {
		return CourtPrice;
	}

	public void setCourtPrice(float courtPrice) {
		CourtPrice = courtPrice;
	}

	public String getShoes() {
		return Shoes;
	}

	public void setShoes(String shoes) {
		Shoes = shoes;
	}

	public String getShuttels() {
		return Shuttels;
	}

	public void setShuttels(String shuttels) {
		Shuttels = shuttels;
	}

	public String getDrinks() {
		return Drinks;
	}

	public void setDrinks(String drinks) {
		Drinks = drinks;
	}

	public String getRacquets() {
		return Racquets;
	}

	public void setRacquets(String racquets) {
		Racquets = racquets;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}
	
	
}
