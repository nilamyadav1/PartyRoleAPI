package com.partyrole.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class PaymentMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payementmethod_seq")
	@GenericGenerator(name = "payementmethod_seq", strategy = "com.partyrole.entities.IdGenerator")
	@Column(nullable = false)
	private String id;
	private String name;
	private String href;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
