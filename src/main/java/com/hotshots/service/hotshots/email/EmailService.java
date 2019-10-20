package com.hotshots.service.hotshots.email;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.hotshots.service.hotshots.entityDetails.BookingInfo;
import com.hotshots.service.hotshots.entityDetails.CourtInfo;
import com.hotshots.service.hotshots.entityDetails.TimeSlotInfo;

@Component
public class EmailService {

	@Autowired
	public Mail Mail;
	
	@Autowired
    private JavaMailSenderImpl emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;
	
//	private List<EmailContext> emailContextList= new ArrayList<EmailContext>();
	
	private HashMap<String, List<EmailContext>> hashMap = new HashMap<String, List<EmailContext>>();
	private HashMap<String, Float> courtTotals = new HashMap<String, Float>();
	
	 private String Shoes;
	 private String Shuttels = "";
	 private String Drinks = "";
	 private String Racquets = "";
	 private float CourtPrice = 0;
	 private float Total = 0;
	 private float CourtTotalCounter = 0;

	public void BuildContextData(List<BookingInfo> bookingInfo) {
		if(bookingInfo != null && bookingInfo.size() > 0) {
			Set<CourtInfo> courtInfo = bookingInfo.get(0).getCourtDetailsList();

			if(hashMap.size() > 0) {
				hashMap.clear();
			}
			
			if(this.courtTotals.size() > 0) {
				this.courtTotals.clear();
			}
			
		 for (CourtInfo courtDetails : courtInfo) {
			 
//				if(emailContextList.size() > 0) {
//					emailContextList = new 
//				}
		
			CourtTotalCounter = 0;
			 List<EmailContext> emailContextList= new ArrayList<EmailContext>();
				
			 for (TimeSlotInfo timeSlotDetails : courtDetails.getTimeSlotDetails()) {
				 Shoes = "";
				 Shuttels = "";
				 Drinks = "";
				 Racquets = "";
				 CourtPrice = 0;
				 Total = 0;
				timeSlotDetails.getUtilityInfoDetails().forEach(utilityDetails -> {	
					switch(utilityDetails.getUtilityName()) {
					case "Shoes":
							Shoes = Float.toString(utilityDetails.getUtilityPrice() * utilityDetails.getUtilityQuantity());
							break;
					case "Shuttels": 
						Shuttels = Float.toString(utilityDetails.getUtilityPrice() * utilityDetails.getUtilityQuantity());
						break;
					case "Drinks": 
						Drinks = Float.toString(utilityDetails.getUtilityPrice() * utilityDetails.getUtilityQuantity());
						break;
						
					case "Racquets": 
						Racquets = Float.toString(utilityDetails.getUtilityPrice() * utilityDetails.getUtilityQuantity());
						break;
						
					case "CourtPrice": 
						CourtPrice = utilityDetails.getUtilityPrice();
						break;
					}
						Total = Total + (utilityDetails.getUtilityPrice() * utilityDetails.getUtilityQuantity());
						
				});
				 EmailContext emailContext = new EmailContext(timeSlotDetails.getTimeSlotCode(), CourtPrice, Shoes, Shuttels, Drinks, Racquets, Total);
				 emailContextList.add(emailContext);
				
			}
			 
			 hashMap.put(courtDetails.getCourtName(), emailContextList);
			 if(emailContextList != null && emailContextList.size() > 0) {
				 emailContextList.forEach(item -> {
					 CourtTotalCounter = CourtTotalCounter + item.Total;
				 });
				 this.courtTotals.put(courtDetails.getCourtName(), CourtTotalCounter);
			 }
		}

			
	}
		if(hashMap.size() > 0) {
			System.out.println();
		}
}
	
	
	public void sendEmail() {
		 MimeMessage message = emailSender.createMimeMessage();
		 try {
		        MimeMessageHelper helper = new MimeMessageHelper(message,
		                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
		                StandardCharsets.UTF_8.name());
		        Context context = new Context();
		        context.setVariable("emailContext", this.hashMap);
		        context.setVariable("courtTotalValue", this.courtTotals);
		        String html = templateEngine.process("email-template", context);
		        helper.setTo("vamsismarty143@gmail.com");
		        helper.setText(html, true);
		        helper.setSubject("Report Data");
		        helper.setFrom("hotshotstest6@gmail.com");
		        
		        emailSender.setUsername("hotshotstest6@gmail.com");
		        emailSender.setPassword("Hotshots@123");
		        emailSender.setHost("smtp.gmail.com");
		        emailSender.setPort(587);		        
		        emailSender.send(message);
		        System.out.println("Email sent");
		       
		 }catch(Exception ex) {
			System.out.println(ex); 
		 }	        

	}

}