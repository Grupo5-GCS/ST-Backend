package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.ProductEntity;

public interface ProductJpaRepository extends PagingAndSortingRepository<ProductEntity, Integer>, ProductJpaRepositoryCustom {

	@Query("SELECT p FROM ProductEntity p inner join p.organization org where org.id =:organizationId and p.deleted=false ")	
	public List<ProductEntity> getProductsByUserPrincipal(@Param("organizationId") Integer organizationId);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.type=:type and deleted=false ")
	public List<ProductEntity> getProductByType(@Param("type") String type);
	
}
