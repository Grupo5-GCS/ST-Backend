package com.fivesolutions.safetravel.soa.bean;

import java.util.Date;

public class UserBean extends BaseBean {

	private Integer id;
	private Integer organizationId;
	private String documentType;
	private String documentNumber;
	private String genderTypeId;
	private ProfileBean profile;
	private String name;
	private String lastname;
	private String surname;
	private String position;
	private Date birthDate;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String tokenResetPassword;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getGenderTypeId() {
		return genderTypeId;
	}

	public void setGenderTypeId(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	public ProfileBean getProfile() {
		return profile;
	}

	public void setProfile(ProfileBean profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getTokenResetPassword() {
		return tokenResetPassword;
	}

	public void setTokenResetPassword(String tokenResetPassword) {
		this.tokenResetPassword = tokenResetPassword;
	}
}
