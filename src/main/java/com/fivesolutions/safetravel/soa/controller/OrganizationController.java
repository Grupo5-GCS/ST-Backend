package com.fivesolutions.safetravel.soa.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fivesolutions.safetravel.service.OrganizationService;
import com.fivesolutions.safetravel.service.UserService;
import com.fivesolutions.safetravel.soa.bean.OrganizationBean;
import com.fivesolutions.safetravel.soa.bean.UserBean;
import com.fivesolutions.safetravel.soa.request.GenericRequest;
import com.fivesolutions.safetravel.soa.response.GenericResponse;

@RestController
@RequestMapping(value = "/oc")
public class OrganizationController {

	private static final Logger logger = LogManager.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/so", method = RequestMethod.POST)
	public GenericResponse<OrganizationBean> saveOrganization(@RequestBody GenericRequest<OrganizationBean> request) {
		logger.info("OrganizationController.saveOrganization()");
		GenericResponse<OrganizationBean> response = new GenericResponse<>();
		OrganizationBean org = new OrganizationBean();
		org = request.getData();
		organizationService.saveOrganization(org);
		response.setData(org);
		return response;
	}
	
	@RequestMapping(value = "/gao", method = RequestMethod.POST)
	public GenericResponse<OrganizationBean> getAllOrganization(@RequestBody GenericRequest<OrganizationBean> request) {
		logger.info("OrganizationController.getAllOrganization()");
		GenericResponse<OrganizationBean> response = new GenericResponse<>();
		List<OrganizationBean> organizationList = new ArrayList<OrganizationBean>();
		organizationList = organizationService.getAllOrganization();
		response.setDatalist(organizationList);
		return response;
	}
	
	@RequestMapping(value = "/gobup", method = RequestMethod.POST)
	public GenericResponse<OrganizationBean> getOrganizationByUserPrincipal(@RequestBody GenericRequest<OrganizationBean> request) {
		logger.info("OrganizationController.getOrganizationByUserPrincipal()");
		GenericResponse<OrganizationBean> response = new GenericResponse<>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserBean user = userService.getUserByUsername(principal.toString());
		OrganizationBean org = new OrganizationBean();
		if(user.getOrganizationId() != null) {
			org = organizationService.getOrganizationById(user.getOrganizationId());			
		}
		response.setData(org);
		return response;		
	}
	
	@RequestMapping(value = "/gobi", method = RequestMethod.POST)
	public GenericResponse<OrganizationBean> getOrganizationById(@RequestBody GenericRequest<OrganizationBean> request) {
		logger.info("OrganizationController.getOrganizationById()");
		GenericResponse<OrganizationBean> response = new GenericResponse<>();
		OrganizationBean org = new OrganizationBean();
		org = organizationService.getOrganizationById(request.getData().getId());
		response.setData(org);
		return response;
	}
	
}
