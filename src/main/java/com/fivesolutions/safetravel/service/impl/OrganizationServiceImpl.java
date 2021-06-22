package com.fivesolutions.safetravel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fivesolutions.safetravel.entity.OrganizationEntity;
import com.fivesolutions.safetravel.repository.jpa.OrganizationJpaRepository;
import com.fivesolutions.safetravel.service.OrganizationService;
import com.fivesolutions.safetravel.soa.bean.OrganizationBean;

@Service
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	private OrganizationJpaRepository organizationRepository;
	
	@Override
	public OrganizationBean saveOrganization(OrganizationBean orgBean) {
		OrganizationEntity orgEntity = new OrganizationEntity();
		BeanUtils.copyProperties(orgBean, orgEntity);
		orgEntity = organizationRepository.save(orgEntity);
		orgBean.setId(orgEntity.getId());
		return orgBean;
	}

	@Override
	public List<OrganizationBean> getAllOrganization() {
		List<OrganizationBean> result = new ArrayList<OrganizationBean>();
		Iterable<OrganizationEntity> list = organizationRepository.findAll();
		if(list != null) {
			list.forEach(organizationEntity -> {
				OrganizationBean orgBean = new OrganizationBean();
				BeanUtils.copyProperties(organizationEntity, orgBean);
				result.add(orgBean);
			});
		}
		
		return result;
	}

	@Override
	public void deletedOrganization(Integer organizationId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrganizationBean> getOrganizationByUserCreateId(Integer userCreateId) {

		return null;
	}

	@Override
	public OrganizationBean getOrganizationById(Integer organizationId) {
		OrganizationEntity organizationEntity = organizationRepository.findById(organizationId).orElse(null);
		OrganizationBean organizationBean = new OrganizationBean();
		BeanUtils.copyProperties(organizationEntity, organizationBean);
		
		return organizationBean;
	}

}
