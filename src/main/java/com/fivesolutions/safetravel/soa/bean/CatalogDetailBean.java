package com.fivesolutions.safetravel.soa.bean;

public class CatalogDetailBean extends BaseBean {

	private Integer id;
	private CatalogBean catalog;
	private String code;
	private String shortDescription;
	private String longDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatalogBean getCatalog() {
		return catalog;
	}

	public void setCatalog(CatalogBean catalog) {
		this.catalog = catalog;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

}
