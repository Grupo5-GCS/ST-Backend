package com.fivesolutions.safetravel.soa.bean;

public class OrganizationBean extends BaseBean {

	private Integer id;
	private String name;
	private ServiceBean service;
	private String ruc;
	private String direction;
	private String phone;
	private String responsablePaymentName;
	private String responsablePaymentPhone;
	private String responsablePaymentEmail;
	private UserBean adminUserId;
	private String photo;

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

	public UserBean getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(UserBean adminUserId) {
		this.adminUserId = adminUserId;
	}

	public ServiceBean getService() {
		return service;
	}

	public void setService(ServiceBean service) {
		this.service = service;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
