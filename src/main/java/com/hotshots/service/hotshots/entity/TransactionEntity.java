package com.hotshots.service.hotshots.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TransactionTable")
public class TransactionEntity {

	@EmbeddedId
	private TransactionIdentity transactionIdentity;
	
    @Column(name = "courtName")
	private String courtName;
	
    @Column(name = "courtPrice")
	private float courtPrice;
    
    @Column(name = "transactionId")
    private int transactionId;
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionEntity(TransactionIdentity transactionIdentity, String courtName, float courtPrice,
			int transactionId) {
	
		this.transactionIdentity = transactionIdentity;
		this.courtName = courtName;
		this.courtPrice = courtPrice;
		this.transactionId = transactionId;
	}

	public TransactionEntity() {
		
	}

	public TransactionIdentity getTransactionIdentity() {
		return transactionIdentity;
	}

	public void setTransactionIdentity(TransactionIdentity transactionIdentity) {
		this.transactionIdentity = transactionIdentity;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public float getCourtPrice() {
		return courtPrice;
	}

	public void setCourtPrice(float courtPrice) {
		this.courtPrice = courtPrice;
	}
}
