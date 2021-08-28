package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.ProductEntity;

public interface ProductJpaRepository extends PagingAndSortingRepository<ProductEntity, Integer>, ProductJpaRepositoryCustom {

	@Query("SELECT p FROM ProductEntity p inner join p.organization org where org.id =:organizationId and p.deleted=false and p.status=1")	
	public List<ProductEntity> getProductsByUserPrincipal(@Param("organizationId") Integer organizationId);
	
	@Query("SELECT p FROM ProductEntity p WHERE p.type=:type and deleted=false ")
	public List<ProductEntity> getProductByType(@Param("type") String type);
	
	@Query("SELECT p FROM ProductEntity p inner join p.organization org WHERE p.id=:productId and p.deleted=false and org.deleted=false")
	public ProductEntity getProductById(@Param("productId") Integer productId);
	
	@Query("SELECT p FROM ProductEntity p inner join p.organization org WHERE p.deleted=false and org.deleted=false and p.status=1")
	public List<ProductEntity> getAllProducts();
	
	@Query("SELECT p FROM ProductEntity p inner join p.organization org WHERE p.deleted=false and org.deleted=false and p.status=0")
	public List<ProductEntity> getAllProductsDisabled();
	
	@Modifying
	@Query("UPDATE ProductEntity p SET p.status=1 WHERE p.id=:id")
	public void updateStatus(@Param("id") Integer id);
	
	@Modifying
	@Query("UPDATE ProductEntity p SET p.deleted=true WHERE p.id=:id")
	public void delete(@Param("id") Integer id);
	
}
