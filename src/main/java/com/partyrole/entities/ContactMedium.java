package com.partyrole.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ContactMedium {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(targetEntity = MediumCharacteristic.class, cascade = CascadeType.ALL)
	public MediumCharacteristic characteristic;
	public String mediumType;
	public boolean preferred;
	
	@OneToOne(targetEntity = ValidFor.class, cascade = CascadeType.ALL)
	private ValidFor validFor;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MediumCharacteristic getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(MediumCharacteristic characteristic) {
		this.characteristic = characteristic;
	}
	public String getMediumType() {
		return mediumType;
	}
	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}
	public boolean isPreferred() {
		return preferred;
	}
	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}
	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}	
}
