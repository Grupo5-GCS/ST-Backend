package com.fivesolutions.safetravel.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.ServiceEntity;

public interface ServiceJpaRepository extends PagingAndSortingRepository<ServiceEntity, Integer> {

	@Query("select ser from ServiceEntity ser where ser.code=:code and ser.deleted=false")
	public ServiceEntity getServiceByCode(@Param("code") String code);
	
	@Query("select ser from ServiceEntity ser where ser.code in(:code) and ser.deleted=false")
	public List<ServiceEntity> getListServiceByCodes(@Param("code") List<String> code);
	
	@Query("select ser from ServiceEntity ser where ser.id=:id and ser.deleted=false")
	public ServiceEntity getServiceById(@Param("id") Integer id);
	
	@Modifying
	@Query("UPDATE ServiceEntity ser SET ser.deleted=true WHERE ser.id=:id")
	public void delete(@Param("id") Integer id);
	
}
