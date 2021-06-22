package com.fivesolutions.safetravel.service;

import java.util.List;

import com.fivesolutions.safetravel.soa.bean.OrganizationBean;

public interface OrganizationService {

	abstract OrganizationBean saveOrganization(OrganizationBean orgBean);
	abstract List<OrganizationBean> getAllOrganization();
	abstract List<OrganizationBean> getOrganizationByUserCreateId(Integer userCreateId);
	abstract OrganizationBean getOrganizationById(Integer organizationId);
	abstract void deletedOrganization(Integer organizationId);
	
}
