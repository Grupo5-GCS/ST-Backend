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
		StringBuffer queryStr = new StringBuffer("SELECT pd.id, pd.name, pd.image, pd.long_description, pd.price, pd.price_min, pd.price_max, pd.ubication, pd.start_date, pd.end_date FROM product pd ");
		queryStr.append(" inner join organization org ON pd.organization_id=org.id WHERE ");
		
		if(productBean.getName() != null && !productBean.getName().isEmpty()) {
			queryStr.append(" UPPER(pd.name) LIKE CONCAT('%',UPPER(:name),'%') AND ");
		}
		
		if(productBean.getStartDateRequest() != null && productBean.getEndDateRequest() != null) {
			queryStr.append(" pd.start_date BETWEEN :startDate AND :endDate AND ");
			queryStr.append(" pd.end_date BETWEEN :startDate AND :endDate AND");
		}
		
		if(productBean.getServiceId() != null) {
			queryStr.append(" pd.service_id LIKE CONCAT('%',:codesServices,'%') AND ");
		}
		
		queryStr.append(" pd.deleted=false and org.deleted=false and pd.status=1");
		Query query = em.createNativeQuery(queryStr.toString());
		
		if(productBean.getName() != null && !productBean.getName().isEmpty()) {
			query.setParameter("name", "%" + productBean.getName().toUpperCase() + "%");
		}
		
		if(productBean.getStartDateRequest() != null && productBean.getEndDateRequest() != null) {
			query.setParameter("startDate", productBean.getStartDateRequest());
			query.setParameter("endDate", productBean.getEndDateRequest());
		}
		
		if(productBean.getServiceId() != null) {
			query.setParameter("codesServices", "%" + productBean.getServiceId() + "%");
		}
		
		return query.getResultList();
	}

}
