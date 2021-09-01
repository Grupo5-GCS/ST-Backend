package com.fivesolutions.safetravel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fivesolutions.safetravel.entity.CommentaryEntity;
import com.fivesolutions.safetravel.entity.ProductEntity;
import com.fivesolutions.safetravel.repository.jpa.CommentaryJpaRepository;
import com.fivesolutions.safetravel.service.CommentaryService;
import com.fivesolutions.safetravel.service.UserService;
import com.fivesolutions.safetravel.soa.bean.CommentaryBean;
import com.fivesolutions.safetravel.soa.bean.ProductBean;
import com.fivesolutions.safetravel.soa.bean.UserBean;

@Service
public class CommentaryServiceImpl implements CommentaryService {

	@Autowired
	private CommentaryJpaRepository commentaryRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public CommentaryBean save(CommentaryBean commentaryBean) {
		CommentaryEntity commentaryEntity = new CommentaryEntity();
		BeanUtils.copyProperties(commentaryBean, commentaryEntity);
		if(commentaryBean.getProduct() != null) {
			commentaryEntity.setProduct(new ProductEntity());
			commentaryEntity.getProduct().setId(commentaryBean.getProduct().getId());
		}
		
		commentaryEntity = commentaryRepository.save(commentaryEntity);
		commentaryBean.setId(commentaryEntity.getId());
		
		return commentaryBean;
	}

	@Override
	public List<CommentaryBean> getCommentaryByProductId(CommentaryBean commentary) {
		List<CommentaryBean> result = new ArrayList<>();
		List<CommentaryEntity> list = commentaryRepository.getCommentaryByProductId(commentary.getProduct().getId());
		if(list != null) {
			list.forEach(commentaryEntity -> {
				CommentaryBean commentaryBean = new CommentaryBean();
				BeanUtils.copyProperties(commentaryEntity, commentaryBean);
				if(commentaryEntity.getProduct() != null) {
					commentaryBean.setProduct(new ProductBean());
					commentaryBean.getProduct().setId(commentaryEntity.getProduct().getId());
				}
				if(commentaryEntity.getUserCreateId() != null) {
					UserBean user = userService.getUserById(commentaryEntity.getUserCreateId());
					commentaryBean.setUsername(user.getUsername());
				}
				result.add(commentaryBean);
			});
			return result;
		}
		return null;
	}

}
