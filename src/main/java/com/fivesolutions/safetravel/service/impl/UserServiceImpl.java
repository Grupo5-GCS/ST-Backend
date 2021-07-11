package com.fivesolutions.safetravel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fivesolutions.safetravel.entity.ProfileEntity;
import com.fivesolutions.safetravel.entity.UserEntity;
import com.fivesolutions.safetravel.repository.jpa.UserJpaRepository;
import com.fivesolutions.safetravel.service.UserService;
import com.fivesolutions.safetravel.soa.bean.ProfileBean;
import com.fivesolutions.safetravel.soa.bean.UserBean;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserJpaRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Override
	public UserBean saveUser(UserBean userBean) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(userBean, userEntity);
		if(userBean.getId() == null) {
			if(userBean.getPassword() != null) {
				userEntity.setPassword(bcrypt.encode(userBean.getPassword()));							
			} else {
				userEntity.setPassword(bcrypt.encode(userBean.getDocumentNumber()));										
			}
		} else {
			userEntity.setPassword((userBean.getPassword()));
		}
		//userEntity.setPassword(EncryptUtil.encryptMD5(userBean.getPassword()));
		userEntity.setProfile(new ProfileEntity());
		userEntity.getProfile().setId(userBean.getProfile().getId());
		
		userEntity = userRepository.save(userEntity);
		userBean.setId(userEntity.getId());
		return userBean;
	}

	@Override
	public UserBean getUserById(Integer id) {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(userEntity, userBean);
		userBean.setProfile(new ProfileBean());
		userBean.getProfile().setId(userEntity.getProfile().getId());
		
		return userBean;
	}

	@Override
	public UserBean getUserByUsername(String username) {
		UserEntity userEntity = userRepository.findByUsername(username);
		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(userEntity, userBean);
		userBean.setProfile(new ProfileBean());
		userBean.getProfile().setId(userEntity.getProfile().getId());
		return userBean;
	}

	@Override
	public UserBean getUserByEmail(String email) {
		
		return null;
	}

	@Override
	public UserBean getUserByTokenResetPassword(String tokenResetPassword) {
		
		return null;
	}

	@Override
	public List<UserBean> getAllUsers() {
		List<UserEntity> listUsersEntity = (List<UserEntity>) userRepository.findAll();
		List<UserBean> listUserBean = new ArrayList<>();
		if(listUsersEntity != null) {
			UserBean userBean = new UserBean();
			listUsersEntity.forEach(userEntity -> {
				BeanUtils.copyProperties(userEntity, userBean);
				if(userEntity.getProfile() != null) {
					userBean.setProfile(new ProfileBean());
					userBean.getProfile().setId(userEntity.getProfile().getId());
				}
				listUserBean.add(userBean);
			});
			return listUserBean;
		}
		return null;
	}

}
