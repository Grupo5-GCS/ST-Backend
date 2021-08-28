package com.fivesolutions.safetravel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fivesolutions.safetravel.entity.OrganizationEntity;
import com.fivesolutions.safetravel.entity.ProductEntity;
import com.fivesolutions.safetravel.repository.jpa.ProductJpaRepository;
import com.fivesolutions.safetravel.service.ProductService;
import com.fivesolutions.safetravel.soa.bean.OrganizationBean;
import com.fivesolutions.safetravel.soa.bean.ProductBean;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductJpaRepository productRepository;

	@Override
	public ProductBean saveProduct(ProductBean productBean) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productBean, productEntity);
		if(productBean.getOrganization() != null) {
			productEntity.setOrganization(new OrganizationEntity());
			productEntity.getOrganization().setId(productBean.getOrganization().getId());			
		}
		
		productEntity = productRepository.save(productEntity);
		productBean.setId(productEntity.getId());
		return productBean;
	}

	@Override
	public List<ProductBean> getAllProducts() {
		List<ProductBean> result = new ArrayList<>();
		Iterable<ProductEntity> list = productRepository.getAllProducts();
		if(list != null) {
			list.forEach(productEntity -> {
				ProductBean productBean = new ProductBean();
				BeanUtils.copyProperties(productEntity, productBean);
				if(productEntity.getOrganization() != null) {
					productBean.setOrganization(new OrganizationBean());
					productBean.getOrganization().setId(productEntity.getOrganization().getId());
				}
				result.add(productBean);
			});
		}
		
		return result;
	}

	@Override
	public ProductBean getProductById(Integer productId) {
		ProductEntity productEntity = productRepository.getProductById(productId);
		ProductBean productBean = new ProductBean();
		BeanUtils.copyProperties(productEntity, productBean);
		if(productEntity.getOrganization() != null) {
			productBean.setOrganization(new OrganizationBean());
			productBean.getOrganization().setId(productEntity.getOrganization().getId());
		}
		
		return productBean;
	}

	@Override
	public List<ProductBean> getProductsByUserPrincipal(Integer organizationId) {
		List<ProductBean> result = new ArrayList<>();
		List<ProductEntity> list = productRepository.getProductsByUserPrincipal(organizationId);
		if(list != null) {
			list.forEach(productEntity -> {
				ProductBean productBean = new ProductBean();
				BeanUtils.copyProperties(productEntity, productBean);
				if(productEntity.getOrganization() != null) {
					productBean.setOrganization(new OrganizationBean());
					productBean.getOrganization().setId(productEntity.getOrganization().getId());
				}
				result.add(productBean);
			});
		}
		
		return result;
	}

	@Override
	public List<ProductBean> getProductByType(ProductBean productBean) {
		List<ProductEntity> list = productRepository.getProductByType(productBean.getType());
		List<ProductBean> result = new ArrayList<ProductBean>();
		if(list != null) {
			list.forEach(productEntity -> {
				ProductBean prodBean = new ProductBean();
				BeanUtils.copyProperties(productEntity, prodBean);
				if(productEntity.getOrganization() != null) {
					prodBean.setOrganization(new OrganizationBean());
					prodBean.getOrganization().setId(productEntity.getOrganization().getId());
				}
				result.add(prodBean);
			});
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getProductByNameAndDates(ProductBean productBean) {
		
		return null;
	}

	@Override
	public void deleteProduct(Integer productId) {
		if(productId != null) {
			productRepository.delete(productId);
		}
	}

	@Override
	public List<ProductBean> getProductsDisabled() {
		List<ProductEntity> list = productRepository.getAllProductsDisabled();
		List<ProductBean> result = new ArrayList<ProductBean>();
		if(list != null) {
			list.forEach(productEntity -> {
				ProductBean productBean = new ProductBean();
				BeanUtils.copyProperties(productEntity, productBean);
				if(productEntity.getOrganization() != null) {
					productBean.setOrganization(new OrganizationBean());
					productBean.getOrganization().setId(productEntity.getOrganization().getId());
				}
				result.add(productBean);
			});
			return result;
		}
		return null;
	}

	@Override
	public void updateStatus(Integer productId) {
		if(productId != null) {
			productRepository.updateStatus(productId);
		}
		
	}

}
