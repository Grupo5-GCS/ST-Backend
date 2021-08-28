package com.fivesolutions.safetravel.soa.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fivesolutions.safetravel.service.ServiceService;
import com.fivesolutions.safetravel.soa.bean.ServiceBean;
import com.fivesolutions.safetravel.soa.request.GenericRequest;
import com.fivesolutions.safetravel.soa.response.GenericResponse;

@RestController
@RequestMapping(value = "/sc")
public class ServiceController {

	private static final Logger logger = LogManager.getLogger(ServiceController.class);
	
	@Autowired
	private ServiceService serviceService;
	
	
	@RequestMapping(value = "/ss", method = RequestMethod.POST)
	public GenericResponse<ServiceBean> save(@RequestBody GenericRequest<ServiceBean> request) {
		logger.info("ServiceController.getServiceById()");
		GenericResponse<ServiceBean> response = new GenericResponse<ServiceBean>();
		ServiceBean serviceBean = serviceService.saveService(request.getData());
		response.setData(serviceBean);
		return response;
	}
	
	@RequestMapping(value = "/gas", method = RequestMethod.POST)
	public GenericResponse<ServiceBean> getAllServiceBean(@RequestBody GenericRequest<ServiceBean> request) {
		logger.info("ServiceController.getAllServiceBean()");
		GenericResponse<ServiceBean> response = new GenericResponse<ServiceBean>();
		List<ServiceBean> listServices = serviceService.getAllServices();
		response.setDatalist(listServices);
		return response;
	}
	
	@RequestMapping(value = "/gsbi", method = RequestMethod.POST)
	public GenericResponse<ServiceBean> getServiceById(@RequestBody GenericRequest<ServiceBean> request) {
		logger.info("ServiceController.getServiceById()");
		GenericResponse<ServiceBean> response = new GenericResponse<ServiceBean>();
		ServiceBean serviceBean = serviceService.getServiceById(request.getData().getId());
		response.setData(serviceBean);
		return response;
	}
	
	@RequestMapping(value = "/ds", method = RequestMethod.POST)
	public GenericResponse<ServiceBean> deleteProduct(@RequestBody GenericRequest<ServiceBean> request) {
		logger.info("ProductController.deleteProduct()");
		GenericResponse<ServiceBean> response = new GenericResponse<>();
		serviceService.deleteService(request.getData().getId());
		return response;
	}
}
