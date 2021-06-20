package com.fivesolutions.safetravel.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "organization")
@Where(clause = "deleted =false")
public class OrganizationEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "ruc")
	private String ruc;

	@Column(name = "direction")
	private String direction;

	@Column(name = "phone")
	private String phone;

	@Column(name = "responsable_payment_name")
	private String responsablePaymentName;

	@Column(name = "responsable_payment_phone")
	private String responsablePaymentPhone;

	@Column(name = "responsable_payment_email")
	private String responsablePaymentEmail;
	
	@Column(name = "photo")
	private byte[] photo;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResponsablePaymentName() {
		return responsablePaymentName;
	}

	public void setResponsablePaymentName(String responsablePaymentName) {
		this.responsablePaymentName = responsablePaymentName;
	}

	public String getResponsablePaymentPhone() {
		return responsablePaymentPhone;
	}

	public void setResponsablePaymentPhone(String responsablePaymentPhone) {
		this.responsablePaymentPhone = responsablePaymentPhone;
	}

	public String getResponsablePaymentEmail() {
		return responsablePaymentEmail;
	}

	public void setResponsablePaymentEmail(String responsablePaymentEmail) {
		this.responsablePaymentEmail = responsablePaymentEmail;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}
