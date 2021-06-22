package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import com.fivesolutions.safetravel.soa.bean.ProductBean;

public interface ProductJpaRepositoryCustom {
	
	abstract List<Object[]> getProductByNameAndDates(ProductBean productBean);

}
