package com.fivesolutions.safetravel.service;

import java.util.List;

import com.fivesolutions.safetravel.soa.bean.CommentaryBean;

public interface CommentaryService {
	
	abstract CommentaryBean save(CommentaryBean commentaryEntity);
	abstract List<CommentaryBean> getCommentaryByProductId(CommentaryBean commentaryBean);

}
