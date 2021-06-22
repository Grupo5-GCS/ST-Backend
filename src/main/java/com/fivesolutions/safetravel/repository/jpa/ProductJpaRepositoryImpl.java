package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.fivesolutions.safetravel.soa.bean.ProductBean;

public class ProductJpaRepositoryImpl implements ProductJpaRepositoryCustom {

	@PersistenceContext()
	private EntityManager em;
	
	@Override
	public List<Object[]> getProductByNameAndDates(ProductBean productBean) {
		return null;
	}

}
