package com.fivesolutions.safetravel.soa.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class ProductBean extends BaseBean {

	private Integer id;
	private OrganizationBean organization;
	private String name;
	private String emailAdmin;
	private byte[] image;
	private String imagePath;
	private String type;
	private String ubication;
	private Date startDate;
	private Date endDate;
	private Date startDateRequest;
	private Date endDateRequest;
	private String department;
	private String province;
	private String district;
	private String shortDescription;
	private String longDescription;
	private BigDecimal price;
	private String serviceId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrganizationBean getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationBean organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUbication() {
		return ubication;
	}

	public void setUbication(String ubication) {
		this.ubication = ubication;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDateRequest() {
		return startDateRequest;
	}

	public void setStartDateRequest(Date startDateRequest) {
		this.startDateRequest = startDateRequest;
	}

	public Date getEndDateRequest() {
		return endDateRequest;
	}

	public void setEndDateRequest(Date endDateRequest) {
		this.endDateRequest = endDateRequest;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
