package com.fivesolutions.safetravel.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fivesolutions.safetravel.entity.UserEntity;

public interface UserJpaRepository extends PagingAndSortingRepository<UserEntity, Integer> {

	@Query("select u from UserEntity u where u.username = :username")
	UserEntity findByUsername(@Param("username") String username);
	
	@Query("select u from UserEntity u where u.email=:email")
	UserEntity findUserByEmail(@Param("email") String email);
	
	@Query("select u from UserEntity u where u.tokenResetPassword=:tokenResetPassword")
	UserEntity findUserByTokenResetPassword(@Param("tokenResetPassword") String tokenResetPassword);
}
