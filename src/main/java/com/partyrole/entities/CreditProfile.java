package com.partyrole.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CreditProfile {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private LocalDateTime creditProfileDate;
	private int creditRiskRating;
	private int creditScore;

	@OneToOne(targetEntity = ValidFor.class, cascade = CascadeType.ALL)
	private ValidFor validFor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getCreditProfileDate() {
		return creditProfileDate;
	}
	public void setCreditProfileDate(LocalDateTime creditProfileDate) {
		this.creditProfileDate = creditProfileDate;
	}
	public int getCreditRiskRating() {
		return creditRiskRating;
	}
	public void setCreditRiskRating(int creditRiskRating) {
		this.creditRiskRating = creditRiskRating;
	}
	public int getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}



}
