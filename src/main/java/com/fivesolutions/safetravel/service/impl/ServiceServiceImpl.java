package com.fivesolutions.safetravel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fivesolutions.safetravel.entity.ServiceEntity;
import com.fivesolutions.safetravel.repository.jpa.ServiceJpaRepository;
import com.fivesolutions.safetravel.service.ServiceService;
import com.fivesolutions.safetravel.soa.bean.ServiceBean;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceJpaRepository serviceJpaRepository;
	
	@Override
	public ServiceBean saveService(ServiceBean serviceBean) {
		ServiceEntity serviceEntity = new ServiceEntity();
		if(serviceBean != null) {
			BeanUtils.copyProperties(serviceBean, serviceEntity);
			serviceEntity = serviceJpaRepository.save(serviceEntity);
			serviceBean.setId(serviceEntity.getId());
		}
		
		return serviceBean;
	}
		
	@Override
	public List<ServiceBean> getAllServices() {
		List<ServiceEntity> listResult = (List<ServiceEntity>) serviceJpaRepository.findAll();
		List<ServiceBean> result = new ArrayList<>();
		if(listResult != null) {
			listResult.forEach(serviceEntity -> {
				ServiceBean serviceBean = new ServiceBean();
				BeanUtils.copyProperties(serviceEntity, serviceBean);
				result.add(serviceBean);
			});
			return result;
		}
		return null;
	}

	@Override
	public ServiceBean getServiceById(Integer id) {
		ServiceEntity serviceEntity = serviceJpaRepository.getServiceById(id);
		ServiceBean serviceBean = new ServiceBean();
		if(serviceEntity != null) {
			BeanUtils.copyProperties(serviceEntity, serviceBean);
			return serviceBean;
		}
		return null;
	}

	@Override
	public void deleteService(Integer id) {
		if(id != null) {
			serviceJpaRepository.delete(id);
		}
		
	}

}
