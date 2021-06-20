package com.fivesolutions.safetravel.service;

import java.util.List;

import com.fivesolutions.safetravel.soa.bean.UserBean;

public interface UserService {

	abstract UserBean saveUser(UserBean userBean);
	abstract UserBean getUserById(Integer id);
	abstract UserBean getUserByUsername(String username);
	abstract UserBean getUserByEmail(String email);
	abstract UserBean getUserByTokenResetPassword(String tokenResetPassword);
	abstract List<UserBean> getAllUsers();
	
}
