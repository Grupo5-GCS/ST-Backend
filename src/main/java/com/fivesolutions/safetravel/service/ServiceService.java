package com.fivesolutions.safetravel.service;

import java.util.List;

import com.fivesolutions.safetravel.soa.bean.ServiceBean;

public interface ServiceService {

	abstract List<ServiceBean> getAllServices();
	abstract ServiceBean getServiceById(Integer id);
	abstract ServiceBean saveService(ServiceBean serviceBean);
	abstract void deleteService(Integer id);
}
