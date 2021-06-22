package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.OrganizationEntity;

public interface OrganizationJpaRepository extends PagingAndSortingRepository<OrganizationEntity, Integer> {

	@Query("SELECT o FROM OrganizationEntity o where o.userCreateId =:userCreateId")	
	public List<OrganizationEntity> getOrganizationByUserCreateId(@Param("userCreateId") Integer userCreateId);
	
}
