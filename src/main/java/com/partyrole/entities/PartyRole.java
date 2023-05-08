package com.partyrole.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="PARTYROLE")
public class PartyRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_role_seq")
	@GenericGenerator(name = "party_role_seq", strategy = "com.partyrole.entities.IdGenerator")
	@Column(nullable = false)
	private String id;
	private String href;
	
	@NotNull(message = "The name is mandatory attribute")
	private String name;
	private String status;
	private String statusReason;

	@OneToOne(targetEntity = ValidFor.class, cascade = CascadeType.ALL)
	private ValidFor validFor;

	@NotNull(message = "The engaged party is mandatory attribute")
	@OneToOne(targetEntity = RelatedParty.class, cascade = CascadeType.ALL)
	private RelatedParty engagedParty;

	@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Pacc_fk", referencedColumnName = "id")
	private List<Account> account;

	@OneToMany(targetEntity = PaymentMethod.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "ppaymethods_fk", referencedColumnName = "id")
	private List<PaymentMethod> paymentMethod;

	@OneToMany(targetEntity = Characteristic.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pchars_fk", referencedColumnName = "id")
	private List<Characteristic> characteristic;

	@OneToMany(targetEntity = CreditProfile.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pcreditp_fk", referencedColumnName = "id")
	private List<CreditProfile> creditProfile;

	@OneToMany(targetEntity = Agreement.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "Pagg_fk", referencedColumnName = "id")
	private List<Agreement> agreement;

	@OneToMany(targetEntity = RelatedParty.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "prparty_fk", referencedColumnName = "id")
	private List<RelatedParty> relatedParty;

	@OneToMany(targetEntity = ContactMedium.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pcm_fk", referencedColumnName = "id")
	private List<ContactMedium> contactMediums;
	
	private boolean isActive;


	public ValidFor getValidFor() {
		return validFor;
	}
	public void setValidFor(ValidFor validFor) {
		this.validFor = validFor;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public List<Agreement> getAgreement() {
		return agreement;
	}

	public void setAgreement(List<Agreement> agreement) {
		this.agreement = agreement;
	}

	public List<Characteristic> getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(List<Characteristic> characteristic) {
		this.characteristic = characteristic;
	}

	public List<ContactMedium> getContactMediums() {
		return contactMediums;
	}

	public void setContactMediums(List<ContactMedium> contactMediums) {
		this.contactMediums = contactMediums;
	}

	public List<CreditProfile> getCreditProfile() {
		return creditProfile;
	}

	public void setCreditProfile(List<CreditProfile> creditProfile) {
		this.creditProfile = creditProfile;
	}

	public RelatedParty getEngagedParty() {
		return engagedParty;
	}

	public void setEngagedParty(RelatedParty engagedParty) {
		this.engagedParty = engagedParty;
	}

	public List<PaymentMethod> getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(List<PaymentMethod> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<RelatedParty> getRelatedParty() {
		return relatedParty;
	}

	public void setRelatedParty(List<RelatedParty> relatedParty) {
		this.relatedParty = relatedParty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}
	
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
